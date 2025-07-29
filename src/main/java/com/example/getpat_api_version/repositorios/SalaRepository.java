package com.example.getpat_api_version.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.getpat_api_version.models.CadastroSala;

@Repository
public interface SalaRepository extends JpaRepository<CadastroSala, Long> {
    List<CadastroSala> findByNomeSalaContainingIgnoreCase(String nomeSala);
}