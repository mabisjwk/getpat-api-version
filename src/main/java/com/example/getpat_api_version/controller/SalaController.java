package com.example.getpat_api_version.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.getpat_api_version.dtos.SalaDto;
import com.example.getpat_api_version.services.SalaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/sala")
public class SalaController {

    private final SalaService salaService;

    public SalaController(SalaService salaService) {
        this.salaService = salaService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarSala(@RequestBody @Valid SalaDto dto) {
        SalaDto response = salaService.criarSala(dto);
        return ResponseEntity.ok(response);
    }

    //so mandar o /buscar?nome= vazio q ele retorna tudo
    @GetMapping("/buscar")
    public ResponseEntity<List<SalaDto>> buscarPorNome(@RequestParam String nome) {
        List<SalaDto> salas = salaService.buscarSalaPorNome(nome);
        return ResponseEntity.ok(salas);
        //buscar?nome=nome%20
        //%20 para ignorar o espaço
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

//jesus