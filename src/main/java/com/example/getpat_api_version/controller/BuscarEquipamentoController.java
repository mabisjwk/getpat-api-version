package com.example.getpat_api_version.controller;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
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

}
