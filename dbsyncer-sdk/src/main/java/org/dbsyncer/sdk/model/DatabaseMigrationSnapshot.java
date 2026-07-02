/**
 * DBSyncer Copyright 2020-2026 All Rights Reserved.
 */
package org.dbsyncer.sdk.model;

import org.dbsyncer.sdk.enums.MigrationStepStatusEnum;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 库级迁移快照（按 {@link DatabaseMapping#index} 索引）。
 * <p>库流水线状态见 {@link #status}；其下 {@link #tables} 按表映射 index 记录结构/数据阶段与行级游标。</p>
 *
 * @author wuji
 * @version 1.0.0
 * @date 2026-05-29 11:30
 */
public class DatabaseMigrationSnapshot implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 预留游标，与订正校验快照字段对齐 */
    private long cursor;

    /** 库映射流水线状态，见 {@link MigrationStepStatusEnum} */
    private int status;

    /** 表级快照：key = 表映射 index（库内唯一） */
    private final ConcurrentHashMap<Integer, DatabaseMigrationTableSnapshot> tables = new ConcurrentHashMap<>();

    public DatabaseMigrationSnapshot() {
    }

    public DatabaseMigrationSnapshot(long cursor, int status) {
        this.cursor = cursor;
        this.status = status;
    }

    public DatabaseMigrationSnapshot(long cursor, MigrationStepStatusEnum status) {
        this.cursor = cursor;
        this.status = status == null ? MigrationStepStatusEnum.PENDING.getCode() : status.getCode();
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

    public void setStatus(MigrationStepStatusEnum status) {
        this.status = status == null ? MigrationStepStatusEnum.PENDING.getCode() : status.getCode();
    }

    public MigrationStepStatusEnum getStatusEnum() {
        return MigrationStepStatusEnum.ofCode(status);
    }

    public ConcurrentHashMap<Integer, DatabaseMigrationTableSnapshot> getTables() {
        return tables;
    }

    public DatabaseMigrationTableSnapshot getTable(int tableIndex) {
        return tables.get(tableIndex);
    }

    public DatabaseMigrationTableSnapshot getOrCreateTable(int tableIndex) {
        return tables.computeIfAbsent(tableIndex, key -> new DatabaseMigrationTableSnapshot(
                MigrationStepStatusEnum.PENDING.getCode(),
                1,
                MigrationStepStatusEnum.PENDING.getCode()));
    }
}
