package br.com.credentialskol;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@Slf4j
@EnableWebFlux
@SpringBootApplication
public class CredentialsKolApplication {

	public static void main(String[] args) {
		SpringApplication.run(CredentialsKolApplication.class, args);
	}

}
