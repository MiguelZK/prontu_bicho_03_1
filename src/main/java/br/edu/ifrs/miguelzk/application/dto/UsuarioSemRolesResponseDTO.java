package br.edu.ifrs.miguelzk.application.dto;

import lombok.Data;

@Data
public class UsuarioSemRolesResponseDTO {
    private Long idUsuario;
    private String userName;
    private String nomeCompleto;
    /*private Cpf cpf;
    private Date dataNascimento;
    private Date dataCadastro;
    private Set<Telefone> telefones;
    private Set<Endereco> enderecos;
    private Set<String> emails;
    private Set<Animal> animais;*/
}