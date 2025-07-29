package com.example.getpat_api_version.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.getpat_api_version.models.CadastroSetor;

@Repository
public interface SetorRepository extends JpaRepository<CadastroSetor, Long>, JpaSpecificationExecutor<CadastroSetor> {
    Optional<CadastroSetor> findByNomeSetor(String nomeSetor);
    Optional<CadastroSetor> findByAndar(String andar);
    
}
