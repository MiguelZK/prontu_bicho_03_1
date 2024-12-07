package br.edu.ifrs.miguelzk.domain.repository;

import java.util.List;
import br.edu.ifrs.miguelzk.domain.entities.Atendimento;

public interface AtendimentoRepository {

    Atendimento findAtendimentoById(Long id);
    List<Atendimento> findAtendimentoAll();
    Atendimento save(Atendimento atendimento);
    Atendimento update(Atendimento Atendimento);
    void deleteAtendimentoById(Long id);
}
