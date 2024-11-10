package br.edu.ifrs.miguelzk.application.usecase.impl;

import br.edu.ifrs.miguelzk.application.dto.MedVetRequestDTO;
import br.edu.ifrs.miguelzk.application.dto.MedVetResponseDTO;
import br.edu.ifrs.miguelzk.application.usecase.CreateMedVetUseCase;
import br.edu.ifrs.miguelzk.domain.entities.MedVet;
import br.edu.ifrs.miguelzk.domain.repository.MedVetRepository;
import br.edu.ifrs.miguelzk.infrastructure.persistence.DataLoader;
import io.quarkus.elytron.security.common.BcryptUtil;
import org.jboss.logging.Logger;
import org.modelmapper.ModelMapper;

public class CreateMedVetUseCaseImpl implements CreateMedVetUseCase {

    private final MedVetRepository medVetRepository;
    private final ModelMapper modelMapper;
    private static final Logger LOG = Logger.getLogger(DataLoader.class);

    public CreateMedVetUseCaseImpl(MedVetRepository medVetRepository, ModelMapper modelMapper) {
        this.medVetRepository = medVetRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public MedVetResponseDTO execute(MedVetRequestDTO request) {
        MedVet medVet = modelMapper.map(request, MedVet.class);

        LOG.info(request.toString());

        medVet.setPassword(BcryptUtil.bcryptHash(medVet.getPassword()));
        MedVet saveMedVet = medVetRepository.save(medVet);
        return modelMapper.map(saveMedVet, MedVetResponseDTO.class);
    }

}
