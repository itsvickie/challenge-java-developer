package br.com.neurotech.challenge.dto.request;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequestDTO {
  @NotBlank()
  @Schema(example = "User name")
  private String name;

  @NotNull()
  @Past()
  @Schema(example = "2001-01-01")
  private LocalDate birthDate;

  @NotNull()
  @Positive()
  @Schema(example = "5000")
  private Double income;
}
