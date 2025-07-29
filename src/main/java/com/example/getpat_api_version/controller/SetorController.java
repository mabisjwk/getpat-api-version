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
import com.example.getpat_api_version.dtos.SetorDto;
import com.example.getpat_api_version.models.CadastroSetor;
import com.example.getpat_api_version.services.SetorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/setor")

public class SetorController {
    
    @Autowired
    private SetorService setorService;

    @PostMapping("/cadastrar")
    public ResponseEntity<SetorDto> cadastrarSetor(@RequestBody @Valid SetorDto dto) {
        CadastroSetor setor = setorService.criarSetor(dto);
        return ResponseEntity.ok(new SetorDto(setor));
    }

    @GetMapping("/listar")
    public List<SetorDto> listarSetores() {
        return setorService.listarSetores();
    }

    @GetMapping("/buscarSetor/{nomeSetor}")
    public List<SetorDto> buscarSetorPorNome(@PathVariable String nomeSetor) {
        return setorService.buscarSetorPorNome(nomeSetor);
    }

    @GetMapping("buscarAndar/{andar}")
    public List<SetorDto> buscarSetorPorAndar(@PathVariable String andar) {
        return setorService.buscarSetorPorAndar(andar);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarSetor(@PathVariable Long id, @RequestBody @Valid SetorDto request) {
        try {
            SetorDto response = setorService.atualizarSetor(id, request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao editar setor: " + e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarSetor(@PathVariable Long id) {
        boolean deletado = setorService.deletarSetor(id);
        if (deletado) {
            return ResponseEntity.ok("Setor com ID " + id + " deletado com sucesso.");
        } else {
            return ResponseEntity.badRequest().body("Erro ao deletar setor com ID " + id + ".");
        }
    }
}
