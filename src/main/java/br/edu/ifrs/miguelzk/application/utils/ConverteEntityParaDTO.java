package br.edu.ifrs.miguelzk.application.utils;

import br.edu.ifrs.miguelzk.application.dto.*;
import br.edu.ifrs.miguelzk.domain.entities.Atendimento;
import org.modelmapper.ModelMapper;

import java.util.Set;
import java.util.stream.Collectors;

public final class ConverteEntityParaDTO {

    private static final ModelMapper modelMapper = new ModelMapper();

/*    public ConverteEntityParaDTO(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }*/

    public static AtendimentoResponseDTO atendimentoParaDTO(Atendimento atendimento) {
        return AtendimentoResponseDTO.builder()
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
                .usuariosDTO(atendimento.getUsuarios().stream().map(u -> modelMapper.map(u, UsuarioSemRolesResponseDTO.class))
                        .collect(Collectors.toSet()))
//                        .medVetsDTO(atendimento.getMedVets().stream().map(m -> modelMapper.map(m, MedVetResponseDTO.class))
                .medVetsDTO(atendimento.getMedVets().stream().map(medVet -> {
                    MedVetResponseDTO dto = new MedVetResponseDTO();
                    dto.setCrmv(medVet.getCrmv());
                    dto.setNomeCompleto(medVet.getNomeCompleto());
                    dto.setEspecialidade(medVet.getEspecialidade());
                    return dto;
                }).collect(Collectors.toSet()))
                .build();
    }

    public static Set<AtendimentoResponseDTO> atendimentosParaDTO(Set<Atendimento> atendimentos) {

        return atendimentos.stream()
                .map(ConverteEntityParaDTO::atendimentoParaDTO)
                .collect(Collectors.toSet());
    }

    public static AtendimentoParaImunizanteResponseDTO atendimentoParaImunizanteParaDTO(Atendimento atendimento) {
        return AtendimentoParaImunizanteResponseDTO.builder()
                .id(atendimento.getIdAtendimento())
                .dataAtendimento(atendimento.getDataAtendimento())
                .usuariosDTO(atendimento.getUsuarios().stream().map(u -> modelMapper.map(u, UsuarioSemRolesResponseDTO.class))
                        .collect(Collectors.toSet()))
                .medVetsDTO(atendimento.getMedVets().stream().map(m -> modelMapper.map(m, MedVetResponseDTO.class))
/*                .medVetsDTO(atendimento.getMedVets().stream().map(medVet -> {
                    MedVetResponseDTO dto = new MedVetResponseDTO();
                    dto.setCrmv(medVet.getCrmv());
                    dto.setNomeCompleto(medVet.getNomeCompleto());
                    dto.setEspecialidade(medVet.getEspecialidade());
                    return dto;
                })*/.collect(Collectors.toSet()))
                .build();
    }

/*    public static String teste() {
        return "VERIFICA SE É PROBLEMA DE TRANSACTION";
    }*/

}
