package br.edu.ifrs.miguelzk.application.dto;

import java.util.Date;
import java.util.Set;

//import org.hibernate.type.descriptor.java.DateJavaType;

import br.edu.ifrs.miguelzk.domain.entities.Animal;
import br.edu.ifrs.miguelzk.domain.entities.Cpf;
import br.edu.ifrs.miguelzk.domain.entities.Endereco;
import br.edu.ifrs.miguelzk.domain.entities.Telefone;
import lombok.Data;

@Data
public class UsuarioRequestDTO {

    private String userName;
    private String password;
    private String role;
    private String nomeCompleto;
/*    private Cpf cpf;
    private Date dataNascimento;
    private Date dataCadastro;
    private Set<Telefone> telefones;
    private Set<Endereco> enderecos;
    private Set<String> emails;
    private Set<Animal> animais;*/

}
