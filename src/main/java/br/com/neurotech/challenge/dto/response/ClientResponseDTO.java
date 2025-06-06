package br.com.neurotech.challenge.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponseDTO {
  @NotBlank()
  private String name;

  @NotNull()
  private int age;

  @NotNull()
  @Positive()
  private Double income;
}
