/**
 * DBSyncer Copyright 2020-2026 All Rights Reserved.
 */
package org.dbsyncer.sdk.model;

import org.dbsyncer.sdk.enums.MigrationStepStatusEnum;
import org.dbsyncer.sdk.enums.MigrationTableStepEnum;

import java.io.Serializable;

/**
 * 表级迁移快照（按表映射 index 索引）。
 * <p>
 * 使用 {@link #step} 标记当前阶段，{@link #status} 标记该阶段状态（见 {@link MigrationStepStatusEnum}）。
 *
 * @author wuji
 * @version 1.0.0
 * @date 2026-05-29 11:30
 */
public class DatabaseMigrationTableSnapshot implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 当前迁移阶段，见 {@link MigrationTableStepEnum} */
    private int step;

    /** 当前阶段状态，见 {@link MigrationStepStatusEnum} */
    private int status;

    /** 数据迁移分页游标（页码，从 1 开始） */
    private long dataCursor;

    private long successTotal;

    private long failTotal;

    public DatabaseMigrationTableSnapshot() {
    }

    public DatabaseMigrationTableSnapshot(int step, int status, long dataCursor) {
        this.step = step;
        this.status = status;
        this.dataCursor = dataCursor;
    }

    /**
     * 按任务开关创建初始表快照。
     */
    public static DatabaseMigrationTableSnapshot createInitial(boolean enableCopySchema, boolean enableCopyData) {
        if (!enableCopySchema && enableCopyData) {
            return new DatabaseMigrationTableSnapshot(
                    MigrationTableStepEnum.DATA.getCode(),
                    MigrationStepStatusEnum.PENDING.getCode(),
                    1L);
        }
        return new DatabaseMigrationTableSnapshot(
                MigrationTableStepEnum.SCHEMA.getCode(),
                MigrationStepStatusEnum.PENDING.getCode(),
                1L);
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public void setStep(MigrationTableStepEnum step) {
        this.step = step == null ? MigrationTableStepEnum.SCHEMA.getCode() : step.getCode();
    }

    public MigrationTableStepEnum getStepEnum() {
        return MigrationTableStepEnum.ofCode(step);
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

    public long getDataCursor() {
        return dataCursor;
    }

    public void setDataCursor(long dataCursor) {
        this.dataCursor = dataCursor;
    }

    public long getSuccessTotal() {
        return successTotal;
    }

    public void setSuccessTotal(long successTotal) {
        this.successTotal = successTotal;
    }

    public long getFailTotal() {
        return failTotal;
    }

    public void setFailTotal(long failTotal) {
        this.failTotal = failTotal;
    }

    /**
     * 结构阶段是否已结束（含跳过）；已进入数据阶段视为结构已完成。
     */
    public boolean isSchemaPhaseDone() {
        MigrationTableStepEnum currentStep = getStepEnum();
        if (currentStep == MigrationTableStepEnum.DATA) {
            return true;
        }
        return currentStep == MigrationTableStepEnum.SCHEMA && MigrationStepStatusEnum.isDone(status);
    }

    /**
     * 数据阶段是否已结束（含跳过）。
     */
    public boolean isDataPhaseDone() {
        return getStepEnum() == MigrationTableStepEnum.DATA && MigrationStepStatusEnum.isDone(status);
    }

    /**
     * 结构阶段结束时更新快照；若仍需迁数据则切入数据阶段并重置游标。
     */
    public void completeSchemaPhase(MigrationStepStatusEnum schemaResult, boolean advanceToData) {
        if (advanceToData && schemaResult != null && MigrationStepStatusEnum.isDone(schemaResult.getCode())) {
            setStep(MigrationTableStepEnum.DATA);
            setStatus(MigrationStepStatusEnum.PENDING);
            setDataCursor(1L);
            return;
        }
        setStep(MigrationTableStepEnum.SCHEMA);
        setStatus(schemaResult);
    }

    /**
     * 标记数据阶段状态。
     */
    public void setDataPhaseStatus(MigrationStepStatusEnum dataResult) {
        setStep(MigrationTableStepEnum.DATA);
        setStatus(dataResult);
    }

    /**
     * 当前表在任务配置下是否已全部完成（含跳过）。
     */
    public boolean isTableFinished(boolean enableCopySchema, boolean enableCopyData) {
        if (enableCopySchema && !isSchemaPhaseDone()) {
            return false;
        }
        if (enableCopyData && !isDataPhaseDone()) {
            return false;
        }
        return enableCopySchema || enableCopyData;
    }
}
