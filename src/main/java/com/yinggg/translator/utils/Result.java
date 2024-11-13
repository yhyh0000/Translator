package com.yinggg.translator.utils;

import java.util.Objects;

public class Result<T> {
    private int code;
    private String message;
    private T data;

    // 构造方法
    public Result(int code, String message) {
        this(code, message, null);  // 默认 data 为 null
    }

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 获取状态码
    public int getCode() {
        return code;
    }

    // 获取消息
    public String getMessage() {
        return message;
    }

    // 获取数据
    public T getData() {
        return data;
    }

    // 重写 toString 方法，便于调试输出
    @Override
    public String toString() {
        return "Result{code=" + code + ", message='" + message + "', data=" + (data != null ? data.toString() : "null") + "}";
    }

    // 重写 equals 方法，支持对象比较
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result<?> result = (Result<?>) o;
        return code == result.code && Objects.equals(message, result.message) && Objects.equals(data, result.data);
    }

    // 重写 hashCode 方法
    @Override
    public int hashCode() {
        return Objects.hash(code, message, data);
    }
}
