package br.com.neurotech.challenge.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.neurotech.challenge.dto.request.ClientRequestDTO;
import br.com.neurotech.challenge.dto.response.ClientResponseDTO;
import br.com.neurotech.challenge.entity.Client;
import br.com.neurotech.challenge.service.ClientService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ClientController.class)
class ClientControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ClientService clientService;

  @InjectMocks
  private ClientController clientController;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void createClientSuccessfully() throws Exception {
    when(clientService.save(any(Client.class))).thenReturn(10L);

    ClientRequestDTO clientRequestDTO = new ClientRequestDTO("João", java.time.LocalDate.parse("2001-01-01"),
        5000.0);

    this.mockMvc.perform(post("/api/client")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(clientRequestDTO)))
        .andExpect(status().isCreated())
        .andExpect(header().string("Location", "/api/client/10"));
  }

  @Test
  void returnClientById() throws Exception {
    Client client = new Client(1L, "João", LocalDate.now(), 5000.0, null);
    when(clientService.getById(1L)).thenReturn(client);

    ClientResponseDTO clientResponseDTO = new ClientResponseDTO("João",
        Period.between(LocalDate.now(), LocalDate.now()).getYears(), 5000.0);

    this.mockMvc.perform(get("/api/client/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andExpect(content().json(
            objectMapper.writeValueAsString(clientResponseDTO)));
  }

  @Test
  void returnStatus200WhenUserIsEligible() throws Exception {
    when(clientService.isClientEligibleForVehicleModel(1L, "suv")).thenReturn(true);

    this.mockMvc.perform(get("/api/client/1/eligibility/suv")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void returnStatus403WhenUserIsEligible() throws Exception {
    when(clientService.isClientEligibleForVehicleModel(2L, "suv")).thenReturn(false);

    this.mockMvc.perform(get("/api/client/2/eligibility/suv")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isForbidden());
  }

  @Test
  void returnEligibilityClients() throws Exception {
    when(clientService.getSpecificEligibilityClients()).thenReturn(Collections.emptyList());

    this.mockMvc.perform(get("/api/client/specific-eligibility")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json("[]"));
  }
}
