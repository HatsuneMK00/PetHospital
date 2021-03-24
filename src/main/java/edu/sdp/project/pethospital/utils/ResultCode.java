package edu.sdp.project.pethospital.utils;

public enum ResultCode {
    /**
     * 成功
     */
    SUCCESS(200),

    CREATED(201),

    ACCEPTED(202),

    NO_CONTENT(204),
    /**
     * 失败
     */
    FAIL(400),
    /**
     * 未认证（签名错误）
     */
    UNAUTHORIZED(401),

    /**
     * 接口不存在
     */
    NOT_FOUND(404),

    /**
     * 服务器内部错误
     */
    INTERNAL_SERVER_ERROR(500);


    public int code;

    ResultCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
