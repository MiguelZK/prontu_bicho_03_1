package br.edu.ifrs.miguelzk.application.usecase.impl;

import br.edu.ifrs.miguelzk.application.dto.*;
import br.edu.ifrs.miguelzk.application.usecase.ImunizanteUseCase;
import br.edu.ifrs.miguelzk.domain.entities.Animal;
import br.edu.ifrs.miguelzk.domain.entities.Imunizante;
import br.edu.ifrs.miguelzk.domain.repository.*;
import br.edu.ifrs.miguelzk.infrastructure.exception.ObjetoNaoEncontradoException;
import br.edu.ifrs.miguelzk.infrastructure.persistence.DataLoader;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import org.jboss.logging.Logger;
import org.modelmapper.ModelMapper;

import java.util.*;
import java.util.stream.Collectors;

public class ImunizanteUseCaseImpl implements ImunizanteUseCase {

    private static final Logger LOG = Logger.getLogger(DataLoader.class);
    private final ImunizanteRepository vacinaRepository;
    private final AnimalRepository animalRepository;
    private final AtendimentoRepository atendimentoRepository;
    private final ModelMapper modelMapper;

    public ImunizanteUseCaseImpl(ImunizanteRepository vacinaRepository, AnimalRepository animalRepository
            , AtendimentoRepository atendimentoRepository, ModelMapper modelMapper) {
        this.vacinaRepository = vacinaRepository;
        this.animalRepository = animalRepository;
        this.atendimentoRepository = atendimentoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ImunizanteComAnimalResponseDTO createImunizante(ImunizanteRequestDTO dto) {
        Imunizante vacina = modelMapper.map(dto, Imunizante.class);
        vacina.setIdImunizante(null);
        Optional<Animal> animalOpt = Optional.ofNullable(animalRepository.findAnimalById(dto.getIdAnimal()));
        if (animalOpt.isEmpty()) {
            throw new ObjetoNaoEncontradoException("Animal não encontrado");
        }
        Animal animal = animalOpt.get();
//        vacina.setAnimal(animal);
        vacina.setRegistroAtivo(true);
        Imunizante savedImunizante = vacinaRepository.save(vacina);
        animal.getImunizantes().add(savedImunizante);
        animalRepository.update(animal);
        ImunizanteComAnimalResponseDTO vacinaComAnimalResponseDTO = modelMapper.map(savedImunizante, ImunizanteComAnimalResponseDTO.class);
        vacinaComAnimalResponseDTO.setAnimalResponseDTO(modelMapper.map(animal, AnimalResponseDTO.class));
        return vacinaComAnimalResponseDTO;
    }

    @Override
    public ImunizanteTodasResponseDTO findImunizanteById(Long id) {
        try {
            Imunizante vacina = vacinaRepository.findImunizanteById(id);

            if (vacina == null) {
                throw new NotFoundException("Imunizante id " + id + " não encontrada");
            }
            ImunizanteTodasResponseDTO vacinaTodasResponseDTO = modelMapper.map(vacina, ImunizanteTodasResponseDTO.class);
//            vacinaResponseDTO.setAnimalResponseDTO(modelMapper.map(vacina.getAnimal(), AnimalResponseDTO.class));
            return vacinaTodasResponseDTO;
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Imunizante não encontrado");
        }
    }

    @Override
    public List<ImunizanteTodasResponseDTO> listAllImunizantes() {
        List<ImunizanteTodasResponseDTO> vacinaTodasResponseDTOS = new ArrayList<>();
        List<Imunizante> listImunizante = vacinaRepository.findImunizanteAll();

        for (Imunizante vacina : listImunizante) {
            ImunizanteTodasResponseDTO vacinaTodasResponseDTO = modelMapper.map(vacina, ImunizanteTodasResponseDTO.class);
//            vacinaTodasResponseDTO.setAnimalResponseDTO(modelMapper.map(vacina.getAnimal(), AnimalResponseDTO.class));
            vacinaTodasResponseDTOS.add(vacinaTodasResponseDTO);
        }

        return vacinaTodasResponseDTOS;
    }

    @Override
    public Set<ImunizanteResponseDTO> carteiraDeImunizantecao(Long idAnimal) {
        Optional<Animal> animalOpt = Optional.ofNullable(animalRepository.findAnimalById(idAnimal));
        if (animalOpt.isEmpty()) {
            throw new ObjetoNaoEncontradoException("Animal Não Encontrado.");
        }
        return animalOpt.get().getImunizantes().stream()
                .map(vacina -> modelMapper.map(vacina, ImunizanteResponseDTO.class))
                .collect(Collectors.toSet());
    }

    @Override
    public ImunizanteComAnimalResponseDTO updateImunizante(Long id, ImunizanteRequestDTO dto) {

        Imunizante vacinaExistente = vacinaRepository.findImunizanteById(id);
        if (vacinaExistente == null) {
            throw new ObjetoNaoEncontradoException("Imunizante não encontrada");
        }

        Animal animal = animalRepository.findAnimalById(dto.getIdAnimal());
        if (animal == null) {
            throw new ObjetoNaoEncontradoException("Animal não encontrada");
        }

        Imunizante vacina = modelMapper.map(dto, Imunizante.class);
        vacina.setIdImunizante(null);
        vacina.setRegistroAtivo(true);
        Imunizante vacinaSaved = vacinaRepository.save(vacina);

        vacinaExistente.setRegistroAtivo(false);

        animal.getImunizantes().add(vacinaSaved);
        animalRepository.update(animal);
        ImunizanteComAnimalResponseDTO vacinaComAnimalResponseDTO = modelMapper.map(vacinaSaved, ImunizanteComAnimalResponseDTO.class);
        vacinaComAnimalResponseDTO.setAnimalResponseDTO(modelMapper.map(animal, AnimalResponseDTO.class));
        return vacinaComAnimalResponseDTO;
    }

    @Override
    public void deleteImunizante(Long id) {
        Optional<Imunizante> vacinaOpt = Optional.ofNullable(vacinaRepository.findImunizanteById(id));
        if (vacinaOpt.isEmpty()) {
            throw new ObjetoNaoEncontradoException("Imunizante não Encontrada.");
        }
        Imunizante vacina = vacinaOpt.get();
        vacinaRepository.deleteImunizanteById(vacina.getIdImunizante());
    }
}
