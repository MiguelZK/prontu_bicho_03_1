package br.edu.ifrs.miguelzk.infrastructure.persistence;

import java.util.List;

import br.edu.ifrs.miguelzk.domain.entities.Usuario;
import br.edu.ifrs.miguelzk.domain.repository.UsuarioRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepositoryImpl implements UsuarioRepository, PanacheRepositoryBase<Usuario, Long> {

  public Usuario save(Usuario usuario) {
    persist(usuario);
    return usuario;
  }

  @Override
  public Usuario findUsuarioById(Long id) {
    return findById(id);
  }

  @Override
  public List<Usuario> findUsuarioByName(String nomeUsuario) {
    return find("userName like ?1", "%" + nomeUsuario + "%").list();
  }

  @Override
  public List<Usuario> findUsuarioAll() {
    return listAll();
  }

  @Override
  public Usuario update(Usuario usuario) {
    persist(usuario);
    return usuario;
  }

  @Override
  public void deleteUsuarioById(Long id) {
    deleteById(id);
  }

  @Override
  public long contaUsuarios() {
    return count();
  }
}
