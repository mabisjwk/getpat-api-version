package com.example.getpat_api_version.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.example.getpat_api_version.dtos.SalaDto;
import com.example.getpat_api_version.models.CadastroSala;
import com.example.getpat_api_version.models.CadastroSetor;
import com.example.getpat_api_version.repositorios.SalaRepository;
import com.example.getpat_api_version.repositorios.SetorRepository;

@Service
public class SalaService {
    
    private final SalaRepository salaRepository;
    private final SetorRepository setorRepository;
    
    public SalaService(SalaRepository salaRepository, SetorRepository setorRepository) {
        this.salaRepository = salaRepository;
        this.setorRepository = setorRepository;
    }

    public SalaDto criarSala(SalaDto dto) {
        CadastroSetor setor = setorRepository.findById(dto.getSetorId())
            .orElseThrow(() -> new RuntimeException("Setor não encontrado"));

        CadastroSala sala = new CadastroSala();
        sala.setNomeSala(dto.getNomeSala());
        sala.setSetor(setor);

        CadastroSala salva = salaRepository.save(sala);
        return new SalaDto(salva);
    }

    public List<SalaDto> listarSalas() {
        return salaRepository.findAll().stream().map(SalaDto::new).collect(Collectors.toList());
    }

    public List<SalaDto> buscarSalaPorNome(String nomeSala) {
    if (nomeSala == null || nomeSala.trim().isEmpty()) {
        return listarSalas();
    }

    String nomeTratado = nomeSala.replaceAll("\\s+", "");
    return salaRepository.buscarPorNomeIgnorandoEspacos(nomeTratado)
        .stream()
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



//https://medium.com/@jeffersonfabriciodev/o-uso-do-autowired-no-spring-%C3%A9-uma-m%C3%A1-pratica-a23378be3c27

