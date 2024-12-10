package br.edu.ifrs.miguelzk.application.dto;

//import org.hibernate.type.descriptor.java.DateJavaType;

import br.edu.ifrs.miguelzk.domain.enums.PorteCachorro;
import lombok.Data;

import java.util.Set;

@Data
public class AnimalComColecoesResponseDTO {

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
    private PorteCachorro porteCachorro;
    private Set<UsuarioResponseDTO> usuarios;
    private Set<AtendimentoResponseDTO> atendimentos;
    private Set<ImunizanteResponseDTO> imunizantes;
}
