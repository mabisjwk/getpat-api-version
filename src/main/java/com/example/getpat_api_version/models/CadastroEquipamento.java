package com.example.getpat_api_version.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import com.example.getpat_api_version.models.enumEstadoConservacao.EnumEstadoConservacao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "cadastro_equipamento")

@Getter
@Setter


@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CadastroEquipamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(1)
    @Column(name = "numeroPat", nullable = false, unique = true)
    private Integer numeroPat;

    private LocalDate dataCriacao;
    @PrePersist //garantir que a dataCriacao seja preenchida com a data atual
    protected void onCreate() {
        this.dataCriacao = LocalDate.now();
    }

    @NotBlank
    @Column(name = "tipo", nullable = false)
    private String tipo;

    @NotBlank
    @Column(name = "marca", nullable = false)
    private String marca;

    @NotBlank
    @Column(name = "modelo", nullable = false)
    private String modelo;

    @NotBlank
    @Column(name = "numeroSerie", nullable = false, unique = true)
    private String numeroSerie;
    
    @NotBlank
    @Column(name = "notaFiscal", nullable = false)
    private String notaFiscal;

    @NotNull
    @Column(name = "data_aquisicao", nullable = false)
    private LocalDate dataAquisicao;

    @NotNull
    @Column(name = "valor_aquisicao", nullable = false)
    private BigDecimal valorAquisicao;

    @NotNull
    @Column(name = "tempoGarantia", nullable = false)
    private Period tempoGarantia;

    @NotBlank
    @Column(name = "setor", nullable = false)
    private String setor;

    @NotBlank
    @Column(name = "sala", nullable = false)
    private String sala;

    @Column(name = "responsavel", nullable = true)
    private String responsavel;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_conservacao", nullable = false)
    private EnumEstadoConservacao estadoConservacao;

}


