package br.edu.ifrs.miguelzk.infrastructure.persistence;

import br.edu.ifrs.miguelzk.domain.entities.Usuario;
import br.edu.ifrs.miguelzk.domain.entities.Animal;
import br.edu.ifrs.miguelzk.domain.repository.UsuarioRepository;
import br.edu.ifrs.miguelzk.domain.repository.AnimalRepository;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;

@ApplicationScoped
public class DataLoader {

    private final UsuarioRepository usuarioRepository;
    private final AnimalRepository animalRepository;
    private static final Logger LOG = Logger.getLogger(DataLoader.class);

    public DataLoader(UsuarioRepository usuarioRepository, AnimalRepository animalRepository) {
        this.usuarioRepository = usuarioRepository;
        this.animalRepository = animalRepository;
    }

    @PostConstruct
    public void loadData() {
        // Verificar se os dados já foram inseridos para evitar duplicidade
        LOG.info("Carregando os dados iniciais no Dataloader...");
        if (usuarioRepository.contaUsuarios() == 0 && animalRepository.contaAnimais() == 0) {
            // Inserir usuários
            Usuario usuario1 = new Usuario("Miguel", "miguel", "senha123", "admin, user");
            Usuario usuario2 = new Usuario("Laura", "laura", "senha456", "user");
            Usuario usuario3 = new Usuario("Carol", "carol", "senha789", "user");

            usuarioRepository.save(usuario1);
            usuarioRepository.save(usuario2);
            usuarioRepository.save(usuario3);

            // Inserir animais
            Animal animal1 = new Animal("Shenka", "PEQUENO");
            Animal animal2 = new Animal("Agnes", "PEQUENO");
            Animal animal3 = new Animal("Bidu", "PEQUENO");

            animalRepository.save(animal1);
            animalRepository.save(animal2);
            animalRepository.save(animal3);

            // Relacionar usuários e animais programaticamente
            usuario1.getAnimais().add(animal1);
            usuario2.getAnimais().add(animal1);
            usuario3.getAnimais().add(animal2);
            usuario3.getAnimais().add(animal3);

//            LOG.info("Usuario 1: " + usuario1);
//            LOG.info("Usuario 2: " + usuario2);
//            LOG.info("Usuario 3: " + usuario3);

            animal1.getUsuarios().add(usuario1);
            animal1.getUsuarios().add(usuario2);
            animal2.getUsuarios().add(usuario3);
            animal3.getUsuarios().add(usuario3);

            // Persistir as atualizações nos usuários
            usuarioRepository.save(usuario1);
            usuarioRepository.save(usuario2);
            usuarioRepository.save(usuario3);

//            LOG.info("Usuario 1 do BD: " + animal1);

            animalRepository.save(animal1);
            animalRepository.save(animal2);
            animalRepository.save(animal3);

            LOG.info("Dados carregados com sucesso.");
        }
    }
}
