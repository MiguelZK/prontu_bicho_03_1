package br.edu.ifrs.miguelzk.application.dto;

import lombok.Data;

import java.util.Date;

@Data
public class VacinaResponseSemAnimalDTO {

    private Long idVacina;

    private String nome;
    private String marca;
    private String lote;
    private Boolean importada;
    private Date dataFabricacao;
    private Date dataValidade;
    private Date dataAplicacao;
}
