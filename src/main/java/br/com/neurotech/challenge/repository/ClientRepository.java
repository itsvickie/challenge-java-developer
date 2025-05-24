package br.com.neurotech.challenge.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.neurotech.challenge.entity.Client;
import br.com.neurotech.challenge.entity.CreditType;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
  Client findByNameAndBirthDate(String name, LocalDate birthDate);

  @Query("SELECT c " +
      "FROM Client c " +
      "WHERE c.birthDate BETWEEN :startDate AND :endDate " +
      "AND c.creditType = :creditType " +
      "AND c.income BETWEEN :startIncome AND :endIncome")
  List<Client> findEligibleClientsByAgeAndIncomeAndCreditType(
      @Param("startDate") LocalDate startDate,
      @Param("endDate") LocalDate endDate,
      @Param("creditType") CreditType creditType,
      @Param("startIncome") Double startIncome,
      @Param("endIncome") Double endIncome);
}
