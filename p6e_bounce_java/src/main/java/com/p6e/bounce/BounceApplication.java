package com.p6e.bounce;

import com.p6e.bounce.tool.P6eProverbTool;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
@MapperScan("com.p6e.bounce.mapper")
public class BounceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BounceApplication.class, args);

        // 执行 谚语的爬虫程序
        // SpringApplication.run(BounceApplication.class, args).getBean(P6eProverbTool.class).execute();
    }

}
