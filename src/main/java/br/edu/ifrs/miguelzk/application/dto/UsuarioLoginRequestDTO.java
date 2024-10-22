package br.edu.ifrs.miguelzk.application.dto;

import br.edu.ifrs.miguelzk.domain.entities.Animal;
import br.edu.ifrs.miguelzk.domain.entities.Cpf;
import br.edu.ifrs.miguelzk.domain.entities.Endereco;
import br.edu.ifrs.miguelzk.domain.entities.Telefone;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class UsuarioLoginRequestDTO {

    private String userName;
    private String password;
    private String nomeCompleto;
}
