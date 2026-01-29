package com.indra.controlhorarioapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.jdbc.core.JdbcTemplate;


@SpringBootApplication
public class ControlHorarioApiApplication {
	private static final Logger log = LoggerFactory.getLogger(ControlHorarioApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ControlHorarioApiApplication.class, args);
	}
}
