package com.zhige.generator.template;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import com.zhige.generator.model.TableInfo;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TemplateUtil {
    private static final String JAVA_TEMPLATE_DIR = "vm/java/";

    public static VelocityContext prepareContext(TableInfo tableInfo){
        VelocityContext context = new VelocityContext();
        context.put("tableName", tableInfo.getTableName());
        context.put("className", tableInfo.getClassName());
        context.put("packageName", tableInfo.getPackageName());
        context.put("columns", tableInfo.getColumns());
        context.put("lowerCaseClassName", StringUtils.uncapitalize(tableInfo.getClassName()));
        if (tableInfo.getColumns().isEmpty()){
            context.put("idType", tableInfo.getColumns().get(0).getJavaType());
        }
        // 添加fields变量
        List<Map<String, Object>> fields=new ArrayList<>();
        for (TableInfo.ColumnInfo columnInfo : tableInfo.getColumns()) {
            Map<String, Object> field = new java.util.HashMap<>();
            field.put("name", columnInfo.getColumnName());
            field.put("type", columnInfo.getJavaType());
            fields.add(field);
        }
        context.put("fields", fields);
        System.out.println("fields变量内容: " + context.get("fields"));
        System.out.println("fields变量类型: " + fields.getClass());
        for (Map<String, Object> field : fields){
            System.out.println("field.get(\"type\") = " + field.get("type"));
            System.out.println("field.get(\"name\") = " + field.get("name"));
        }
        return context;
    }
    public static List<String> getTemplateList(){
        List<String> templates = new ArrayList<>();
        templates.add(JAVA_TEMPLATE_DIR + "Controller.java.vm");
        templates.add(JAVA_TEMPLATE_DIR + "Entity.java.vm");
        templates.add(JAVA_TEMPLATE_DIR + "Mapper.java.vm");
        templates.add(JAVA_TEMPLATE_DIR + "Service.java.vm");
        templates.add(JAVA_TEMPLATE_DIR + "ServiceImpl.java.vm");
        return templates;
    }

    public static String getFileName(String templatePath, TableInfo tableInfo){
        String fileName="";
        String packageDir="src/main/java/"+tableInfo.getPackageName().replace(".","/");
        if (templatePath.contains("Controller.java.vm")){
            fileName=packageDir+"/controller/"+tableInfo.getClassName()+"Controller.java";
        } else if (templatePath.contains("Entity.java.vm")) {
            fileName=packageDir+"/entity/"+tableInfo.getClassName()+"Entity.java";
        } else if (templatePath.contains("Mapper.java.vm")) {
            fileName=packageDir+"/mapper/"+tableInfo.getClassName()+"Mapper.java";
        } else if (templatePath.contains("Service.java.vm")) {
            fileName=packageDir+"/service/"+tableInfo.getClassName()+"Service.java";
        } else if (templatePath.contains("ServiceImpl.java.vm")) {
            fileName=packageDir+"/service/impl/"+tableInfo.getClassName()+"ServiceImpl.java";
        }
        return fileName;
    }
    public static void gennerateCode(Template template,VelocityContext context, String fileName){
        try {
            File file = new File(fileName);
            File parentDir = file.getParentFile();
            if (!parentDir.exists()){
                parentDir.mkdirs();
            }
            Writer writer = new FileWriter(file);
            template.merge(context, writer);
            writer.close();
            System.out.println("生成文件：" + fileName);
        } catch (Exception e) {
            System.out.println("生成文件失败：" + fileName + " 时出错");
            e.printStackTrace();
        }
    }
}
