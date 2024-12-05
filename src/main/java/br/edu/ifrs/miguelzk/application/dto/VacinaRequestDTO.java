package br.edu.ifrs.miguelzk.application.dto;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
public class VacinaRequestDTO {

    private String nome;
    private String marca;
    private String lote;
    private Boolean importada;
    private Date dataFabricacao;
    private Date dataValidade;
    private Date dataAplicacao;
    private Long idAnimal;
}
