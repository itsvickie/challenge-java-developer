package br.com.neurotech.challenge.entity;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(name = "client", uniqueConstraints = {
    @UniqueConstraint(columnNames = { "name", "birthDate" })
})
public class Client {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private LocalDate birthDate;

  private Double income;

  private CreditType creditType;

  @Transient
  public int getAge() {
    return Period.between(birthDate, LocalDate.now()).getYears();
  }
}
