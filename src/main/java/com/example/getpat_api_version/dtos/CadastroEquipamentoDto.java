package com.example.getpat_api_version.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

import com.example.getpat_api_version.models.CadastroEquipamento;
import com.example.getpat_api_version.models.enumEstadoConservacao.EnumEstadoConservacao;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CadastroEquipamentoDto {
    @NotNull(message = "O número de patrimônio não pode ser nulo")
    @Min(1)
    private int numeroPatrimonio;

    @NotBlank(message = "O tipo não pode ser vazio")
    private String tipo;

    @NotBlank(message = "A marca não pode ser vazia")
    private String marca;

    @NotBlank(message = "O modelo não pode ser vazio")
    private String modelo;

    @NotBlank(message = "O número de série não pode ser vazio")
    private String numeroSerie;

    private EnumEstadoConservacao estadoConservacao;

    @NotBlank(message = "A nota fiscal não pode ser vazia")
    private String notaFiscal;

    @NotNull(message = "A data de aquisição não pode ser nula")
    private LocalDate dataAquisicao;

    @NotNull(message = "O valor de aquisição não pode ser nulo")
    @Min(value = 0, message = "O valor de aquisição não pode ser negativo")
    private BigDecimal valorAquisicao;

    @NotNull(message = "O tempo de garantia não pode ser nulo")
    private Period tempoGarantia;

    @NotBlank(message = "O setor não pode ser vazio")
    private String setor;

    @NotBlank(message = "A sala não pode ser vazia")
    private String sala;

    private String responsavel;

    public CadastroEquipamentoDto(CadastroEquipamento equipamento) {
        this.numeroPatrimonio = equipamento.getNumeroPat();
        this.tipo = equipamento.getTipo();
        this.modelo = equipamento.getModelo();
        this.marca = equipamento.getMarca();
        this.numeroSerie = equipamento.getNumeroSerie();
        this.notaFiscal = equipamento.getNotaFiscal();
        this.dataAquisicao = equipamento.getDataAquisicao();
        this.valorAquisicao = equipamento.getValorAquisicao();
        this.tempoGarantia = equipamento.getTempoGarantia();
        this.setor = equipamento.getSetor();
        this.sala = equipamento.getSala();
        this.responsavel = equipamento.getResponsavel();
        this.estadoConservacao = equipamento.getEstadoConservacao();

    }

    public CadastroEquipamentoDto() {
    // Construtor vazio necessário para deserialização
    }


}
