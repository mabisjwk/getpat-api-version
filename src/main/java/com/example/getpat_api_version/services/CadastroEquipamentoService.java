package com.example.getpat_api_version.services;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;

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
            spec = spec.and((root, query, cb) -> cb.equal(root.get("tipo"), filtros.get("tipo")));
        }

        if (filtros.containsKey("marca")) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("marca"), filtros.get("marca")));
        }

        if (filtros.containsKey("modelo")) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("modelo"), filtros.get("modelo")));
        }

        if (filtros.containsKey("numeroSerie")) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("numeroSerie"), filtros.get("numeroSerie")));
        }

        if (filtros.containsKey("numeroPatrimonio")) {
            try {
                Integer numeroPat = Integer.parseInt(filtros.get("numeroPatrimonio"));
                spec = spec.and((root, query, cb) -> cb.equal(root.get("numeroPat"), numeroPat));
            } catch (NumberFormatException ignored) {}
        }

        if (filtros.containsKey("setor")) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("setor"), filtros.get("setor")));
        }

        if (filtros.containsKey("sala")) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("sala"), filtros.get("sala")));
        }

        if (filtros.containsKey("responsavel")) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("responsavel"), filtros.get("responsavel")));
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


}
