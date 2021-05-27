package br.com.zupacademy.ratkovski.mercadolivre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


//exclude = {SecurityAutoConfiguration.class} n quer carregar a autoconfiguration
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class MercadolivreApplication {

	public static void main(String[] args) {
		SpringApplication.run(MercadolivreApplication.class, args);
	}

}
