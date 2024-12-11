package br.edu.ifrs.miguelzk.application.usecase.impl;

import br.edu.ifrs.miguelzk.application.dto.AtendimentoRequestDTO;
import br.edu.ifrs.miguelzk.application.dto.AtendimentoResponseDTO;
import br.edu.ifrs.miguelzk.application.utils.ConverteEntityParaDTO;
import br.edu.ifrs.miguelzk.application.usecase.UpdateAtendimentoUseCase;
import br.edu.ifrs.miguelzk.domain.entities.Animal;
import br.edu.ifrs.miguelzk.domain.entities.Atendimento;
import br.edu.ifrs.miguelzk.domain.entities.MedVet;
import br.edu.ifrs.miguelzk.domain.entities.Usuario;
import br.edu.ifrs.miguelzk.domain.repository.AnimalRepository;
import br.edu.ifrs.miguelzk.domain.repository.AtendimentoRepository;
import br.edu.ifrs.miguelzk.domain.repository.MedVetRepository;
import br.edu.ifrs.miguelzk.domain.repository.UsuarioRepository;
import jakarta.ws.rs.NotFoundException;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UpdateAtendimentoUseCaseImpl implements UpdateAtendimentoUseCase {

    private final ModelMapper modelMapper;
    private AtendimentoRepository atendimentoRepository;
    private AnimalRepository animalRepository;
    private UsuarioRepository usuarioRepository;
    private MedVetRepository medVetRepository;

    public UpdateAtendimentoUseCaseImpl(AtendimentoRepository atendimentoRepository, AnimalRepository animalRepository
            , UsuarioRepository usuarioRepository, MedVetRepository medVetRepository, ModelMapper modelMapper) {
        this.atendimentoRepository = atendimentoRepository;
        this.animalRepository = animalRepository;
        this.usuarioRepository = usuarioRepository;
        this.medVetRepository = medVetRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AtendimentoResponseDTO execute(Long id, AtendimentoRequestDTO dto) {
        Atendimento atendimentoExistente = atendimentoRepository.findAtendimentoById(id);
        if (atendimentoExistente == null && atendimentoExistente.getRegistroAtivo()) {
            throw new NotFoundException("Atendimento não encontrado");
        }
        // CRIA ATENDIMENTO ATUALIZADO
        Atendimento atendimento = modelMapper.map(dto, Atendimento.class);
        atendimento.setIdAtendimento(null);
        atendimento.setRegistroAtivo(true);

        // VERIFICA ANIMAL E ATUALIZA
        Animal animal = animalRepository.findAnimalById(dto.getIdAnimal());
        if (animal == null) {
            throw new NotFoundException("Animal não encontrado");
        }

        // VERIFICA USUARIOS E ATUALIZA
        Set<Usuario> usuarios = new HashSet<>();
        dto.getIdUsuarios().stream().map(u -> usuarios.add(usuarioRepository.findUsuarioById(u)))
                .collect(Collectors.toSet());

        atendimento.setUsuarios(usuarios);

/*        // VERIFICA MEDVETS E ATUALIZA
        Set<MedVet> medVets = new HashSet<>();
        dto.getCrmvMedVets().stream().map(m -> medVets.add(medVetRepository.findMedVetById(m)))
                .collect(Collectors.toSet());

        atendimento.setMedVets(medVets);*/

        // SETANDO MEDVETS NO ATENDIMENTO
        Set<MedVet> medVets = new HashSet<>();
        dto.getCrmvMedVets().forEach((crmv) -> {
            try {
                MedVet medVet = medVetRepository.findMedVetByCrmv(crmv);
                medVets.add(medVet);
            } catch (NotFoundException e) {
                System.out.println("medVet com CRMV " + crmv + "não encontrado");
            }
        });
        atendimento.setMedVets(medVets);
//        atendimento.getMedVets().addAll(medVets);

        System.out.println("ATENDIMENTO ATUALIZADO: " + atendimentoExistente);

        // MANTÉM IMUNIZANTES NO REGISTRO DE ATENDIMENTO ATUALIZADO
        atendimento.setImunizantes(atendimentoExistente.getImunizantes());

        // ATUALIZA O REGISTRO
        Atendimento atendimentoSaved = atendimentoRepository.save(atendimento);
        System.out.println(atendimentoSaved);
        atualizaAtendimentoExistente(atendimentoExistente);
//        System.out.println(ConverteEntityParaDTO.teste());

        return ConverteEntityParaDTO.atendimentoParaDTO(atendimentoSaved);
    }

    public void atualizaAtendimentoExistente(Atendimento atendimento) {
        atendimento.setRegistroAtivo(false);
        atendimentoRepository.update(atendimento);
    }
}
