package br.edu.ifrs.miguelzk.application.usecase.impl;

import br.edu.ifrs.miguelzk.application.dto.MedVetResponseDTO;
import br.edu.ifrs.miguelzk.application.usecase.FindMedVetUseCase;
import br.edu.ifrs.miguelzk.domain.entities.MedVet;
import br.edu.ifrs.miguelzk.domain.repository.MedVetRepository;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import org.jboss.logging.Logger;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class FindMedVetUseCaseImpl implements FindMedVetUseCase {

    private final MedVetRepository medVetRepository;
    private final ModelMapper modelMapper;
    private static final Logger LOG = Logger.getLogger(FindMedVetUseCaseImpl.class);

    public FindMedVetUseCaseImpl(MedVetRepository medVetRepository, ModelMapper modelMapper) {
        this.medVetRepository = medVetRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public MedVetResponseDTO execute(Long id) {
        try {
            MedVet medVet = medVetRepository.findMedVetById(id);

            if (medVet == null) {
                LOG.info("FindMedVetUseCaseImpl - Linha 35 - if");
                throw new NotFoundException("MedVet não encontrado");
            }
            return modelMapper.map(medVet, MedVetResponseDTO.class);
        } catch (IllegalArgumentException e) {
            LOG.info("FindMedVetUseCaseImpl - Linha 40 - catch - IllegalArgumentException");
            throw new BadRequestException("MedVet não encontrado");
        }
    }

    @Override
    public MedVetResponseDTO executeByCrmv(Long crmv) {
        try {
            MedVet medVet = medVetRepository.findMedVetByCrmv(crmv);

            if (medVet == null) {
                LOG.info("FindMedVetUseCaseImpl - Linha 35 - if");
                throw new NotFoundException("MedVet não encontrado");
            }
            return modelMapper.map(medVet, MedVetResponseDTO.class);
        } catch (IllegalArgumentException e) {
            LOG.info("FindMedVetUseCaseImpl - Linha 40 - catch - IllegalArgumentException");
            throw new BadRequestException("MedVet não encontrado");
        }
    }


    // A SER VERIFICADO SE A BUSCA POR NOME DE USUARIO ENCONTRA MEDVETS TBM
    @Override
    public List<MedVetResponseDTO> execute(String userName) {
        List<MedVetResponseDTO> listResponseDTO = new ArrayList<>();
        List<MedVet> listMedVet = medVetRepository.findMedVetByLogin(userName);

        for (MedVet medVet : listMedVet) {
            listResponseDTO.add(modelMapper.map(medVet, MedVetResponseDTO.class));
        }
        return listResponseDTO;
    }

    @Override
    public List<MedVetResponseDTO> executeByNome(String nomeCompleto) {
        List<MedVetResponseDTO> listResponseDTO = new ArrayList<>();
        List<MedVet> listMedVet = medVetRepository.findMedVetByName(nomeCompleto);

        for (MedVet medVet : listMedVet) {
            listResponseDTO.add(modelMapper.map(medVet, MedVetResponseDTO.class));
        }
        return listResponseDTO;
    }

    @Override
    public List<MedVetResponseDTO> execute() {
        List<MedVetResponseDTO> listResponseDTOs = new ArrayList<>();
        List<MedVet> listMedVetEtity = medVetRepository.findMedVetAll();

        for (MedVet medVet : listMedVetEtity) {
            listResponseDTOs.add(modelMapper.map(medVet, MedVetResponseDTO.class));
        }

        return listResponseDTOs;
    }

}
