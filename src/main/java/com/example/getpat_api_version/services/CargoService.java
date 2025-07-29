package com.example.getpat_api_version.services;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.getpat_api_version.dtos.CargoDto;
import com.example.getpat_api_version.models.CadastroCargo;
import com.example.getpat_api_version.repositorios.CargoRepository;

@Service
public class CargoService {
    
    @Autowired
    private CargoRepository cargoRepository;

    public CadastroCargo criarCargo(CargoDto dto) {
        CadastroCargo cargo = new CadastroCargo();
        cargo.setNomeCargo(dto.getNomeCargo());
        return cargoRepository.save(cargo);
    }

    public List<CargoDto> listarCargos() {
        return cargoRepository.findAll().stream().map(CargoDto::new).collect(Collectors.toList());
    }

    public List<CargoDto> buscarCargoPorNome(String nomeCargo) {
        return cargoRepository.findByNomeCargoContainingIgnoreCase(nomeCargo).stream()
                .map(CargoDto::new)
                .collect(Collectors.toList());
    }

    public CargoDto atualizarCargo(Long id, CargoDto request) {
        CadastroCargo cargo = cargoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cargo não encontrado com ID: " + id));
        
        cargo.setNomeCargo(request.getNomeCargo());
        cargoRepository.save(cargo);
        
        return new CargoDto(cargo);
    }

    public boolean deletarCargo(Long id) {
        return cargoRepository.findById(id).map(cargo -> {
                cargoRepository.delete(cargo);
                return true;
            })
            .orElse(false);
    }
}
