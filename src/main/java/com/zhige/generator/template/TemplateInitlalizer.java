package com.zhige.generator.template;

import org.apache.velocity.app.Velocity;

import java.util.Properties;

public class TemplateInitlalizer {

    public static void init(){
        Properties properties = new Properties();
        // 设置加载模板的方法，这里从classpath下加载

        properties.setProperty("resource.loader", "classpath");
        properties.setProperty("classpath.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        properties.setProperty(Velocity.INPUT_ENCODING, "utf-8");
        properties.setProperty("runtime.strict_mode","false");

        try {
            Velocity.init(properties);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("初始化引擎失败",e);
        }

    }
}
