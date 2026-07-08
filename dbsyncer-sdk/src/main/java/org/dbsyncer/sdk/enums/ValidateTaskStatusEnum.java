/**
 * DBSyncer Copyright 2020-2026 All Rights Reserved.
 */
package org.dbsyncer.sdk.enums;

/**
 * 订正校验表快照状态。
 *
 * @author wuji
 * @version 1.0.0
 */
public enum ValidateTaskStatusEnum {

    /** 未完成 */
    PENDING(0, "待处理"),
    /** 已完成 */
    DONE(1, "已完成");

    private final int code;
    private final String message;

    ValidateTaskStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static boolean isDone(int status) {
        return status == DONE.code;
    }

    public static boolean isPending(int status) {
        return status == PENDING.code;
    }

    public static ValidateTaskStatusEnum ofCode(int code) {
        for (ValidateTaskStatusEnum value : values()) {
            if (value.code == code) {
                return value;
            }
        }
        return null;
    }
}
