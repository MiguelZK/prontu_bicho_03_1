package br.edu.ifrs.miguelzk.domain.repository;

import java.util.List;
import br.edu.ifrs.miguelzk.domain.entities.Animal;

public interface AnimalRepository {
    
    Animal findAnimalById(Long idAnimal);
    List<Animal> findAnimalAll();
    List<Animal> findAnimalByName(String nomeAnimal);
    Animal save(Animal animal);
    Animal update(Animal animal);
    void deleteAnimalById(Long id);

    long contaAnimais();
}
