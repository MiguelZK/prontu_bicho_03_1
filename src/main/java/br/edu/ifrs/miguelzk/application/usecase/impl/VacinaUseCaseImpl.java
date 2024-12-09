package br.edu.ifrs.miguelzk.application.usecase.impl;

import br.edu.ifrs.miguelzk.application.dto.*;
import br.edu.ifrs.miguelzk.application.usecase.VacinaUseCase;
import br.edu.ifrs.miguelzk.domain.entities.Animal;
import br.edu.ifrs.miguelzk.domain.entities.Vacina;
import br.edu.ifrs.miguelzk.domain.repository.*;
import br.edu.ifrs.miguelzk.infrastructure.exception.ObjetoNaoEncontradoException;
import br.edu.ifrs.miguelzk.infrastructure.persistence.DataLoader;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import org.jboss.logging.Logger;
import org.modelmapper.ModelMapper;

import java.util.*;
import java.util.stream.Collectors;

public class VacinaUseCaseImpl implements VacinaUseCase {

    private static final Logger LOG = Logger.getLogger(DataLoader.class);
    private final VacinaRepository vacinaRepository;
    private final AnimalRepository animalRepository;
    private final AtendimentoRepository atendimentoRepository;
    private final ModelMapper modelMapper;

    public VacinaUseCaseImpl(VacinaRepository vacinaRepository, AnimalRepository animalRepository
            , AtendimentoRepository atendimentoRepository, ModelMapper modelMapper) {
        this.vacinaRepository = vacinaRepository;
        this.animalRepository = animalRepository;
        this.atendimentoRepository = atendimentoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public VacinaComAnimalResponseDTO createVacina(VacinaRequestDTO dto) {
        Vacina vacina = modelMapper.map(dto, Vacina.class);
        vacina.setIdVacina(null);
        Optional<Animal> animalOpt = Optional.ofNullable(animalRepository.findAnimalById(dto.getIdAnimal()));
        if (animalOpt.isEmpty()) {
            throw new ObjetoNaoEncontradoException("Animal não encontrado");
        }
        Animal animal = animalOpt.get();
//        vacina.setAnimal(animal);
        vacina.setRegistroAtivo(true);
        Vacina savedVacina = vacinaRepository.save(vacina);
        animal.getVacinas().add(savedVacina);
        animalRepository.update(animal);
        VacinaComAnimalResponseDTO vacinaComAnimalResponseDTO = modelMapper.map(savedVacina, VacinaComAnimalResponseDTO.class);
        vacinaComAnimalResponseDTO.setAnimalResponseDTO(modelMapper.map(animal, AnimalResponseDTO.class));
        return vacinaComAnimalResponseDTO;
    }

    @Override
    public VacinaTodasResponseDTO findVacinaById(Long id) {
        try {
            Vacina vacina = vacinaRepository.findVacinaById(id);

            if (vacina == null) {
                throw new NotFoundException("Vacina id " + id + " não encontrada");
            }
            VacinaTodasResponseDTO vacinaTodasResponseDTO = modelMapper.map(vacina, VacinaTodasResponseDTO.class);
//            vacinaResponseDTO.setAnimalResponseDTO(modelMapper.map(vacina.getAnimal(), AnimalResponseDTO.class));
            return vacinaTodasResponseDTO;
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Vacina não encontrado");
        }
    }

    @Override
    public List<VacinaTodasResponseDTO> listAllVacinas() {
        List<VacinaTodasResponseDTO> vacinaTodasResponseDTOS = new ArrayList<>();
        List<Vacina> listVacina = vacinaRepository.findVacinaAll();

        for (Vacina vacina : listVacina) {
            VacinaTodasResponseDTO vacinaTodasResponseDTO = modelMapper.map(vacina, VacinaTodasResponseDTO.class);
//            vacinaTodasResponseDTO.setAnimalResponseDTO(modelMapper.map(vacina.getAnimal(), AnimalResponseDTO.class));
            vacinaTodasResponseDTOS.add(vacinaTodasResponseDTO);
        }

        return vacinaTodasResponseDTOS;
    }

    @Override
    public Set<VacinaResponseDTO> carteiraDeVacinacao(Long idAnimal) {
        Optional<Animal> animalOpt = Optional.ofNullable(animalRepository.findAnimalById(idAnimal));
        if (animalOpt.isEmpty()) {
            throw new ObjetoNaoEncontradoException("Animal Não Encontrado.");
        }
        return animalOpt.get().getVacinas().stream()
                .map(vacina -> modelMapper.map(vacina, VacinaResponseDTO.class))
                .collect(Collectors.toSet());
    }

    @Override
    public VacinaComAnimalResponseDTO updateVacina(Long id, VacinaRequestDTO dto) {

        Vacina vacinaExistente = vacinaRepository.findVacinaById(id);
        if (vacinaExistente == null) {
            throw new ObjetoNaoEncontradoException("Vacina não encontrada");
        }

        Animal animal = animalRepository.findAnimalById(dto.getIdAnimal());
        if (animal == null) {
            throw new ObjetoNaoEncontradoException("Animal não encontrada");
        }

        Vacina vacina = modelMapper.map(dto, Vacina.class);
        vacina.setIdVacina(null);
        vacina.setRegistroAtivo(true);
        Vacina vacinaSaved = vacinaRepository.save(vacina);

        vacinaExistente.setRegistroAtivo(false);

        animal.getVacinas().add(vacinaSaved);
        animalRepository.update(animal);
        VacinaComAnimalResponseDTO vacinaComAnimalResponseDTO = modelMapper.map(vacinaSaved, VacinaComAnimalResponseDTO.class);
        vacinaComAnimalResponseDTO.setAnimalResponseDTO(modelMapper.map(animal, AnimalResponseDTO.class));
        return vacinaComAnimalResponseDTO;
    }

    @Override
    public void deleteVacina(Long id) {
        Optional<Vacina> vacinaOpt = Optional.ofNullable(vacinaRepository.findVacinaById(id));
        if (vacinaOpt.isEmpty()) {
            throw new ObjetoNaoEncontradoException("Vacina não Encontrada.");
        }
        Vacina vacina = vacinaOpt.get();
        vacinaRepository.deleteVacinaById(vacina.getIdVacina());
    }
}
