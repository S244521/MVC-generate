package com.zhige.generator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

// 生成模板
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TableInfo {
    private String tableName;
    private String className;
    private String packageName;
    private List<ColumnInfo> columns;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class ColumnInfo {
        private String columnName;
        private String javaType;
    }
}
