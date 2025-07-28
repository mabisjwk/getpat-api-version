package com.example.getpat_api_version.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetorService {

    @Autowired
    private SetorRepository setorRepository;
    
    public cadastroSetor cadastrarSetor(SetorDto dto) {
        CadastroSetor setor = new CadastroSetor();
        setor.setNome(dto.getNome());
        setor.setAndar(dto.getAndar());
        setor.setDescricao(dto.getDescricao());
        
        return setorRepository.save(setor);
    }
}
