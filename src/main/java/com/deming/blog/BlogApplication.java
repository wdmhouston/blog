package com.deming.blog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import javax.swing.*;

//@ComponentScan({"com.xxx", "com.yy...."})  add packages to be scanned
@SpringBootApplication
public class BlogApplication {

	private static Logger LOG = LoggerFactory.getLogger(BlogApplication.class);

	public static void main(String[] args) {
		//SpringApplication.run(BlogApplication.class, args);
		SpringApplication app = new SpringApplication(BlogApplication.class);
		Environment env = app.run(args).getEnvironment();
		LOG.info("Started successfully");
		LOG.info("Address:\thttp://127.0.0.1:{}", env.getProperty("server.port"));
	}
}
