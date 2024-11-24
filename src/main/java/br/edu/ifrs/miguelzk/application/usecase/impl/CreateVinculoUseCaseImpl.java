package br.edu.ifrs.miguelzk.application.usecase.impl;

import br.edu.ifrs.miguelzk.application.dto.VinculoRequestDTO;
import br.edu.ifrs.miguelzk.application.dto.VinculoResponseDTO;
import br.edu.ifrs.miguelzk.application.usecase.CreateVinculoUseCase;
import br.edu.ifrs.miguelzk.domain.entities.Animal;
import br.edu.ifrs.miguelzk.domain.entities.Usuario;
import br.edu.ifrs.miguelzk.domain.entities.Vinculo;
import br.edu.ifrs.miguelzk.domain.repository.AnimalRepository;
import br.edu.ifrs.miguelzk.domain.repository.UsuarioRepository;
import br.edu.ifrs.miguelzk.domain.repository.VinculoRepository;
import br.edu.ifrs.miguelzk.infrastructure.persistence.DataLoader;
import jakarta.ws.rs.NotFoundException;
import org.jboss.logging.Logger;
import org.modelmapper.ModelMapper;

public class CreateVinculoUseCaseImpl implements CreateVinculoUseCase {

    private final VinculoRepository vinculoRepository;
    private final UsuarioRepository usuarioRepository;
    private final AnimalRepository animalRepository;
    private final ModelMapper modelMapper;
    private static final Logger LOG = Logger.getLogger(DataLoader.class);

    public CreateVinculoUseCaseImpl(VinculoRepository vinculoRepository, AnimalRepository animalRepository,
                                    UsuarioRepository usuarioRepository, ModelMapper modelMapper) {
        this.vinculoRepository = vinculoRepository;
        this.animalRepository = animalRepository;
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public VinculoResponseDTO execute(VinculoRequestDTO request) {

        Usuario usuario = usuarioRepository.findUsuarioById(request.getIdUsuario());
        Animal animal = animalRepository.findAnimalById(request.getIdAnimal());

        LOG.info(animal);

        if (usuario == null || animal == null) {
          throw new NotFoundException("Usuário ou animal não encontrado");
        }

        Vinculo vinculo = new Vinculo();
//        vinculo.setNomeAnimal(request.getNomeAnimal());
        vinculo.setNomeAnimal(animal.getNomeAnimal());
        vinculo.setAnimal(animal);
        vinculo.setUsuario(usuario);

        animal.getUsuarios().add(usuario);
        usuario.getAnimais().add(animal);
        LOG.info(animal);
        LOG.info(animal.getUsuarios());

        animalRepository.save(animal);
        usuarioRepository.save(usuario);

        Vinculo savedVinculo = vinculoRepository.save(vinculo);
        return modelMapper.map(savedVinculo, VinculoResponseDTO.class);
    }

}
