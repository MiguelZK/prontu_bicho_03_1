package br.edu.ifrs.miguelzk.application.usecase.impl;

import org.modelmapper.ModelMapper;

import br.edu.ifrs.miguelzk.application.dto.AtendimentoRequestDTO;
import br.edu.ifrs.miguelzk.application.dto.AtendimentoResponseDTO;
import br.edu.ifrs.miguelzk.application.usecase.CreateAtendimentoUseCase;
import br.edu.ifrs.miguelzk.domain.entities.Atendimento;
import br.edu.ifrs.miguelzk.domain.entities.Animal;
import br.edu.ifrs.miguelzk.domain.repository.UsuarioRepository;
import br.edu.ifrs.miguelzk.domain.repository.AtendimentoRepository;
import br.edu.ifrs.miguelzk.domain.repository.AnimalRepository;

public class CreateAtendimentoUseCaseImpl implements CreateAtendimentoUseCase {

    private AtendimentoRepository atendimentoRepository;
    private UsuarioRepository usuarioRepository;
    private AnimalRepository animalRepository;
    private ModelMapper modelMapper;

    public CreateAtendimentoUseCaseImpl(AtendimentoRepository atendimentoRepository, UsuarioRepository usuarioRepository, AnimalRepository animalRepository, ModelMapper modelMapper) {
        this.atendimentoRepository = atendimentoRepository;
        this.usuarioRepository = usuarioRepository;
        this.animalRepository = animalRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AtendimentoResponseDTO execute(AtendimentoRequestDTO atendimentoRequest) {
        Atendimento atendimento = new Atendimento();
        Animal animal = animalRepository.findAnimalById(atendimentoRequest.getAnimal().getIdAnimal());
        atendimento.setAnimal(animal);
        Atendimento saveatendimento = atendimentoRepository.save(atendimento);

        return modelMapper.map(saveatendimento, AtendimentoResponseDTO.class);
    }

}
