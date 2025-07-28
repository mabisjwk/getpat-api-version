package com.example.getpat_api_version.dtos;

import com.example.getpat_api_version.models.CadastroSala;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@NoArgsConstructor
public class SalaDto {

    private Long id;
    
    @NotBlank
    private String nomeSala;

    public SalaDto(CadastroSala sala) {
        this.id = sala.getId();
        this.nomeSala = sala.getNomeSala();
    }
}
