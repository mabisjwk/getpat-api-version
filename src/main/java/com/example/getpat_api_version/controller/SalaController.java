package com.example.getpat_api_version.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.getpat_api_version.dtos.SalaDto;
import com.example.getpat_api_version.services.SalaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/sala")
public class SalaController {
    
    @Autowired
    private SalaService salaService;

    @PostMapping("/cadastrar")
    public ResponseEntity<SalaDto> cadastrarSala(@RequestBody @Valid SalaDto dto) {
        SalaDto sala = salaService.criarSala(dto);
        return ResponseEntity.ok(sala);
    }

    @GetMapping("/listar")
    public List<SalaDto> listarSalas() {
        return salaService.listarSalas();
    }

    @GetMapping("/buscar/{nomeSala}")
    public List<SalaDto> buscarSalaPorNome(@PathVariable String nomeSala) {
        return salaService.buscarSalaPorNome(nomeSala);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarSala(@PathVariable Long id, @RequestBody @Valid SalaDto request) {
        try {
            SalaDto response = salaService.atualizarSala(id, request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao editar sala: " + e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarSala(@PathVariable Long id) {
        boolean deletado = salaService.deletarSala(id);
        if (deletado) {
            return ResponseEntity.ok("Sala com ID " + id + " deletada com sucesso.");
        } else {
            return ResponseEntity.badRequest().body("Erro ao deletar sala com ID " + id + ".");
        }
    }

}
