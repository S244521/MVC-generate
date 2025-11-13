# MVC项目代码起步生成器

```
├─src
  └─main
      ├─java
      │  ├─com
      │  │  └─zhige
      │  │      └─generator
      │  │          ├─config 需要修改的配置都在这里
      │  │          ├─model      
      │  │          ├─template
      |  |			|	├─ 模板初始化      
      |  |			|	└─ 模板工具类 
      |  |			|  	JAVA_TEMPLATE_DIR指定读取模板包路径
      │  │          ├─util
      |  |			└─CodeGenerator生成启动
      │  └─生成代码包
      └─resources
          └─vm
              └─java 代码模板包可以自己修改
```

### 食用指南



##### 1.修改配置

```
CodeGeneratorConfig.java

public static final String URL="jdbc:mysql://localhost:3306/piclibrary";// 数据库连接地址
public static final String USERNAME="root";// 数据库连接用户名
public static final String PASSWORD="root";// 数据库连接密码
public static final String PACKAGE_NAME="com.zhige.library";// 生成代码的包名 生成的路径在java后
```



##### 2.选择生成方式

```
CodeGenerator.java

public static void main(String[] args) {
    CodeGenerator codeGenerator = new CodeGenerator();
    codeGenerator.generateCodeDataBase();// 一键生成数据库所有的表的代码

    codeGenerator.generateCodeTable("user");// 生成指定表的代码
}
```



##### 3.数据库映射java类型添加修改

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

