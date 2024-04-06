package com.phsz.gateway;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.util.Base64;
import java.util.Date;

@Component
@Order(1)
public class JwtAuthenticationFilter implements GlobalFilter {

	private final JWSVerifier verifier;

    public JwtAuthenticationFilter(JwtProperties jwtProperties) throws JOSEException {
		byte[] secretBytes = Base64.getDecoder().decode(jwtProperties.getSecret());
		this.verifier = new MACVerifier(secretBytes);
    }

    @Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		String path = exchange.getRequest().getURI().getPath();
		// 从HTTP请求中获取JWT
		String token = extractJwtFromRequest(exchange.getRequest());
		System.out.println(1);
		if (token != null && validateJwt(token)) {
			System.out.println(2);
			JWTClaimsSet claims = getClaimsFromJwt(token);
			if (claims != null) {
				System.out.println(3);
				try {
					System.out.println(claims.getLongClaim("userid"));
					ServerHttpRequest request = exchange.getRequest().mutate()
							.header("UserId", String.valueOf(claims.getLongClaim("userid")))
							.header("Username", claims.getSubject())
							.header("Roles", String.join(",", claims.getStringListClaim("roles")))
							.build();
					return chain.filter(exchange.mutate().request(request).build());
				} catch (ParseException e) {
					throw new RuntimeException(e);
				}
			}
        }
		if(!permitAll(path)) {
			// 如果JWT无效，设置HTTP状态码为401
			exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
			// 设置内容类型为application/json
			exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
			// 创建返回的错误消息
			String errorMessage = "{\"error\": \"Unauthorized\", \"message\": \"Invalid JWT token\"}";
			// 获取DataBufferFactory来创建DataBuffer
			DataBufferFactory dataBufferFactory = exchange.getResponse().bufferFactory();
			DataBuffer buffer = dataBufferFactory.wrap(errorMessage.getBytes());
			// 使用writeWith方法返回错误消息
			return exchange.getResponse().writeWith(Mono.just(buffer));
		}
		// 清除Header中的UserId, Username, Roles
		ServerHttpRequest request = exchange.getRequest().mutate()
				.headers(httpHeaders -> {
					httpHeaders.remove("UserId");
					httpHeaders.remove("Username");
					httpHeaders.remove("Roles");
				})
				.build();
		return chain.filter(exchange.mutate().request(request).build());
	}

	private boolean permitAll(String path) {
		return !path.startsWith("/api/users");
	}

	private String extractJwtFromRequest(ServerHttpRequest request) {
		String authorization = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
		if (authorization != null && authorization.startsWith("Bearer ")) {
			return authorization.substring(7);
		}
		return null;
	}

	private boolean validateJwt(String token) {
		try {
			SignedJWT signedJWT = SignedJWT.parse(token);
			if (!signedJWT.verify(verifier)) {
				return false;
			}
			if (signedJWT.getJWTClaimsSet().getExpirationTime().before(new Date())) {
				return false;
			}
			return true;
		} catch (JOSEException | ParseException e) {
			return false;
		}
    }

	private JWTClaimsSet getClaimsFromJwt(String token) {
		try {
			SignedJWT signedJWT = SignedJWT.parse(token);
			return signedJWT.getJWTClaimsSet();
		} catch (ParseException e) {
			return null;
		}
	}

}
