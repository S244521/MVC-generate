package com.zhige.generator;

import com.zhige.generator.model.TableInfo;
import com.zhige.generator.template.TemplateInitlalizer;
import com.zhige.generator.template.TemplateUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.util.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Class.forName;

public class CodeGenerator {
    public static void main(String[] args) {
        // 初始化Velocity引擎
        TemplateInitlalizer.init();

        // 从数据库获取表信息并生成代码（可以循环遍历所有表）
        TableInfo tableInfo = createTableInfoFromDatabase("tab");

        // 准备模板上下文
        VelocityContext context = TemplateUtil.prepareContext(tableInfo);

        // 获取模板路径列表
        List<String> templateList = TemplateUtil.getTemplateList();

        for (String templatePath : templateList) {
            // 获取Velocity模板对象
            Template template = Velocity.getTemplate(templatePath);

            // 获取生成的文件名
            String fileName = TemplateUtil.getFileName(templatePath, tableInfo);

            // 生成代码文件
            TemplateUtil.gennerateCode(template, context, fileName);
        }
    }

    private static TableInfo createTableInfoFromDatabase(String tableName) {
        List<TableInfo.ColumnInfo> columns = new ArrayList<>();

        String url = "jdbc:mysql://localhost:3306/piclibrary";
        String username = "root";
        String password = "root";

        Connection connection = null;
        List<String> fields = new ArrayList<>();

        try {
            // 1.数据库连接
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            // 获取数据库元数据
            Statement statement = connection.createStatement();

            // 解析表的字段
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);
            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                String columnName = metaData.getColumnName(i);
                String javaType = getJavaTypeFromSqlType(metaData.getColumnType(i));
                columns.add(new TableInfo.ColumnInfo(columnName, javaType));
//                System.out.println("字段名: " + columnName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }
        return new TableInfo(tableName, StringUtils.capitalize(tableName), "packageCode", columns);
    }

    private static String getJavaTypeFromSqlType(int sqlType) {
        switch (sqlType) {
            case Types.INTEGER:// int
            case Types.TINYINT:// 数据库中的boolean一个字节的数字型
                return "Integer";
            case Types.LONGVARCHAR:// text
            case Types.VARCHAR:// varchar
                return "String";
            case Types.TIMESTAMP:// timestamp
            case Types.DATE:// date
                return "Date";
            case Types.BIGINT:// long
                return "Long";
            case Types.DOUBLE:// double
                return "Double";
            case Types.FLOAT:// float
                return "Float";
            default:
                return "Object";
        }
    }
}
