package br.edu.ifrs.miguelzk.application.utils;

import br.edu.ifrs.miguelzk.application.dto.*;
import br.edu.ifrs.miguelzk.domain.entities.Imunizante;
import org.modelmapper.ModelMapper;

public final class ConverteDTOParaEntity {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static Imunizante dtoParaImunizante(ImunizanteRequestDTO dto) {
        return Imunizante.builder()
                .nome(dto.getNome())
                .tipoImunizante(dto.getTipoImunizante())
                .marca(dto.getMarca())
                .lote(dto.getLote())
                .importada(dto.getImportada())
                .dataFabricacao(dto.getDataFabricacao())
                .dataValidade(dto.getDataValidade())
                .dataAplicacao(dto.getDataAplicacao())
                .build();
    }

}
