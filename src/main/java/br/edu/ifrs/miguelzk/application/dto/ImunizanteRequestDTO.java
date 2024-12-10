package br.edu.ifrs.miguelzk.application.dto;

import br.edu.ifrs.miguelzk.domain.enums.TipoImunizante;
import lombok.Data;

import java.util.Date;

@Data
public class ImunizanteRequestDTO {

    private String nome;
    private TipoImunizante tipoImunizante;
    private String marca;
    private String lote;
    private Boolean importada;
    private Date dataFabricacao;
    private Date dataValidade;
    private Date dataAplicacao;
    private Long idAnimal;
}
