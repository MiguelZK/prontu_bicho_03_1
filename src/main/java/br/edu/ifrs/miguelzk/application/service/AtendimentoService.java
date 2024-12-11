package br.edu.ifrs.miguelzk.application.service;

import java.util.List;

import br.edu.ifrs.miguelzk.application.dto.*;
import br.edu.ifrs.miguelzk.application.usecase.CreateAtendimentoUseCase;
import br.edu.ifrs.miguelzk.application.usecase.DeleteAtendimentoUseCase;
import br.edu.ifrs.miguelzk.application.usecase.FindAtendimentoUseCase;
import br.edu.ifrs.miguelzk.application.usecase.UpdateAtendimentoUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AtendimentoService {

    private final CreateAtendimentoUseCase createAtendimentoUseCase;
    private final FindAtendimentoUseCase findAtendimentoUseCase;
    private final DeleteAtendimentoUseCase deleteAtendimentoUseCase;
    private final UpdateAtendimentoUseCase updateAtendimentoUseCase;

    @Inject
    public AtendimentoService(CreateAtendimentoUseCase createAtendimentoUseCase
            , FindAtendimentoUseCase findAtendimentoUseCase
            , UpdateAtendimentoUseCase updateAtendimentoUseCase
            , DeleteAtendimentoUseCase deleteAtendimentoUseCase) {
        this.createAtendimentoUseCase = createAtendimentoUseCase;
        this.findAtendimentoUseCase = findAtendimentoUseCase;
        this.updateAtendimentoUseCase = updateAtendimentoUseCase;
        this.deleteAtendimentoUseCase = deleteAtendimentoUseCase;
    }

    @Transactional
    public AtendimentoResponseDTO createAtendimento(AtendimentoRequestDTO request) {
        return createAtendimentoUseCase.execute(request);
    }

    public AtendimentoTodosResponseDTO findAtendimentoById(Long id) {
        try {
            return findAtendimentoUseCase.execute(id);
        } catch (NotFoundException e) {
            throw new NotFoundException("Atendimento não encontrado");
        }
    }

    public List<AtendimentoTodosResponseDTO> findAtendimentoAll() {
        return findAtendimentoUseCase.execute();
    }


    @Transactional
    public AtendimentoResponseDTO updateAtendimento(Long id, AtendimentoRequestDTO request) {
        return updateAtendimentoUseCase.execute(id, request);
    }

    @Transactional
    public void deleteAtendimentoById(Long id) {
        deleteAtendimentoUseCase.execute(id);
    }

}
