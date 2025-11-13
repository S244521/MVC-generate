# MVC项目代码起步生成器

```
├─src
  └─main
      ├─java
      │  ├─com
      │  │  └─zhige
      │  │      └─generator
      │  │          ├─model
      │  │          ├─template
      |  |			|	├─ 模板初始化      
      |  |			|	└─ 模板工具类 
      |  |			|  	JAVA_TEMPLATE_DIR指定读取模板包路径
      │  │          ├─util
      |  |			└─CodeGenerator生成启动
      |  |				设置表名
      |  |				数据库连接配置
      |  |				修改包名
      |  |				数据库映射java类型添加修改      
      │  └─生成代码包
      │
      └─resources
          └─vm
              └─java 代码模板包可以自己修改
```

### 食用指南

##### 1.连接数据库

```
CodeGenerator.java

String url = "jdbc:mysql://localhost:3306/piclibrary";
String username = "root";
String password = "root";
改成自己的
```



##### 2.指定生成的表

```
CodeGenerator.java

TableInfo tableInfo = createTableInfoFromDatabase("tab");
改表名
```



##### 3.修改包名(生成的路径在java后)

```
CodeGenerator.java

return new TableInfo(tableName, StringUtils.capitalize(tableName), "packageCode", columns);
这个packageCode改了即可 包名格式就是常规包名xxx.xxx.xxx，没有数量要求建议写一个就行后面自己手动移
```



##### 4.数据库映射java类型添加修改

```
CodeGenerator.java

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
我把常用的弄进去了，可以看情况添加
```

