package com.example.getpat_api_version.repositorios;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.getpat_api_version.models.CadastroEquipamento;

@Repository
public interface EquipamentoRepository extends JpaRepository<CadastroEquipamento, Long>, JpaSpecificationExecutor<CadastroEquipamento> {
    Optional<CadastroEquipamento> findByNumeroPat(String numeroPat);
    Optional<CadastroEquipamento> findByNumeroSerie(String numeroSerie);

}