package br.com.neurotech.challenge.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.neurotech.challenge.dto.response.ClientResponseDTO;
import br.com.neurotech.challenge.entity.Client;
import br.com.neurotech.challenge.entity.CreditType;
import br.com.neurotech.challenge.entity.VehicleModel;
import br.com.neurotech.challenge.mapper.ClientMapper;
import br.com.neurotech.challenge.repository.ClientRepository;

@Service
public class ClientService {
  private final ClientRepository clientRepository;

  public ClientService(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  /**
   * Salva um cliente no banco de dados
   *
   * @param client Cliente a ser salvo
   * @return ID do cliente salvo
   */
  public Long save(Client client) {
    Client existingClient = clientRepository.findByNameAndBirthDate(client.getName(), client.getBirthDate());
    if (existingClient != null) {
      throw new ResponseStatusException(HttpStatus.CONFLICT);
    }

    Client savedClient = clientRepository.save(client);
    return savedClient.getId();
  }

  /**
   * Recupera um cliente baseado no seu ID
   */
  public Client getById(Long id) {
    Client client = clientRepository.findById(id).orElse(null);
    if (client == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    return client;
  }

  /**
   * Verifica se o cliente é elegível para o modelo de veículo
   *
   * @param id    ID do cliente
   * @param model Modelo do veículo
   * @return true se o cliente é elegível, false caso contrário
   */
  public Boolean isClientEligibleForVehicleModel(Long id, String model) {
    VehicleModel vehicleModel = VehicleModel.fromString(model);

    Client client = this.getById(id);
    Double clientIncome = client.getIncome();

    switch (vehicleModel) {
      case HATCH:
        return clientIncome >= 5000 && clientIncome <= 15000;
      case SUV:
        int clientAge = client.getAge();
        return clientIncome >= 8000 && clientAge >= 20;
      default:
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * Recupera todos os clientes elegíveis para o crédito
   *
   * @return Lista de clientes elegíveis
   */
  public List<ClientResponseDTO> getSpecificEligibilityClients() {
    LocalDate now = LocalDate.now();
    LocalDate maxBirthDate = now.minusYears(23);
    LocalDate minBirthDate = now.minusYears(49);

    List<Client> clients = clientRepository.findEligibleClientsByAgeAndIncomeAndCreditType(minBirthDate,
        maxBirthDate, CreditType.FIXED_RATE, 5000.0, 15000.0);

    return clients.stream()
        .map(ClientMapper::toResponseDTO)
        .toList();
  }
}
