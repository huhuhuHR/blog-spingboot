package com.huorong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// Spring Boot 应用的标识
@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
// mapper 接口类扫描包配置 mapper 接口类扫描包配置
@MapperScan("com.huorong.dao")
public class SpringbootApplication {

    public static void main(String[] args) {
        // 程序启动入口
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        SpringApplication.run(SpringbootApplication.class, args);
    }

    // @Bean
    // public PageHelper pageHelper() {
    // PageHelper pageHelper = new PageHelper();
    // Properties properties = new Properties();
    // properties.setProperty("offsetAsPageNum", "true");
    // properties.setProperty("rowBoundsWithCount", "true");
    // properties.setProperty("reasonable", "true");
    // properties.setProperty("dialect", "mysql"); // 配置mysql数据库的方言
    // pageHelper.setProperties(properties);
    // return pageHelper;
    // }
}
