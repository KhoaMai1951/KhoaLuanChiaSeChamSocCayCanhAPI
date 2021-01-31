package com.myclass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.myclass.*")
public class KhoaLuanChiaSeChamSocCayCanhApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(KhoaLuanChiaSeChamSocCayCanhApiApplication.class, args);
	}

}
