package com.pickle.pickledemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*@SpringBootApplication(
		scanBasePackages =  {
				"com.pickledemo.pickledemo",
				"com.pickledemo.entity"
		}
)*/
@SpringBootApplication
public class PickleApplication {

	public static void main(String[] args) {
		SpringApplication.run(PickleApplication.class, args);
	}

}
