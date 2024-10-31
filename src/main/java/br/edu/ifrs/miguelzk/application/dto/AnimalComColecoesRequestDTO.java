package br.edu.ifrs.miguelzk.application.dto;

import br.edu.ifrs.miguelzk.domain.enums.PorteCachorro;
import lombok.Data;

@Data
public class AnimalComColecoesRequestDTO {
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
    private PorteCachorro porteCachorro;
/*    private Set<Usuario> usuarios;
    private Set<Atendimento> atendimentos;*/
}
