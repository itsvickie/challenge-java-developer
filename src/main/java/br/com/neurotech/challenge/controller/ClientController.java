package br.com.neurotech.challenge.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.neurotech.challenge.dto.request.ClientRequestDTO;
import br.com.neurotech.challenge.dto.response.ClientResponseDTO;
import br.com.neurotech.challenge.entity.Client;
import br.com.neurotech.challenge.mapper.ClientMapper;
import br.com.neurotech.challenge.service.ClientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/client")
@Tag(name = "Client")
public class ClientController {

  private final ClientService clientService;

  public ClientController(ClientService clientService) {
    this.clientService = clientService;
  }

  @PostMapping
  public ResponseEntity<Void> createClient(@Valid @RequestBody ClientRequestDTO clientDTO) {
    Client client = ClientMapper.toEntity(clientDTO);
    Long id = clientService.save(client);

    String location = String.format("/api/client/%d", id);

    HttpHeaders headers = new HttpHeaders();
    headers.add("Location", location);

    return new ResponseEntity<>(headers, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ClientResponseDTO> getClient(@PathVariable Long id) {
    Client client = clientService.getById(id);

    return ResponseEntity.ok(ClientMapper.toResponseDTO(client));
  }

  @GetMapping("/{id}/eligibility/{vehicleModel}")
  public ResponseEntity<Void> checkClientEligibility(
      @PathVariable Long id,
      @PathVariable String vehicleModel) {
    boolean isEligible = clientService.isClientEligibleForVehicleModel(id, vehicleModel);

    if (!isEligible) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    return ResponseEntity.ok().build();
  }

  @GetMapping("/specific-eligibility")
  public ResponseEntity<List<ClientResponseDTO>> getSpecificEligibilityClients() {
    List<ClientResponseDTO> clients = clientService.getSpecificEligibilityClients();
    return ResponseEntity.ok(clients);
  }
}
