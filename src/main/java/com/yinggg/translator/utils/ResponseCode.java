package com.yinggg.translator.utils;

public enum ResponseCode {
    SUCCESS(200, "请求成功"),
    ERROR(500, "请求失败"),
    NOT_FOUND(404, "无法访问资源");

    private int code;
    private String message;

    // 枚举构造方法
    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    // 返回没有数据的 Result 对象
    public Result<Void> toResult() {
        return new Result<>(this.code, this.message, null);
    }

    // 返回带有数据的 Result 对象
    public <T> Result<T> toResult(T data) {
        return new Result<>(this.code, this.message, data);
    }

    // 你还可以添加其他方法，例如根据 code 查找对应的 ResponseCode
    public static ResponseCode fromCode(int code) {
        for (ResponseCode responseCode : values()) {
            if (responseCode.code == code) {
                return responseCode;
            }
        }
        return null; // 可以根据需要抛出异常
    }
}
