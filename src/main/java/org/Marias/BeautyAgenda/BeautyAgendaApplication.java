package org.Marias.BeautyAgenda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BeautyAgendaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeautyAgendaApplication.class, args);
	}

}
