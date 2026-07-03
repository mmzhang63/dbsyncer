/**
 * DBSyncer Copyright 2020-2026 All Rights Reserved.
 */
package org.dbsyncer.sdk.enums;

/**
 * 表级迁移阶段：结构 → 数据（串行流水线）。
 *
 * @author wuji
 * @version 1.0.0
 */
public enum MigrationTableStepEnum {

    /** 结构迁移 */
    SCHEMA(0),
    /** 数据迁移 */
    DATA(1);

    private final int code;

    MigrationTableStepEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static MigrationTableStepEnum ofCode(int code) {
        for (MigrationTableStepEnum value : values()) {
            if (value.code == code) {
                return value;
            }
        }
        return null;
    }
}
