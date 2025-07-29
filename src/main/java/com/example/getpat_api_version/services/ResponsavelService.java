package com.example.getpat_api_version.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.getpat_api_version.dtos.ResponsavelDto;
import com.example.getpat_api_version.models.CadastroResponsavel;
import com.example.getpat_api_version.repositorios.ResponsavelRepository;

@Service
public class ResponsavelService {

    @Autowired
    private ResponsavelRepository responsavelRepository;

    public CadastroResponsavel criarResponsavel(ResponsavelDto dto) {
        CadastroResponsavel responsavel = new CadastroResponsavel();
        responsavel.setNome(dto.getNome());
        // Adicione outros campos caso existam, como cargo, sala etc.
        return responsavelRepository.save(responsavel);
    }

    public List<ResponsavelDto> buscarResponsavelPorNome(String nome) {
        return responsavelRepository.findByNomeContainingIgnoreCase(nome)
            .stream()
            .map(ResponsavelDto::new)
            .toList();
    }

    public CadastroResponsavel editarResponsavel(Long id, ResponsavelDto dto) {
        CadastroResponsavel responsavel = responsavelRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Responsável não encontrado com ID: " + id));
        
        responsavel.setNome(dto.getNome());
        // Atualize outros campos conforme necessário
        return responsavelRepository.save(responsavel);
    }

    public boolean deletarResponsavel(Long id) {
        return responsavelRepository.findById(id)
            .map(responsavel -> {
                responsavelRepository.delete(responsavel);
                return true;
            })
            .orElse(false);
    }
}