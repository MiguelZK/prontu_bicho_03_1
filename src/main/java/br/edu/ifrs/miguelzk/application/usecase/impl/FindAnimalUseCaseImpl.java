package br.edu.ifrs.miguelzk.application.usecase.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.edu.ifrs.miguelzk.application.dto.*;
import br.edu.ifrs.miguelzk.application.service.ConverteEntityParaDTO;
import br.edu.ifrs.miguelzk.domain.entities.Atendimento;
import br.edu.ifrs.miguelzk.infrastructure.exception.ObjetoNaoEncontradoException;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import org.modelmapper.ModelMapper;
import br.edu.ifrs.miguelzk.application.usecase.FindAnimalUseCase;
import br.edu.ifrs.miguelzk.domain.entities.Animal;
import br.edu.ifrs.miguelzk.domain.repository.AnimalRepository;

public class FindAnimalUseCaseImpl implements FindAnimalUseCase {

    private final AnimalRepository animalRepository;
    private final ModelMapper modelMapper;
    private final ConverteEntityParaDTO converteEntityParaDTO;

    public FindAnimalUseCaseImpl(AnimalRepository animalRepository, ModelMapper modelMapper) {
        this.animalRepository = animalRepository;
        this.modelMapper = modelMapper;
        this.converteEntityParaDTO = new ConverteEntityParaDTO(modelMapper);
    }

    @Override
    public List<AnimalResponseDTO> execute() {
        List<AnimalResponseDTO> listAnimalResponseDTO = new ArrayList<>();
        List<Animal> listAnimal = animalRepository.findAnimalAll();

        for (Animal animal : listAnimal) {
            listAnimalResponseDTO.add(modelMapper.map(animal, AnimalResponseDTO.class));
        }

        return listAnimalResponseDTO;
    }

    // PRONTUÁRIO COMPLETO
    @Override
    public AnimalComColecoesResponseDTO findAnimalComColecoesExecute(Long id) {
        Optional<Animal> animalOpt = Optional.ofNullable(animalRepository.findAnimalById(id));
        if (animalOpt.isEmpty()) {
            throw new ObjetoNaoEncontradoException("Animal Não Encontrado.");
        }
        Animal animal = animalOpt.get();
        AnimalComColecoesResponseDTO animalComColecoesResponseDTO = modelMapper
                .map(animal, AnimalComColecoesResponseDTO.class);
        animalComColecoesResponseDTO.setUsuarios(animal.getUsuarios().stream()
                .map(u -> modelMapper.map(u, UsuarioResponseDTO.class))
                .collect(Collectors.toSet()));
        animalComColecoesResponseDTO.setAtendimentos
                (converteEntityParaDTO.atendimentosParaDTO(animal.getAtendimentos()));
        animalComColecoesResponseDTO.setVacinas(animal.getVacinas().stream()
                .map(v -> modelMapper.map(v, VacinaResponseSemAnimalDTO.class))
                .collect(Collectors.toSet()));
        return animalComColecoesResponseDTO;
    }

    @Override
    public AnimalResponseDTO execute(Long id) {
        try {
            Animal animal = animalRepository.findAnimalById(id);

            if (animal == null) {
                throw new NotFoundException("Animal não encontrado");
            }
            return modelMapper.map(animal, AnimalResponseDTO.class);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Animal não encontrado");
        }
    }

    @Override
    public List<AnimalResponseDTO> execute(String nomeAnimal) {
        List<AnimalResponseDTO> listAnimalResponseDTO = new ArrayList<>();
        List<Animal> listAnimal = animalRepository.findAnimalByName(nomeAnimal);

        for (Animal animal : listAnimal) {
            listAnimalResponseDTO.add(modelMapper.map(animal, AnimalResponseDTO.class));
        }

        return listAnimalResponseDTO;
    }

}
