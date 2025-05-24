package br.com.neurotech.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Neurotech Java Challenge", version = "1.0.0"))
public class ChallengeJavaDeveloperApplication {
  public static void main(String[] args) {
    SpringApplication.run(ChallengeJavaDeveloperApplication.class, args);
  }
}
