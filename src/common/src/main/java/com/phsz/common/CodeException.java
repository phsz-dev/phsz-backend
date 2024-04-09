package com.phsz.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Setter
@Getter
public class CodeException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    private int code;

    public CodeException(int code, String message) {
        super(message);
        this.code = code;
    }

    public CodeException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

}
