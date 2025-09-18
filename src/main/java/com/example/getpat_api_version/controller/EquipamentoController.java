package com.example.getpat_api_version.controller;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.example.getpat_api_version.dtos.EquipamentoDto;
import com.example.getpat_api_version.models.CadastroEquipamento;
import com.example.getpat_api_version.services.EquipamentoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/equipamento")
public class EquipamentoController {
    @Autowired //
    private EquipamentoService cadastroEquipamentoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<CadastroEquipamento> cadastrarEquipamento(@RequestBody @Valid EquipamentoDto dto) {
        CadastroEquipamento equipamento = cadastroEquipamentoService.criarEquipamento(dto);
        return ResponseEntity.ok(equipamento);
    }//

    //gerar um get pra retornar os tipos de estados de conservação futuramente

    @GetMapping("/buscar")
    public ResponseEntity<List<CadastroEquipamento>> filtrar(@RequestParam Map<String, String> filtros) {
        if (filtros == null || filtros.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        }

        Map<String, String> filtrosDecodificados = new HashMap<>();
        for (Map.Entry<String, String> entry : filtros.entrySet()) {
            String chave = entry.getKey();
            String valor = URLDecoder.decode(entry.getValue(), StandardCharsets.UTF_8);
            valor = valor.toLowerCase();

            if(!valor.isEmpty()){
                filtrosDecodificados.put(chave, valor);
            }
        }

        List<CadastroEquipamento> resultado = cadastroEquipamentoService.filtrarEquipamentos(filtrosDecodificados);
        return ResponseEntity.ok(resultado);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarEquipamento(@PathVariable Long id) {
        boolean deletado = cadastroEquipamentoService.deletarEquipamento(id);
        if (deletado) {
            return ResponseEntity.ok("Equipamento com ID " + id + " deletado com sucesso.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Equipamento com ID " + id + " não encontrado.");
        
    }

    @DeleteMapping("/deletarPorPatrimonio/{numeroPat}")
    public ResponseEntity<String> deletarPorNumeroPat(@PathVariable String numeroPat) {
        boolean deletado = cadastroEquipamentoService.deletarPorNumeroPatrimonio(numeroPat);
        if (deletado) {
            return ResponseEntity.ok("Equipamento com patrimônio " + numeroPat + " deletado com sucesso.");
        } else {
            return ResponseEntity.status(404).body("Equipamento com patrimônio " + numeroPat + " não encontrado.");
        }
    } //isso realmente faz sentido? até pq um dispositivo q vai pra baixa permanece existente porem somente nao mais em uso
    // se precisar verificar esse patrimonio ent preciso manter salvo, este caralho n faz sentido no fim...
    // o delete de editar pelo id faz sentido se eu precisar editar algo mas ao inves de editar informacao por informacao eu so apagar e refazer do 0
    //vou excluir dps e criar uma especie de filtro por status - bagulho la do enum



    @DeleteMapping("/deletarPorNumSerie/{numeroSerie}")
    public ResponseEntity<String> deletarPorNumeroSerie(@PathVariable String numeroSerie) {
        boolean deletado = cadastroEquipamentoService.deletarPorNumeroSerie(numeroSerie);
        if (deletado) {
            return ResponseEntity.ok("Equipamento com número de série " + numeroSerie + " deletado com sucesso.");
        } else {
            return ResponseEntity.status(404).body("Equipamento com número de série " + numeroSerie + " não encontrado.");
        }
    }//excluir isso aq tbm 

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarEquipamento(@PathVariable Long id, @RequestBody @Valid EquipamentoDto request) {
        try {
            EquipamentoDto response = cadastroEquipamentoService.atualizarEquipamento(id, request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Equipamento com ID " + id + " não encontrado.");
        }
    }
}


//manter apenas um controlador (ou n)