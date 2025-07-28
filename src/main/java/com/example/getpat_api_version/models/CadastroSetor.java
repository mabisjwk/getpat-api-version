package com.example.getpat_api_version.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@NoArgsConstructor

@Entity
@Table(name = "cadastro_setor")
public class CadastroSetor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
        
    @NotBlank
    private String nomeSetor;

    @NotBlank
    private String andar;

    @OneToMany(mappedBy = "setor")
    private List<CadastroSala> sala;

    @ManyToMany(mappedBy = "setores")
    private List<CadastroResponsavel> responsaveis;

}
