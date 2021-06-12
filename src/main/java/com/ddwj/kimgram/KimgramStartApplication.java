package com.ddwj.kimgram;

import com.ddwj.kimgram.config.SessionListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.servlet.http.HttpSessionListener;

@SpringBootApplication
public class KimgramStartApplication {

	public static void main(String[] args) {
		SpringApplication.run(KimgramStartApplication.class, args);
	}



	@Bean
	public HttpSessionListener httpSessionListener(){

		return new SessionListener();
	}
}
