package com.pickle.pickledemo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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

    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }
}
