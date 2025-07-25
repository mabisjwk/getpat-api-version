package com.example.getpat_api_version.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.getpat_api_version.models.CadastroEquipamento;
import com.example.getpat_api_version.services.CadastroEquipamentoService;

@RestController
@RequestMapping("/buscar-equipamento")
public class BuscarEquipamentoController {
    @Autowired
    private CadastroEquipamentoService cadastroEquipamentoService;

    @GetMapping
    public ResponseEntity<List<CadastroEquipamento>> filtrar(@RequestParam Map<String, String> filtros) {
        if (filtros == null || filtros.isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }
        List<CadastroEquipamento> resultado = cadastroEquipamentoService.filtrarEquipamentos(filtros);
        return ResponseEntity.ok(resultado);
    }

}
