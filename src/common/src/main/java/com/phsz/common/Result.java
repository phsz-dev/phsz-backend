package com.phsz.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回结果实体类
 *
 * @author 墨枫逸尘
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private Integer code;//响应码，0 代表成功; 1 代表失败; 2 开始为自定义错误码

    private String msg;  //响应信息 描述字符串

    private Object data; //返回的数据

    //增删改 成功响应
    public static Result success() {
        return new Result(0, "success", null);
    }

    //查询 成功响应
    public static Result success(String msg, Object data) {
        return new Result(0, msg, data);
    }

    public static Result success(String msg) {
        return new Result(0, msg, null);
    }

    //失败响应
    public static Result error(String msg) {
        return new Result(1, msg, null);
    }

    public static Result error(Integer code, String msg) {
        return new Result(code, msg, null);
    }

    public static Result error(Integer code, String msg, Object data) {
        return new Result(code, msg, data);
    }
}