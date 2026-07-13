/**
 * DBSyncer Copyright 2020-2026 All Rights Reserved.
 */
package org.dbsyncer.sdk.model;

/**
 * 订正校验单表终态结果
 *
 * @author wuji
 * @version 1.0.0
 * @date 2026-06-04 18:30
 */
public class ValidateSyncDetailResult {

    /** 明细类型，如 rowData、tableSchema */
    private String detailType;
    private String sourceTableName;
    private String targetTableName;
    private Long sourceTotal;
    private Long targetTotal;
    private long diffTotal;
    private long fixedTotal;
    /** 差异 data 列表 JSON，汇总指标走独立列 */
    private String content;

    public String getDetailType() {
        return detailType;
    }

    public void setDetailType(String detailType) {
        this.detailType = detailType;
    }

    public String getSourceTableName() {
        return sourceTableName;
    }

    public void setSourceTableName(String sourceTableName) {
        this.sourceTableName = sourceTableName;
    }

    public String getTargetTableName() {
        return targetTableName;
    }

    public void setTargetTableName(String targetTableName) {
        this.targetTableName = targetTableName;
    }

    public Long getSourceTotal() {
        return sourceTotal;
    }

    public void setSourceTotal(Long sourceTotal) {
        this.sourceTotal = sourceTotal;
    }

    public Long getTargetTotal() {
        return targetTotal;
    }

    public void setTargetTotal(Long targetTotal) {
        this.targetTotal = targetTotal;
    }

    public long getDiffTotal() {
        return diffTotal;
    }

    public void setDiffTotal(long diffTotal) {
        this.diffTotal = diffTotal;
    }

    public long getFixedTotal() {
        return fixedTotal;
    }

    public void setFixedTotal(long fixedTotal) {
        this.fixedTotal = fixedTotal;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
