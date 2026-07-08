/**
 * DBSyncer Copyright 2020-2026 All Rights Reserved.
 */
package org.dbsyncer.sdk.model;

import org.dbsyncer.sdk.enums.ValidateTaskStatusEnum;

import java.io.Serializable;

/**
 * 表校验快照：使用 cursor 记录当前阶段（正向/反向）的续跑页码。
 *
 * @author wuji
 */
public class ValidateTableSnapshot implements Serializable {

    /** 正向扫描下一页页码 */
    private long cursor;

    /** 表校验是否完成，见 {@link ValidateTaskStatusEnum} */
    private int status;

    /** 正向扫描是否完成，见 {@link ValidateTaskStatusEnum} */
    private int sourceScanDone;

    public ValidateTableSnapshot(long cursor, int status) {
        this.cursor = cursor;
        this.status = status;
        this.sourceScanDone = ValidateTaskStatusEnum.PENDING.getCode();
    }

    public ValidateTableSnapshot(long cursor, ValidateTaskStatusEnum status) {
        this.cursor = cursor;
        this.status = status == null ? ValidateTaskStatusEnum.PENDING.getCode() : status.getCode();
        this.sourceScanDone = ValidateTaskStatusEnum.PENDING.getCode();
    }

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

    public void setStatus(ValidateTaskStatusEnum status) {
        this.status = status == null ? ValidateTaskStatusEnum.PENDING.getCode() : status.getCode();
    }

    public ValidateTaskStatusEnum getStatusEnum() {
        return ValidateTaskStatusEnum.ofCode(status);
    }

    public int getSourceScanDone() {
        return sourceScanDone;
    }

    public void setSourceScanDone(int sourceScanDone) {
        this.sourceScanDone = sourceScanDone;
    }

    public void setSourceScanDone(ValidateTaskStatusEnum sourceScanDone) {
        this.sourceScanDone = sourceScanDone == null
                ? ValidateTaskStatusEnum.PENDING.getCode()
                : sourceScanDone.getCode();
    }

    public ValidateTaskStatusEnum getSourceScanDoneEnum() {
        return ValidateTaskStatusEnum.ofCode(sourceScanDone);
    }

}
