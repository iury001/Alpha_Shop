package com.NTT.articoliwebservice;

import com.NTT.articoliwebservice.controllers.ArticoloController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class ArticoliWebServiceApplication {
	private static final Logger logger = LoggerFactory.getLogger(ArticoliWebServiceApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ArticoliWebServiceApplication.class, args);
	}

}
