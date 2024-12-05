package br.edu.ifrs.miguelzk.application.usecase.impl;

import br.edu.ifrs.miguelzk.application.dto.*;
import br.edu.ifrs.miguelzk.application.usecase.VacinaUseCase;
import br.edu.ifrs.miguelzk.domain.entities.Animal;
import br.edu.ifrs.miguelzk.domain.entities.Vacina;
import br.edu.ifrs.miguelzk.domain.entities.Vinculo;
import br.edu.ifrs.miguelzk.domain.repository.*;
import br.edu.ifrs.miguelzk.infrastructure.exception.ObjetoNaoEncontradoException;
import br.edu.ifrs.miguelzk.infrastructure.persistence.DataLoader;
import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import org.jboss.logging.Logger;
import org.modelmapper.ModelMapper;

import java.util.*;
import java.util.stream.Collectors;

public class VacinaUseCaseImpl implements VacinaUseCase {

    private final VacinaRepository vacinaRepository;
    private final AnimalRepository animalRepository;
    private final AtendimentoRepository atendimentoRepository;
    private final ModelMapper modelMapper;
    private static final Logger LOG = Logger.getLogger(DataLoader.class);

    public VacinaUseCaseImpl(VacinaRepository vacinaRepository, AnimalRepository animalRepository
            , AtendimentoRepository atendimentoRepository, ModelMapper modelMapper) {
        this.vacinaRepository = vacinaRepository;
        this.animalRepository = animalRepository;
        this.atendimentoRepository = atendimentoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public VacinaResponseDTO createVacina(VacinaRequestDTO dto) {
        Vacina vacina = modelMapper.map(dto, Vacina.class);
        vacina.setIdVacina(null);
        Animal animal = animalRepository.findAnimalById(dto.getIdAnimal());
        vacina.setAnimal(animal);
        Vacina savedVacina = vacinaRepository.save(vacina);
        animal.getVacinas().add(savedVacina);
        animalRepository.update(animal);
        VacinaResponseDTO vacinaResponseDTO = modelMapper.map(savedVacina, VacinaResponseDTO.class);
        vacinaResponseDTO.setAnimalResponseDTO(modelMapper.map(vacina.getAnimal(), AnimalResponseDTO.class));
        return vacinaResponseDTO;
    }

    @Override
    public VacinaResponseDTO findVacinaById(Long id) {
        try {
            Vacina vacina = vacinaRepository.findVacinaById(id);

            if (vacina == null) {
                throw new NotFoundException("Vacina id " + id + " não encontrada");
            }
            VacinaResponseDTO vacinaResponseDTO = modelMapper.map(vacina, VacinaResponseDTO.class);
            vacinaResponseDTO.setAnimalResponseDTO(modelMapper.map(vacina.getAnimal(), AnimalResponseDTO.class));
            return vacinaResponseDTO;
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Vacina não encontrado");
        }
    }

    @Override
    public List<VacinaResponseDTO> listAllVacinas() {
        List<VacinaResponseDTO> listVacinaResponseDTO = new ArrayList<>();
        List<Vacina> listVacina = vacinaRepository.findVacinaAll();

        for (Vacina vacina : listVacina) {
            VacinaResponseDTO vacinaResponseDTO = modelMapper.map(vacina, VacinaResponseDTO.class);
            vacinaResponseDTO.setAnimalResponseDTO(modelMapper.map(vacina.getAnimal(), AnimalResponseDTO.class));
            listVacinaResponseDTO.add(vacinaResponseDTO);
        }

        return listVacinaResponseDTO;
    }

    @Override
    public Set<VacinaResponseSemAnimalDTO> carteiraDeVacinacao(Long idAnimal) {
        Optional<Animal> animalOpt = Optional.ofNullable(animalRepository.findAnimalById(idAnimal));
        if (animalOpt.isEmpty()) {
            throw new ObjetoNaoEncontradoException("Animal Não Encontrado.");
        }
        return animalOpt.get().getVacinas().stream()
                .map(vacina -> modelMapper.map(vacina, VacinaResponseSemAnimalDTO.class))
                .collect(Collectors.toSet());
    }

    @Override
    public VacinaResponseDTO updateVacina(Long id, VacinaRequestDTO dto) {
        Vacina vacinaExistente = vacinaRepository.findVacinaById(id);
        if (vacinaExistente == null) {
            throw new ObjetoNaoEncontradoException("Vacina não encontrada");
        }

/*        if (vacinaExistente.getAnimal().getIdAnimal() != dto.getIdAnimal()) {
            animalRepository.findAnimalById(vacinaExistente.getAnimal().getIdAnimal())
                    .getVacinas().remove(vacinaExistente);
        }*/
        Date dataCadastro = vacinaExistente.getDataCadastro();
        vacinaExistente = modelMapper.map(dto, Vacina.class);
        vacinaExistente.setDataCadastro(dataCadastro);
        vacinaExistente.setIdVacina(id);
        Animal animal = animalRepository.findAnimalById(dto.getIdAnimal());
        vacinaExistente.setAnimal(animal);
/*        animal.getVacinas().add(vacinaExistente);
        animalRepository.update(animal);*/
        Vacina vacinaSaved = vacinaRepository.update(vacinaExistente);
        VacinaResponseDTO vacinaResponseDTO = modelMapper.map(vacinaSaved, VacinaResponseDTO.class);
        vacinaResponseDTO.setAnimalResponseDTO(modelMapper.map(vacinaSaved.getAnimal(), AnimalResponseDTO.class));
        return vacinaResponseDTO;
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
