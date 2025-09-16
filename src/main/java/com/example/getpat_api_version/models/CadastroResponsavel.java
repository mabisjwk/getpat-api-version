package com.example.getpat_api_version.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "responsavel")
public class CadastroResponsavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    @Column(unique=true)
    private String email;
    
    @ManyToMany
    @JoinTable(
        name = "setor_responsavel",
        joinColumns = @JoinColumn(name = "responsavel_id"),
        inverseJoinColumns = @JoinColumn(name = "setor_id")
    )
    private List<CadastroSetor> setores;

    @ManyToOne
    @JoinColumn(name = "cargo_id", nullable = false)
    private CadastroCargo cargo;
        
}
