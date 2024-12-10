package br.edu.ifrs.miguelzk.application.dto;

import lombok.Data;

import java.util.Set;

@Data
public class AnimalCarteiraVacinacaoResponseDTO {

    private Long id;
    private String nomeAnimal;
    /*private String idMicrochip;
    private Date dataCriacao;
    private Date dataNascimento;
    private Date dataFalecimento;
    private Boolean idadeAproximada;
    private String temperamento;
    private String cor;
    private String sinaisParticulares;
    private String tipoPelagem;
    private Double peso;
    private String descricao;*/
    private Set<ImunizanteResponseDTO> vacinas;
}
