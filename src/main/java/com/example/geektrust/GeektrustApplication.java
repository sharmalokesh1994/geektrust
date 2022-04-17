package com.example.geektrust;

import com.example.geektrust.input.InputFileReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class GeektrustApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(GeektrustApplication.class, args);
		ctx.getBean("inputFileReader", InputFileReader.class).readInput(args[0]);
	}

}
