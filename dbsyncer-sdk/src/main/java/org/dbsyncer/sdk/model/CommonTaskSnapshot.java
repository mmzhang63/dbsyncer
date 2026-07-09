/**
 * DBSyncer Copyright 2020-2026 All Rights Reserved.
 */
package org.dbsyncer.sdk.model;

import org.dbsyncer.sdk.enums.MigrationStepStatusEnum;
import org.dbsyncer.sdk.enums.MigrationTableStepEnum;

/**
 * @author wuji
 * @version 1.0.0
 * @date 2026-07-09 10:37
 */
public class CommonTaskSnapshot {
    /** 当前迁移阶段，见 {@link MigrationTableStepEnum} */
    private int step;

    /** 当前阶段状态，见 {@link MigrationStepStatusEnum} */
    private int status;

    /** 数据迁移分页游标 */
    private String cursor;
}
