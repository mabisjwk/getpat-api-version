package com.example.getpat_api_version.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.getpat_api_version.dtos.CadastroEquipamentoDto;
import com.example.getpat_api_version.services.CadastroEquipamentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/editar-equipamento")
public class EditarEquipamentoController {
    @Autowired
    private CadastroEquipamentoService cadastroEquipamentoService;

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarEquipamento(@PathVariable Long id, @RequestBody @Valid CadastroEquipamentoDto request) {
        try {
            CadastroEquipamentoDto response = cadastroEquipamentoService.atualizarEquipamento(id, request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Equipamento com ID " + id + " não encontrado.");
        }
    }
}
