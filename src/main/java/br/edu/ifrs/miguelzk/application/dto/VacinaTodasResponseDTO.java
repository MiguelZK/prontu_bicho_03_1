package br.edu.ifrs.miguelzk.application.dto;

import lombok.Data;

import java.util.Date;

@Data
public class VacinaTodasResponseDTO {

    private Long idVacina;

    private String nome;
    private String marca;
    private String lote;
    private Boolean importada;
    private Boolean registroAtivo;
    private Date dataFabricacao;
    private Date dataValidade;
    private Date dataAplicacao;
//    private AnimalResponseDTO animalResponseDTO;
}
