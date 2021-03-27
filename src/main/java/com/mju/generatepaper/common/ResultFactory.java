package com.mju.generatepaper.common;

public final class ResultFactory {
    /** @deprecated */
    public static final int SUCCESS = 0;
    /** @deprecated */
    public static final int FAILED = 1;

    public ResultFactory() {
    }

    public static <T> Result<T> success(T data) {
        return create(ResponseStatusCode.SUCCESS, data);
    }

    public static <T> Result<T> success(String message, T data) {
        return create(ResponseStatusCode.SUCCESS, message, data);
    }

    public static <T> Result<T> failed(T data) {
        return create(ResponseStatusCode.FAILED, data);
    }

    public static <T> Result<T> failed(String message, T data) {
        return create(ResponseStatusCode.FAILED, message, data);
    }

    public static <T> Result<T> exception(T data) {
        return create(ResponseStatusCode.OPERATION_EXCEPTION, data);
    }

    public static <T> Result<T> exception(String message, T data) {
        return create(ResponseStatusCode.OPERATION_EXCEPTION, message, data);
    }

    public static <T> Result<T> create(int code, String message, T data) {
        return new Result(code, message, data);
    }

    public static <T> Result<T> create(ResponseStatusCode responseStatusCode, T data) {
        return new Result(responseStatusCode.getCode(), responseStatusCode.getMessage(), data);
    }

    public static <T> Result<T> create(ResponseStatusCode responseStatusCode, String message, T data) {
        return new Result(responseStatusCode.getCode(), message, data);
    }
}
