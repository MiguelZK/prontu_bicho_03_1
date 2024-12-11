package br.edu.ifrs.miguelzk.application.usecase.impl;

import br.edu.ifrs.miguelzk.application.dto.*;
import br.edu.ifrs.miguelzk.application.usecase.ImunizanteUseCase;
import br.edu.ifrs.miguelzk.application.utils.ConverteDTOParaEntity;
import br.edu.ifrs.miguelzk.domain.entities.Animal;
import br.edu.ifrs.miguelzk.domain.entities.Atendimento;
import br.edu.ifrs.miguelzk.domain.entities.Imunizante;
import br.edu.ifrs.miguelzk.domain.enums.TipoImunizante;
import br.edu.ifrs.miguelzk.domain.repository.*;
import br.edu.ifrs.miguelzk.infrastructure.exception.ObjetoNaoEncontradoException;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import org.modelmapper.ModelMapper;

import java.util.*;
import java.util.stream.Collectors;

public class ImunizanteUseCaseImpl implements ImunizanteUseCase {

    private final ImunizanteRepository imunizanteRepository;
    private final AnimalRepository animalRepository;
    private final AtendimentoRepository atendimentoRepository;
    private final ModelMapper modelMapper;

    public ImunizanteUseCaseImpl(ImunizanteRepository imunizanteRepository, AnimalRepository animalRepository
            , AtendimentoRepository atendimentoRepository, ModelMapper modelMapper) {
        this.imunizanteRepository = imunizanteRepository;
        this.animalRepository = animalRepository;
        this.atendimentoRepository = atendimentoRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * @implNote Se o imunizante foi aplicado em atendimento, deve ser criado atendimento antes do imunizante.
     * No caso de ser uma vacina, é obrigatório ter atendimento, pois deve ser aplicado por medVet.
     */
    @Override
    public ImunizanteComAnimalResponseDTO createImunizante(ImunizanteRequestDTO dto) {
//        Imunizante imunizante = modelMapper.map(dto, Imunizante.class);
        Imunizante imunizante = ConverteDTOParaEntity.dtoParaImunizante(dto);
        imunizante.setIdImunizante(null);
        imunizante.setRegistroAtivo(true);

        // VERIFICA ANIMAL IMUNIZADO
        Optional<Animal> animalOpt = Optional.ofNullable(animalRepository.findAnimalById(dto.getIdAnimal()));
        if (animalOpt.isEmpty()) {
            throw new ObjetoNaoEncontradoException("Animal não encontrado");
        }
        Animal animal = animalOpt.get();

        Imunizante savedImunizante = imunizanteRepository.save(imunizante);

        ImunizanteComAnimalResponseDTO imunizanteComAnimalResponseDTO =
                modelMapper.map(savedImunizante, ImunizanteComAnimalResponseDTO.class);

        // VERIFICA ATENDIMENTO, SE HOUVER
        if (dto.getTipoImunizante() == TipoImunizante.VACINA || dto.getIdAtendimento() != null) {
            Optional<Atendimento> atendimentoOpt =
                    Optional.ofNullable(atendimentoRepository.findAtendimentoById(dto.getIdAtendimento()));
            if (atendimentoOpt.isEmpty()) {
                throw new ObjetoNaoEncontradoException("Atendimento não encontrado");
            } else if (dto.getIdAtendimento() == null) {
                throw new IllegalArgumentException("Registro de vacina precisa de id de atendimento válido");
            }
            Atendimento atendimento = atendimentoOpt.get();

            if (!Objects.equals(atendimento.getAnimal().getIdAnimal(), dto.getIdAnimal())) {
                throw new BadRequestException("O animal deve ser o mesmo do atendimento");
            }

            atendimento.getImunizantes().add(savedImunizante);
            atendimentoRepository.update(atendimento);
            imunizanteComAnimalResponseDTO.setAtendimentoParaImunizanteResponseDTO(modelMapper
                    .map(atendimento, AtendimentoParaImunizanteResponseDTO.class));
            imunizanteComAnimalResponseDTO.getAtendimentoParaImunizanteResponseDTO().setUsuariosDTO(
                    atendimento.getUsuarios().stream().map(u -> modelMapper.map(u, UsuarioSemRolesResponseDTO.class))
                            .collect(Collectors.toSet())
            );
            imunizanteComAnimalResponseDTO.getAtendimentoParaImunizanteResponseDTO().setMedVetsDTO(
                    atendimento.getMedVets().stream().map(m -> modelMapper.map(m, MedVetResponseDTO.class))
                            .collect(Collectors.toSet())
            );
        }

        animal.getImunizantes().add(savedImunizante);
        animalRepository.update(animal);

        imunizanteComAnimalResponseDTO.setAnimalResponseDTO(modelMapper.map(animal, AnimalResponseDTO.class));
        return imunizanteComAnimalResponseDTO;
    }

    @Override
    public ImunizanteTodasResponseDTO findImunizanteById(Long id) {
        try {
            Imunizante imunizante = imunizanteRepository.findImunizanteById(id);

            if (imunizante == null) {
                throw new NotFoundException("Imunizante id " + id + " não encontrado");
            }
            ImunizanteTodasResponseDTO imunizanteTodasResponseDTO =
                    modelMapper.map(imunizante, ImunizanteTodasResponseDTO.class);
            return imunizanteTodasResponseDTO;
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Imunizante não encontrado");
        }
    }

    @Override
    public List<ImunizanteTodasResponseDTO> listAllImunizantes() {
        List<ImunizanteTodasResponseDTO> imunizanteTodasResponseDTOS = new ArrayList<>();
        List<Imunizante> listImunizante = imunizanteRepository.findImunizanteAll();

        for (Imunizante imunizante : listImunizante) {
            ImunizanteTodasResponseDTO imunizanteTodasResponseDTO =
                    modelMapper.map(imunizante, ImunizanteTodasResponseDTO.class);
            imunizanteTodasResponseDTOS.add(imunizanteTodasResponseDTO);
        }

        return imunizanteTodasResponseDTOS;
    }

    /**
     * @implNote Se o imunizante foi aplicado em atendimento, deve ser criado atendimento antes do imunizante.
     * No caso de ser uma vacina, é obrigatório ter atendimento, pois deve ser aplicado por medVet.
     */
    @Override
    public ImunizanteComAnimalResponseDTO updateImunizante(Long id, ImunizanteRequestDTO dto) {

        Imunizante imunizanteExistente = imunizanteRepository.findImunizanteById(id);
        if (imunizanteExistente == null) {
            throw new ObjetoNaoEncontradoException("Imunizante não encontrado");
        }

        Animal animal = animalRepository.findAnimalById(dto.getIdAnimal());
        if (animal == null) {
            throw new ObjetoNaoEncontradoException("Animal não encontrado");
        }

        Imunizante imunizante = modelMapper.map(dto, Imunizante.class);
        imunizante.setIdImunizante(null);
        imunizante.setRegistroAtivo(true);
        Imunizante savedImunizante = imunizanteRepository.save(imunizante);

        imunizanteExistente.setRegistroAtivo(false);

        animal.getImunizantes().add(savedImunizante);
        animalRepository.update(animal);
        ImunizanteComAnimalResponseDTO imunizanteComAnimalResponseDTO =
                modelMapper.map(savedImunizante, ImunizanteComAnimalResponseDTO.class);

        // VERIFICA ATENDIMENTO, SE HOUVER
        if (dto.getTipoImunizante() == TipoImunizante.VACINA || dto.getIdAtendimento() != null) {
            Optional<Atendimento> atendimentoOpt =
                    Optional.ofNullable(atendimentoRepository.findAtendimentoById(dto.getIdAtendimento()));
            if (atendimentoOpt.isEmpty()) {
                throw new ObjetoNaoEncontradoException("Atendimento não encontrado");
            }
            Atendimento atendimento = atendimentoOpt.get();

            if (!Objects.equals(atendimento.getAnimal().getIdAnimal(), dto.getIdAnimal())) {
                throw new BadRequestException("O animal deve ser o mesmo do atendimento");
            }

            atendimento.getImunizantes().add(savedImunizante);
            atendimentoRepository.update(atendimento);
            imunizanteComAnimalResponseDTO.setAtendimentoParaImunizanteResponseDTO(modelMapper
                    .map(atendimento, AtendimentoParaImunizanteResponseDTO.class));
            imunizanteComAnimalResponseDTO.getAtendimentoParaImunizanteResponseDTO().setUsuariosDTO(
                    atendimento.getUsuarios().stream().map(u -> modelMapper.map(u, UsuarioSemRolesResponseDTO.class))
                            .collect(Collectors.toSet())
            );
            imunizanteComAnimalResponseDTO.getAtendimentoParaImunizanteResponseDTO().setMedVetsDTO(
                    atendimento.getMedVets().stream().map(m -> modelMapper.map(m, MedVetResponseDTO.class))
                            .collect(Collectors.toSet())
            );
        }

        imunizanteComAnimalResponseDTO.setAnimalResponseDTO(modelMapper.map(animal, AnimalResponseDTO.class));
        return imunizanteComAnimalResponseDTO;
    }

    @Override
    public void deleteImunizante(Long id) {
        Optional<Imunizante> imunizanteOpt = Optional.ofNullable(imunizanteRepository.findImunizanteById(id));
        if (imunizanteOpt.isEmpty()) {
            throw new ObjetoNaoEncontradoException("Imunizante não Encontrado.");
        }
        Imunizante imunizante = imunizanteOpt.get();
        imunizanteRepository.deleteImunizanteById(imunizante.getIdImunizante());
    }
}
