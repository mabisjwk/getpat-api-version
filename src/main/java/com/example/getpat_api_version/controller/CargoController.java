package com.example.getpat_api_version.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.getpat_api_version.dtos.CargoDto;
import com.example.getpat_api_version.models.CadastroCargo;
import com.example.getpat_api_version.services.CargoService;
import org.springframework.util.StringUtils;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/cargo")
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @PostMapping
    public ResponseEntity<CargoDto> cadastrarCargo(@RequestBody @Valid CargoDto dto) {
        CadastroCargo cargo = cargoService.criarCargo(dto);
        return ResponseEntity.ok(new CargoDto(cargo));
    }
    //

    @GetMapping
    public ResponseEntity<List<CargoDto>> listarCargos() {
        return ResponseEntity.ok(cargoService.listarCargos());
    }//

    @GetMapping("/buscar/{nomeCargo}")
    public ResponseEntity<List<CargoDto>> buscarCargoPorNome(@PathVariable String nomeCargo) {
        if(!StringUtils.hasText(nomeCargo)){
            return ResponseEntity.badRequest().build();
        }

        List<CargoDto> cargos = cargoService.buscarCargoPorNome(nomeCargo);
        return ResponseEntity.ok(cargos);
    }
        
        @PutMapping("/editar/{id}")
    public ResponseEntity<CargoDto> editarCargo(@PathVariable Long id, @RequestBody @Valid CargoDto dto) {
        return ResponseEntity.ok(cargoService.atualizarCargo(id, dto));
    }//

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarCargo(@PathVariable Long id) {
        boolean deletado = cargoService.deletarCargo(id);
        if (deletado) {
            return ResponseEntity.ok("Cargo com ID " + id + " deletado com sucesso.");
        } else {
            return ResponseEntity.badRequest().body("Erro ao deletar cargo com ID " + id + ".");
        }
    }
}


// StringUtils.hasText(nomeCargo): verifica se o valor não é nulo nem vazio nem só espaços
//criar execeçoes 
//verificar os retornos de erro ;-;