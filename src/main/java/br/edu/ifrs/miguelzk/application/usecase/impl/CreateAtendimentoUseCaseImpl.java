package br.edu.ifrs.miguelzk.application.usecase.impl;

import br.edu.ifrs.miguelzk.application.dto.*;
import br.edu.ifrs.miguelzk.domain.entities.MedVet;
import br.edu.ifrs.miguelzk.domain.entities.Usuario;
import br.edu.ifrs.miguelzk.domain.repository.MedVetRepository;
import br.edu.ifrs.miguelzk.infrastructure.persistence.DataLoader;
import jakarta.ws.rs.NotFoundException;
import org.jboss.logging.Logger;
import org.modelmapper.ModelMapper;

import br.edu.ifrs.miguelzk.application.usecase.CreateAtendimentoUseCase;
import br.edu.ifrs.miguelzk.domain.entities.Atendimento;
import br.edu.ifrs.miguelzk.domain.entities.Animal;
import br.edu.ifrs.miguelzk.domain.repository.UsuarioRepository;
import br.edu.ifrs.miguelzk.domain.repository.AtendimentoRepository;
import br.edu.ifrs.miguelzk.domain.repository.AnimalRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CreateAtendimentoUseCaseImpl implements CreateAtendimentoUseCase {

    private AtendimentoRepository atendimentoRepository;
    private UsuarioRepository usuarioRepository;
    private AnimalRepository animalRepository;
    private MedVetRepository medVetRepository;
    private ModelMapper modelMapper;

    private static final Logger LOG = Logger.getLogger(CreateAtendimentoUseCaseImpl.class);

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

        // CRIANDO ATENDIMENTO
        Atendimento atendimento = new Atendimento();

        atendimento.setDataAtendimento(atendimentoRequest.getDataAtendimento());
        atendimento.setRelatoTutor(atendimentoRequest.getRelatoTutor());
        atendimento.setExameClinico(atendimentoRequest.getExameClinico());
        atendimento.setAvaliacaoExames(atendimentoRequest.getAvaliacaoExames());
        atendimento.setDiagnPresuntivo(atendimentoRequest.getDiagnPresuntivo());
        atendimento.setDiagnConclusivo(atendimentoRequest.getDiagnConclusivo());
        atendimento.setProcedimentoRealizado(atendimentoRequest.getProcedimentoRealizado());
        atendimento.setTratamentoInstituido(atendimentoRequest.getTratamentoInstituido());
        atendimento.setObservarProxConsulta(atendimentoRequest.getObservarProxConsulta());

        // SETANDO ANIMAL NO ATENDIMENTO
        Animal animal = animalRepository.findAnimalById(atendimentoRequest.getIdAnimal());
        atendimento.setAnimal(animal);

        // SETANDO USUARIOS NO ATENDIMENTO
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

        // SETANDO MEDVETS NO ATENDIMENTO
        Set<MedVet> medVets = new HashSet<>();
        atendimentoRequest.getCrmvMedVets().forEach((crmv) -> {
            try {
                MedVet medVet = medVetRepository.findMedVetByCrmv(crmv);
                medVets.add(medVet);
            } catch (NotFoundException e) {
                System.out.println("medVet com CRMV " + crmv + "não encontrado");
            }
        });
        atendimento.getMedVets().addAll(medVets);

        // SALVA ATENDIMENTO
        Atendimento savedatendimento = atendimentoRepository.save(atendimento);

        AtendimentoResponseDTO atendimentoResponseDTO = modelMapper.map(savedatendimento, AtendimentoResponseDTO.class);
        atendimentoResponseDTO.setUsuariosDTO(usuarios.stream().map(u ->
                modelMapper.map(u, UsuarioResponseDTO.class)).collect(Collectors.toSet()));
        LOG.info("ATENDIMENTORESPONSEDTO: " + atendimentoResponseDTO);

        // ADICIONANDO ATENDIMENTO AO CADASTRO DO ANIMAL
        animal.getAtendimentos().add(savedatendimento);

        // CONVERTE USUARIOS E MEDVETS EM DTOS
        savedatendimento.getUsuarios().stream()
                .map(usuario -> {
                    UsuarioResponseDTO dto = new UsuarioResponseDTO();
                    dto.setIdUsuario(usuario.getIdUsuario());
                    dto.setUserName(usuario.getUserName());
                    dto.setRole(usuario.getRole());
                    dto.setNomeCompleto(usuario.getNomeCompleto());
                    return dto;
                })
                .collect(Collectors.toSet());

//        LOG.info("COMO FICOU OS USUARIOS: " + savedatendimento.getUsuarios().toString());

        savedatendimento.getMedVets().stream()
                .map(medVet -> {
                    MedVetResponseDTO dto = new MedVetResponseDTO();
                    dto.setCrmv(medVet.getCrmv());
                    dto.setNomeCompleto(medVet.getNomeCompleto());
                    dto.setEspecialidade(medVet.getEspecialidade());
                    return dto;
                })
                .collect(Collectors.toSet());

//        LOG.info("COMO FICOU OS MEDVETS: " + savedatendimento.getMedVets().toString());

    //    return modelMapper.map(savedatendimento, AtendimentoResponseDTO.class);
        return AtendimentoResponseDTO.builder()
                .id(savedatendimento.getIdAtendimento())
                .animal(modelMapper.map(savedatendimento.getAnimal(), AnimalResponseDTO.class))
                .dataAtendimento(savedatendimento.getDataAtendimento())
                .relatoTutor(savedatendimento.getRelatoTutor())
                .exameClinico(savedatendimento.getExameClinico())
                .avaliacaoExames(savedatendimento.getAvaliacaoExames())
                .diagnPresuntivo(savedatendimento.getDiagnPresuntivo())
                .diagnConclusivo(savedatendimento.getDiagnConclusivo())
                .procedimentoRealizado(savedatendimento.getProcedimentoRealizado())
                .tratamentoInstituido(savedatendimento.getTratamentoInstituido())
                .observarProxConsulta(savedatendimento.getObservarProxConsulta())
                .usuariosDTO(usuarios.stream().map(u -> modelMapper.map(u, UsuarioResponseDTO.class))
                        .collect(Collectors.toSet()))
                .medVetsDTO(medVets.stream().map(m -> modelMapper.map(m, MedVetResponseDTO.class))
                        .collect(Collectors.toSet()))
                .build();



    }

}
