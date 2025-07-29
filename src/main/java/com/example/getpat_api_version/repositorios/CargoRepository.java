package com.example.getpat_api_version.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.getpat_api_version.models.CadastroCargo;

@Repository
public interface CargoRepository extends JpaRepository<CadastroCargo, Long> {
    
    // Busca cargos cujo nome contenha parte da palavra, ignorando maiúsculas/minúsculas
    List<CadastroCargo> findByNomeCargoContainingIgnoreCase(String nomeCargo);
}