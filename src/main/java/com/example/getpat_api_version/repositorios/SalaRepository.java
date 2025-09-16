package com.example.getpat_api_version.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.getpat_api_version.models.CadastroSala;

@Repository
public interface SalaRepository extends JpaRepository<CadastroSala, Long> {
    @Query("SELECT s FROM CadastroSala s WHERE LOWER(REPLACE(s.nomeSala, ' ', '')) LIKE LOWER(CONCAT('%', REPLACE(:nome, ' ', ''), '%'))")
    List<CadastroSala> buscarPorNomeIgnorandoEspacos(@Param("nome") String nome);

    List<CadastroSala> findByNomeSalaContainingIgnoreCase(String nomeSala);
}