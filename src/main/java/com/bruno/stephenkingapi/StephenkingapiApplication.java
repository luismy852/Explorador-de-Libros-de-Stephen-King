package com.bruno.stephenkingapi;

import com.bruno.stephenkingapi.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StephenkingapiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StephenkingapiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.mostrarMenu();
	}
}
