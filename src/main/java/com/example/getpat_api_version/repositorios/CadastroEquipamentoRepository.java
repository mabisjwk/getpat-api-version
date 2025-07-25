package com.example.getpat_api_version.repositorios;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.example.getpat_api_version.models.CadastroEquipamento;

public interface CadastroEquipamentoRepository extends JpaRepository<CadastroEquipamento, Long>, JpaSpecificationExecutor<CadastroEquipamento> {
    Optional<CadastroEquipamento> findByNumeroPat(Integer numeroPat);
    Optional<CadastroEquipamento> findByNumeroSerie(String numeroSerie);

}