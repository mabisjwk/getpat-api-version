package com.example.getpat_api_version.dtos;

import com.example.getpat_api_version.models.CadastroCargo;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class CargoDto {
    
    private Long id;

    @NotBlank
    private String nomeCargo;

    public CargoDto(CadastroCargo cargo) {
        this.id = cargo.getId();
        this.nomeCargo = cargo.getNomeCargo();
    }
}
