package com.example.getpat_api_version.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.getpat_api_version.dtos.CadastroEquipamentoDto;
import com.example.getpat_api_version.models.CadastroEquipamento;
import com.example.getpat_api_version.services.CadastroEquipamentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cadastro-equipamento")
public class CadastroEquipamentoController {
    @Autowired
    private CadastroEquipamentoService cadastroEquipamentoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<CadastroEquipamento> cadastrarEquipamento(@RequestBody @Valid CadastroEquipamentoDto dto) {
        CadastroEquipamento equipamento = cadastroEquipamentoService.criarEquipamento(dto);
        return ResponseEntity.ok(equipamento);
    }
}
