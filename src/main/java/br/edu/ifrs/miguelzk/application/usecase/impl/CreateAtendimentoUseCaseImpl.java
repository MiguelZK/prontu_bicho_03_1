package br.edu.ifrs.miguelzk.application.usecase.impl;

import br.edu.ifrs.miguelzk.domain.entities.MedVet;
import br.edu.ifrs.miguelzk.domain.entities.Usuario;
import br.edu.ifrs.miguelzk.domain.repository.MedVetRepository;
import jakarta.ws.rs.NotFoundException;
import org.hibernate.internal.build.AllowSysOut;
import org.modelmapper.ModelMapper;

import br.edu.ifrs.miguelzk.application.dto.AtendimentoRequestDTO;
import br.edu.ifrs.miguelzk.application.dto.AtendimentoResponseDTO;
import br.edu.ifrs.miguelzk.application.usecase.CreateAtendimentoUseCase;
import br.edu.ifrs.miguelzk.domain.entities.Atendimento;
import br.edu.ifrs.miguelzk.domain.entities.Animal;
import br.edu.ifrs.miguelzk.domain.repository.UsuarioRepository;
import br.edu.ifrs.miguelzk.domain.repository.AtendimentoRepository;
import br.edu.ifrs.miguelzk.domain.repository.AnimalRepository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CreateAtendimentoUseCaseImpl implements CreateAtendimentoUseCase {

    private AtendimentoRepository atendimentoRepository;
    private UsuarioRepository usuarioRepository;
    private AnimalRepository animalRepository;
    private MedVetRepository medVetRepository;
    private ModelMapper modelMapper;

    public CreateAtendimentoUseCaseImpl(AtendimentoRepository atendimentoRepository, UsuarioRepository usuarioRepository
            , AnimalRepository animalRepository, MedVetRepository medVetRepository, ModelMapper modelMapper) {
        this.atendimentoRepository = atendimentoRepository;
        this.usuarioRepository = usuarioRepository;
        this.animalRepository = animalRepository;
        this.medVetRepository = medVetRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AtendimentoResponseDTO execute(AtendimentoRequestDTO atendimentoRequest) {
        Atendimento atendimento = new Atendimento();

        Animal animal = animalRepository.findAnimalById(atendimentoRequest.getAnimal().getIdAnimal());
        atendimento.setAnimal(animal);

        Set<Usuario> usuarios = new HashSet<>();
        atendimentoRequest.getIdUsuarios().forEach((idUsuario) -> {
            try {
                Usuario usuario = usuarioRepository.findUsuarioById(idUsuario);
                usuarios.add(usuario);
            } catch (NotFoundException e) {
                System.out.println("usuario " + idUsuario + "não encontrado");
            }
        });
        atendimento.getUsuarios().addAll(usuarios);

        Set<MedVet> medVets = new HashSet<>();
        atendimentoRequest.getMedVets().forEach((crmv) -> {
            try {
                MedVet medVet = medVetRepository.findMedVetByCrmv(crmv);
                medVets.add(medVet);
            } catch (NotFoundException e) {
                System.out.println("medVet com CRMV " + crmv + "não encontrado");
            }
        });
        atendimento.getMedVets().addAll(medVets);

        Atendimento saveatendimento = atendimentoRepository.save(atendimento);

        return modelMapper.map(saveatendimento, AtendimentoResponseDTO.class);
    }

}
