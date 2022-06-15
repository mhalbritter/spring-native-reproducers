package com.example.gh1599;

import java.sql.Connection;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@NativeHint(types = {
//		@TypeHint(types = HikariDataSource.class, methods = { @MethodHint(name = "<init>") }),
//		@TypeHint(types = Statement[].class, access = {})
//})
public class Gh1599Application {

	public static void main(String[] args) {
		SpringApplication.run(Gh1599Application.class, args);
	}

	@Bean
	public DataSource datasource(DataSourceProperties dataSourceProperties) {
		HikariDataSource dataSource = dataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
		dataSource.setMaximumPoolSize(Runtime.getRuntime().availableProcessors() * 2);

		return dataSource;
	}

	@Bean
	CommandLineRunner commandLineRunner(DataSource dataSource) {
		return args -> {
			try (Connection connection = dataSource.getConnection()) {
				System.out.println("Got connection: " + connection);
			}
		};
	}

}
