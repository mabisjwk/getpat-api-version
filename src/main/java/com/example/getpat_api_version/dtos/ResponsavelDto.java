package com.example.getpat_api_version.dtos;

import com.example.getpat_api_version.models.CadastroResponsavel;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ResponsavelDto {

    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String cargo;

    @NotBlank
    private String email;

    public ResponsavelDto(CadastroResponsavel responsavel) {
        this.id = responsavel.getId();
        this.nome = responsavel.getNome();
        this.email = responsavel.getEmail();
        this.cargo = responsavel.getCargo().getNomeCargo();
    }
    
}
