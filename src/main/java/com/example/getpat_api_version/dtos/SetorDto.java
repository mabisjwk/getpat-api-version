package com.example.getpat_api_version.dtos;

import com.example.getpat_api_version.models.CadastroSetor;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@NoArgsConstructor

public class SetorDto {

    private Long id;
        
    @NotBlank
    private String nomeSetor;

    @NotBlank
    private String andar;

    public SetorDto(CadastroSetor setor) {
        this.id = setor.getId();
        this.nomeSetor = setor.getNomeSetor();
        this.andar = setor.getAndar();
    }


}
