package com.example.getpat_api_version.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.getpat_api_version.models.CadastroResponsavel;

@Repository
public interface ResponsavelRepository extends JpaRepository<CadastroResponsavel, Long> {

    // Busca flexível por nome, ignorando maiúsculas/minúsculas
    List<CadastroResponsavel> findByNomeContainingIgnoreCase(String nome);
}