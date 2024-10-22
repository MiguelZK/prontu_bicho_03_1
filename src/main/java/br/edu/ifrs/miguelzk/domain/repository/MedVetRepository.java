package br.edu.ifrs.miguelzk.domain.repository;

import br.edu.ifrs.miguelzk.domain.entities.MedVet;

import java.util.List;

public interface MedVetRepository {
    
    MedVet findMedVetByCrmv(Long crmv);
    List<MedVet> findMedVetAll();
    MedVet save(MedVet medVet);
    MedVet update(MedVet medVet);
    void deleteMedVetById(Long id);
}
