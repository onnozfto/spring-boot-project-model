package com.self.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//集成了通用Mapper（用mapper-spring-boot-starter jar包）需要用tk.mybatis的MapperScan注解
@MapperScan(basePackages = "com.self.project.dao")
public class SpringBootProjectTemplateApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootProjectTemplateApplication.class, args);
  }

}
