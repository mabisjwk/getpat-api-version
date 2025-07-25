package com.example.getpat_api_version.services;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.example.getpat_api_version.dtos.CadastroEquipamentoDto;
import com.example.getpat_api_version.models.CadastroEquipamento;
import com.example.getpat_api_version.models.enumEstadoConservacao.EnumEstadoConservacao;
import com.example.getpat_api_version.repositorios.CadastroEquipamentoRepository;

@Service
public class CadastroEquipamentoService {
    
    @Autowired
    private CadastroEquipamentoRepository cadastroEquipamentoRepository;

    
    public CadastroEquipamento criarEquipamento(CadastroEquipamentoDto dto) {
        
        CadastroEquipamento equipamento = new CadastroEquipamento();
        equipamento.setNumeroPat(dto.getNumeroPatrimonio());
        equipamento.setTipo(dto.getTipo());
        equipamento.setModelo(dto.getModelo());
        equipamento.setMarca(dto.getMarca());
        equipamento.setNumeroSerie(dto.getNumeroSerie());
        equipamento.setNotaFiscal(dto.getNotaFiscal());
        equipamento.setDataAquisicao(dto.getDataAquisicao());
        equipamento.setValorAquisicao(dto.getValorAquisicao());
        equipamento.setTempoGarantia(dto.getTempoGarantia());
        equipamento.setSetor(dto.getSetor());
        equipamento.setSala(dto.getSala());
        equipamento.setResponsavel(dto.getResponsavel());
        equipamento.setEstadoConservacao(dto.getEstadoConservacao());

        return cadastroEquipamentoRepository.save(equipamento);
    }

    public CadastroEquipamentoDto atualizarEquipamento(Long id, CadastroEquipamentoDto request) {
        CadastroEquipamento equipamento = cadastroEquipamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipamento não encontrado"));

        equipamento.setNumeroPat(request.getNumeroPatrimonio());
        equipamento.setTipo(request.getTipo());
        equipamento.setModelo(request.getModelo());
        equipamento.setMarca(request.getMarca());
        equipamento.setNumeroSerie(request.getNumeroSerie());
        equipamento.setNotaFiscal(request.getNotaFiscal());
        equipamento.setDataAquisicao(request.getDataAquisicao());
        equipamento.setValorAquisicao(request.getValorAquisicao());
        equipamento.setTempoGarantia(request.getTempoGarantia());
        equipamento.setSetor(request.getSetor());
        equipamento.setSala(request.getSala());
        equipamento.setResponsavel(request.getResponsavel());
        equipamento.setEstadoConservacao(request.getEstadoConservacao());

        cadastroEquipamentoRepository.save(equipamento);
        
        return new CadastroEquipamentoDto(equipamento);
    }

    public List<CadastroEquipamento> filtrarEquipamentos(Map<String, String> filtros) {
        Specification<CadastroEquipamento> spec = Specification.unrestricted();

        if (filtros.containsKey("tipo")) {
            String tipo = filtros.get("tipo").toLowerCase();
            spec = spec.and((root, query, cb) ->
                cb.like(cb.lower(root.get("tipo")), "%" + tipo + "%")
            );
        }

        if (filtros.containsKey("marca")) {
            String marca = filtros.get("marca").toLowerCase();
            spec = spec.and((root, query, cb) ->
                cb.like(cb.lower(root.get("marca")), "%" + marca + "%")
            );
        }

        if (filtros.containsKey("modelo")) {
            String modelo = filtros.get("modelo").toLowerCase();
            spec = spec.and((root, query, cb) ->
                cb.like(cb.lower(root.get("modelo")), "%" + modelo + "%")
            );
        }

        if (filtros.containsKey("numeroSerie")) {
            String numeroSerie = filtros.get("numeroSerie").toLowerCase();
            spec = spec.and((root, query, cb) ->
                cb.like(cb.lower(root.get("numeroSerie")), "%" + numeroSerie + "%")
            );
        }

        if (filtros.containsKey("responsavel")) {
            String responsavel = filtros.get("responsavel").toLowerCase();
            spec = spec.and((root, query, cb) ->
                cb.like(cb.lower(root.get("responsavel")), "%" + responsavel + "%")
            );
        }

        if (filtros.containsKey("setor")) {
            String setor = filtros.get("setor").toLowerCase();
            spec = spec.and((root, query, cb) ->
                cb.like(cb.lower(root.get("setor")), "%" + setor + "%")
            );
        }

        if (filtros.containsKey("sala")) {
            String sala = filtros.get("sala").toLowerCase();
            spec = spec.and((root, query, cb) ->
                cb.like(cb.lower(root.get("sala")), "%" + sala + "%")
            );
        }

        // Campos que ainda podem permanecer com comparação exata:

        if (filtros.containsKey("numeroPatrimonio")) {
            try {
                Integer numeroPat = Integer.parseInt(filtros.get("numeroPatrimonio"));
                spec = spec.and((root, query, cb) -> cb.equal(root.get("numeroPat"), numeroPat));
            } catch (NumberFormatException ignored) {}
        }

        if (filtros.containsKey("notaFiscal")) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("notaFiscal"), filtros.get("notaFiscal")));
        }

        if (filtros.containsKey("dataAquisicao")) {
            try {
                LocalDate data = LocalDate.parse(filtros.get("dataAquisicao"));
                spec = spec.and((root, query, cb) -> cb.equal(root.get("dataAquisicao"), data));
            } catch (Exception ignored) {}
        }

        if (filtros.containsKey("dataCriacao")) {
            try {
                LocalDate data = LocalDate.parse(filtros.get("dataCriacao"));
                spec = spec.and((root, query, cb) -> cb.equal(root.get("dataCriacao"), data));
            } catch (Exception ignored) {}
        }

        if (filtros.containsKey("valorAquisicao")) {
            try {
                BigDecimal valor = new BigDecimal(filtros.get("valorAquisicao"));
                spec = spec.and((root, query, cb) -> cb.equal(root.get("valorAquisicao"), valor));
            } catch (Exception ignored) {}
        }

        if (filtros.containsKey("tempoGarantia")) {
            try {
                Period tempo = Period.parse(filtros.get("tempoGarantia"));
                spec = spec.and((root, query, cb) -> cb.equal(root.get("tempoGarantia"), tempo));
            } catch (Exception ignored) {}
        }

        if (filtros.containsKey("estadoConservacao")) {
            try {
                EnumEstadoConservacao estado = EnumEstadoConservacao.valueOf(filtros.get("estadoConservacao"));
                spec = spec.and((root, query, cb) -> cb.equal(root.get("estadoConservacao"), estado));
            } catch (Exception ignored) {}
        }

        return cadastroEquipamentoRepository.findAll(spec);
    }

    public boolean deletarPorNumeroPatrimonio(int numeroPat) {
    Optional<CadastroEquipamento> equipamento = cadastroEquipamentoRepository.findByNumeroPat(numeroPat);
        if (equipamento.isPresent()) {
            cadastroEquipamentoRepository.delete(equipamento.get());
            return true;
        }
        return false;
    }

    public boolean deletarPorNumeroSerie(String numeroSerie) {
        Optional<CadastroEquipamento> equipamento = cadastroEquipamentoRepository.findByNumeroSerie(numeroSerie);
        if (equipamento.isPresent()) {
            cadastroEquipamentoRepository.delete(equipamento.get());
            return true;
        }
        return false;
    }
}
