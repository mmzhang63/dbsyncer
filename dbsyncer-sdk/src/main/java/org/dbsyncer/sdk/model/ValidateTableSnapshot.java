/**
 * DBSyncer Copyright 2020-2026 All Rights Reserved.
 */
package org.dbsyncer.sdk.model;

import org.dbsyncer.sdk.enums.ValidateSyncStatusEnum;

import java.io.Serializable;

/**
 * 表校验快照：使用 cursor 记录当前阶段（正向/反向）的续跑页码。
 *
 * @author wuji
 */
public class ValidateTableSnapshot implements Serializable {

    /** 正向扫描下一页页码 */
    private long cursor;

    /** 表校验是否完成，见 {@link ValidateSyncStatusEnum} */
    private int status = ValidateSyncStatusEnum.PENDING.getCode();

    /** 正向扫描是否完成，见 {@link ValidateSyncStatusEnum} */
    private int step = ValidateSyncStatusEnum.PENDING.getCode();

    public ValidateTableSnapshot() {}

    public long getCursor() {
        return cursor;
    }

    public void setCursor(long cursor) {
        this.cursor = cursor;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }
}
