package com.example.getpat_api_version.controller;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.getpat_api_version.dtos.CadastroEquipamentoDto;
import com.example.getpat_api_version.models.CadastroEquipamento;
import com.example.getpat_api_version.services.CadastroEquipamentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/equipamento")
public class CadastroEquipamentoController {
    @Autowired
    private CadastroEquipamentoService cadastroEquipamentoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<CadastroEquipamento> cadastrarEquipamento(@RequestBody @Valid CadastroEquipamentoDto dto) {
        CadastroEquipamento equipamento = cadastroEquipamentoService.criarEquipamento(dto);
        return ResponseEntity.ok(equipamento);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<CadastroEquipamento>> filtrar(@RequestParam Map<String, String> filtros) {
        if (filtros == null || filtros.isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }

        Map<String, String> filtrosDecodificados = new HashMap<>();
        for (Map.Entry<String, String> entry : filtros.entrySet()) {
            String chave = entry.getKey();
            String valor = URLDecoder.decode(entry.getValue(), StandardCharsets.UTF_8);
            valor = valor.toLowerCase();
            filtrosDecodificados.put(chave, valor);
        }

        List<CadastroEquipamento> resultado = cadastroEquipamentoService.filtrarEquipamentos(filtrosDecodificados);
        return ResponseEntity.ok(resultado);
    }

    @DeleteMapping("/deletarPorPatrimonio/{numeroPat}")
    public ResponseEntity<String> deletarPorNumeroPat(@PathVariable Integer numeroPat) {
        boolean deletado = cadastroEquipamentoService.deletarPorNumeroPatrimonio(numeroPat);
        if (deletado) {
            return ResponseEntity.ok("Equipamento com patrimônio " + numeroPat + " deletado com sucesso.");
        } else {
            return ResponseEntity.status(404).body("Equipamento com patrimônio " + numeroPat + " não encontrado.");
        }
    }

    @DeleteMapping("/deletarPorNumSerie/{numeroSerie}")
    public ResponseEntity<String> deletarPorNumeroSerie(@PathVariable String numeroSerie) {
        boolean deletado = cadastroEquipamentoService.deletarPorNumeroSerie(numeroSerie);
        if (deletado) {
            return ResponseEntity.ok("Equipamento com número de série " + numeroSerie + " deletado com sucesso.");
        } else {
            return ResponseEntity.status(404).body("Equipamento com número de série " + numeroSerie + " não encontrado.");
        }
    }
}


//manter apenas um controlador