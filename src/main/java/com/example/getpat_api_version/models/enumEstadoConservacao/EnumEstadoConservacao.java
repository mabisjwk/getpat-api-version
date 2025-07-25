package com.example.getpat_api_version.models.enumEstadoConservacao;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EnumEstadoConservacao {
    EmUso("Em Uso"),
    Depreciado("Depreciado"),
    Manutencao("Manutenção"),
    Almoxarifado("Almoxarifado");

    private final String descricao;

    EnumEstadoConservacao(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static EnumEstadoConservacao fromJson(String valor) {
        for (EnumEstadoConservacao estado : EnumEstadoConservacao.values()) {
            if (estado.name().equalsIgnoreCase(valor.trim()) || 
                estado.descricao.equalsIgnoreCase(valor.trim())) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Estado de conservação inválido: " + valor);
    }
}