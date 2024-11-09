package br.edu.ifrs.miguelzk.domain.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@UserDefinition
public class Usuario extends PanacheEntityBase {

    @Id
    @GeneratedValue
    private Long idUsuario;

    //    @NotBlank(message = "Login é obrigatório")
    @Column(unique = true)
    @Username
    private String userName;

    //    @NotBlank(message = "Senha é obrigatória")
    @JsonIgnore
    @Password
    private String password;

    @Roles
    private String role;

    private String nomeCompleto;

/*    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @CreationTimestamp
    private Date dataCadastro;

    @ElementCollection
    @CollectionTable(name="emails")
    private Set<String> emails;*/

    @ToString.Exclude
    @JsonBackReference
    @ManyToMany(mappedBy = "usuarios")
    private Set<Animal> animais = new HashSet<>();

    public Usuario(String nomeCompleto, String userName, String password, String role) {
        this.nomeCompleto = nomeCompleto;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.animais = new HashSet<>();
        this.vinculos = new HashSet<>();
    }

    @ToString.Exclude
    @JsonManagedReference
    @OneToMany(mappedBy = "usuario")
    private Set<Vinculo> vinculos = new HashSet<>();

    public Usuario() {
    }

/*    @ToString.Exclude
@OneToOne (cascade = CascadeType.PERSIST)
    @JoinColumn (name = "idCpf")
    private Cpf cpf;

    @ToString.Exclude
@OneToMany
    @JoinColumn (name = "idTelefone")
    private Set<Telefone> telefones;

    @ToString.Exclude
@OneToMany
    @JoinColumn (name = "idEndereco")
    private Set<Endereco> enderecos;
 */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(idUsuario, usuario.idUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idUsuario);
    }

    public static void add(String userName, String password, String role) {
        Usuario usuario = new Usuario();
        usuario.userName = userName;
        usuario.password = BcryptUtil.bcryptHash(password);
        usuario.role = role;
    }
}
