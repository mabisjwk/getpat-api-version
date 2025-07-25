package com.example.getpat_api_version.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.getpat_api_version.services.CadastroEquipamentoService;


@RestController
@RequestMapping("/deletar-equipamento")
public class DeletarEquipamentoController {

    @Autowired
    private CadastroEquipamentoService cadastroEquipamentoService;

    @DeleteMapping("/deletarPorPatrimonio/{numeroPat}")
    public ResponseEntity<String> deletarPorNumeroPat(@PathVariable int numeroPat) {
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
