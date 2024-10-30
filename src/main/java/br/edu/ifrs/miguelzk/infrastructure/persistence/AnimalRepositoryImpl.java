package br.edu.ifrs.miguelzk.infrastructure.persistence;

import java.util.List;

import br.edu.ifrs.miguelzk.domain.entities.Animal;
import br.edu.ifrs.miguelzk.domain.repository.AnimalRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AnimalRepositoryImpl implements AnimalRepository, PanacheRepositoryBase<Animal, Long> {

  @Override
  public Animal findAnimalById(Long idAnimal) {
    return findById(idAnimal);
  }

  @Override
  public List<Animal> findAnimalAll() {
    return listAll();
  }

  @Override
  public List<Animal> findAnimalByName(String nomeAnimal) {
    return find("nomeAnimal like ?1", "%" + nomeAnimal + "%").list();
  }

  @Override
  public Animal save(Animal animal) {
    persist(animal);
    return animal;
  }

  @Override
  public Animal update(Animal animal) {
    persist(animal);
    return animal;
  }

  @Override
  public void deleteAnimalById(Long id) {
    deleteById(id);
  }

  @Override
  public long contaAnimais() {
    return count();
  }

}
