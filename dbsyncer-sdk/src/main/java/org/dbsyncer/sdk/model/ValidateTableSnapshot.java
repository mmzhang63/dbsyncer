/**
 * DBSyncer Copyright 2020-2026 All Rights Reserved.
 */
package org.dbsyncer.sdk.model;

import java.io.Serializable;

/**
 * 表校验快照：使用 cursor 记录当前阶段（正向/反向）的续跑页码。
 *
 * @author wuji
 */
public class ValidateTableSnapshot implements Serializable {

    /** 正向扫描下一页页码 */
    private long cursor;

    /** 是否完成 0 未完成 1 已完成 */
    private int status;

    /** 正向扫描是否已完成 0 未完成 1 已完成 */
    private int sourceScanDone;

    public ValidateTableSnapshot(long cursor, int status) {
        this.cursor = cursor;
        this.status = status;
        this.sourceScanDone = 0;
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

    public int getSourceScanDone() {
        return sourceScanDone;
    }

    public void setSourceScanDone(int sourceScanDone) {
        this.sourceScanDone = sourceScanDone;
    }

}
