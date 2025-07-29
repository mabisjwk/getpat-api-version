package com.example.getpat_api_version.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.getpat_api_version.dtos.SalaDto;
import com.example.getpat_api_version.models.CadastroSala;
import com.example.getpat_api_version.repositorios.SalaRepository;

@Service
public class SalaService {
    
    @Autowired
    private SalaRepository salaRepository;

    public SalaDto criarSala(SalaDto dto) {
        CadastroSala sala = new CadastroSala();
        sala.setNomeSala(dto.getNomeSala());
        CadastroSala salva = salaRepository.save(sala);
        return new SalaDto(salva);
    }

    public List<SalaDto> listarSalas() {
        return salaRepository.findAll().stream().map(SalaDto::new).collect(Collectors.toList());
    }

    public List<SalaDto> buscarSalaPorNome(String nomeSala) {
        return salaRepository.findByNomeSalaContainingIgnoreCase(nomeSala).stream()
                .map(SalaDto::new)
                .collect(Collectors.toList());
    }

    public SalaDto atualizarSala(Long id, SalaDto request) {
        CadastroSala sala = salaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sala não encontrada com ID: " + id));
        
        sala.setNomeSala(request.getNomeSala());
        salaRepository.save(sala);
        
        return new SalaDto(sala);
    }

    public boolean deletarSala(Long id) {
        return salaRepository.findById(id).map(sala -> {
                salaRepository.delete(sala);
                return true;
            })
            .orElse(false);
    }
}
