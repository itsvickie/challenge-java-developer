package br.com.neurotech.challenge.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/health")
@Tag(name = "Health")
public class HealthController {
  @Value("${server.port}")
  private String appPort;

  @GetMapping
  public ResponseEntity<String> healthCheck() {
    return ResponseEntity.ok("API is up and running at port: " + appPort);
  }
}
