package br.com.neurotech.challenge.mapper;

import br.com.neurotech.challenge.dto.request.ClientRequestDTO;
import br.com.neurotech.challenge.dto.response.ClientResponseDTO;
import br.com.neurotech.challenge.entity.Client;
import br.com.neurotech.challenge.entity.CreditType;

public class ClientMapper {
  private ClientMapper() {
  }

  public static ClientRequestDTO toRequestDTO(Client client) {
    if (client == null) {
      return null;
    }

    ClientRequestDTO dto = new ClientRequestDTO();
    dto.setName(client.getName());
    dto.setIncome(client.getIncome());
    dto.setBirthDate(client.getBirthDate());

    return dto;
  }

  public static ClientResponseDTO toResponseDTO(Client client) {
    if (client == null) {
      return null;
    }

    ClientResponseDTO dto = new ClientResponseDTO();
    dto.setName(client.getName());
    dto.setIncome(client.getIncome());
    dto.setAge(client.getAge());

    return dto;
  }

  public static Client toEntity(ClientRequestDTO dto) {
    if (dto == null) {
      return null;
    }

    Client client = new Client();
    client.setName(dto.getName());
    client.setBirthDate(dto.getBirthDate());
    client.setIncome(dto.getIncome());
    client.setCreditType(CreditType.getClientCreditType(client));

    return client;
  }
}
