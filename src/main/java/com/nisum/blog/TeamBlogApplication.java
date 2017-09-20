package com.nisum.blog;

import com.fasterxml.jackson.databind.Module;

import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TeamBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamBlogApplication.class, args);
	}

	@Bean
	public Module jodaTimeModule(){
		return new JodaModule();
	}
}
