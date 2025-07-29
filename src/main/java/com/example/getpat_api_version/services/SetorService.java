package com.example.getpat_api_version.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.getpat_api_version.dtos.SetorDto;
import com.example.getpat_api_version.models.CadastroSetor;
import com.example.getpat_api_version.repositorios.SetorRepository;

@Service
public class SetorService {

    @Autowired
    private SetorRepository setorRepository;
    
    public CadastroSetor criarSetor(SetorDto dto) {
        CadastroSetor setor = new CadastroSetor();
        setor.setNomeSetor(dto.getNomeSetor());
        setor.setAndar(dto.getAndar());

        return setorRepository.save(setor);
    }

    public List<SetorDto> listarSetores() {
        List<CadastroSetor> setores = setorRepository.findAll();
        return setores.stream().map(SetorDto::new).toList();
    }

    public List<SetorDto> buscarSetorPorNome(String nomeSetor) {
        return setorRepository.findByNomeSetor(nomeSetor)
            .map(setor -> List.of(new SetorDto(setor)))
            .orElse(List.of());
    }

    public List<SetorDto> buscarSetorPorAndar(String andar) {
        return setorRepository.findByAndar(andar)
            .map(setor -> List.of(new SetorDto(setor)))
            .orElse(List.of());
    }

    public SetorDto atualizarSetor(Long id, SetorDto request) {
        CadastroSetor setor = setorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Setor não encontrado"));

        setor.setNomeSetor(request.getNomeSetor());
        setor.setAndar(request.getAndar());

        setorRepository.save(setor);
        return new SetorDto(setor);
    }

    public boolean deletarSetor(Long id) {
        Optional<CadastroSetor> setorOpt = setorRepository.findById(id);
        if (setorOpt.isPresent()) {
            setorRepository.delete(setorOpt.get());
            return true;
        }
        return false;
    }
}