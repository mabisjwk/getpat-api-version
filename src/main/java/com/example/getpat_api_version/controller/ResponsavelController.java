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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.getpat_api_version.dtos.ResponsavelDto;
import com.example.getpat_api_version.models.CadastroResponsavel;
import com.example.getpat_api_version.services.ResponsavelService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/responsavel")
public class ResponsavelController {
    
    @Autowired
    private ResponsavelService responsavelService;

    @PostMapping("/cadastrar")
    public ResponseEntity<ResponsavelDto> cadastrarResponsavel(@RequestBody @Valid ResponsavelDto dto) {
        CadastroResponsavel responsavel = responsavelService.criarResponsavel(dto);
        return ResponseEntity.ok(new ResponsavelDto(responsavel));
    }

    @GetMapping("/buscar")
    public List<ResponsavelDto> buscarResponsavel(@RequestParam String nome) {
        return responsavelService.buscarResponsavelPorNome(nome);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<ResponsavelDto> editarResponsavel(@PathVariable Long id, @RequestBody @Valid ResponsavelDto dto) {
        CadastroResponsavel responsavel = responsavelService.editarResponsavel(id, dto);
        return ResponseEntity.ok(new ResponsavelDto(responsavel));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarResponsavel(@PathVariable Long id) {
        boolean deletado = responsavelService.deletarResponsavel(id);
        if (deletado) {
            return ResponseEntity.ok("Responsável com ID " + id + " deletado com sucesso.");
        } else {
            return ResponseEntity.badRequest().body("Erro ao deletar responsável com ID " + id + ".");
        }
    }
    
}
