package br.edu.ifrs.miguelzk.application.service;

import br.edu.ifrs.miguelzk.application.dto.AnimalResponseDTO;
import br.edu.ifrs.miguelzk.application.dto.AtendimentoResponseDTO;
import br.edu.ifrs.miguelzk.application.dto.MedVetResponseDTO;
import br.edu.ifrs.miguelzk.application.dto.UsuarioResponseDTO;
import br.edu.ifrs.miguelzk.domain.entities.Atendimento;
import org.modelmapper.ModelMapper;

import java.util.Set;
import java.util.stream.Collectors;

public class ConverteEntityParaDTO {

    private final ModelMapper modelMapper;

    public ConverteEntityParaDTO(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public AtendimentoResponseDTO atendimentoParaDTO(Atendimento atendimento) {
        AtendimentoResponseDTO atendimentoResponseDTO = modelMapper
                .map(atendimento, AtendimentoResponseDTO.class);
        atendimentoResponseDTO.setUsuariosDTO(atendimento.getUsuarios().stream()
                .map(u -> modelMapper.map(u, UsuarioResponseDTO.class))
                .collect(Collectors.toSet()));
        atendimentoResponseDTO.setMedVetsDTO(atendimento.getMedVets().stream()
                .map(u -> modelMapper.map(u, MedVetResponseDTO.class))
                .collect(Collectors.toSet()));
        return atendimentoResponseDTO;
    }

    public Set<AtendimentoResponseDTO> atendimentosParaDTO(Set<Atendimento> atendimentos) {

        return atendimentos.stream()
                .map(atendimento -> AtendimentoResponseDTO.builder()
                        .id(atendimento.getIdAtendimento())
                        .animal(modelMapper.map(atendimento.getAnimal(), AnimalResponseDTO.class))
                        .dataAtendimento(atendimento.getDataAtendimento())
                        .relatoTutor(atendimento.getRelatoTutor())
                        .exameClinico(atendimento.getExameClinico())
                        .avaliacaoExames(atendimento.getAvaliacaoExames())
                        .diagnPresuntivo(atendimento.getDiagnPresuntivo())
                        .diagnConclusivo(atendimento.getDiagnConclusivo())
                        .procedimentoRealizado(atendimento.getProcedimentoRealizado())
                        .tratamentoInstituido(atendimento.getTratamentoInstituido())
                        .observarProxConsulta(atendimento.getObservarProxConsulta())
                        .usuariosDTO(atendimento.getUsuarios().stream().map(u -> modelMapper.map(u, UsuarioResponseDTO.class))
                                .collect(Collectors.toSet()))
                        .medVetsDTO(atendimento.getMedVets().stream().map(m -> modelMapper.map(m, MedVetResponseDTO.class))
                                .collect(Collectors.toSet()))
                        .build())
                .collect(Collectors.toSet());
    }

}
