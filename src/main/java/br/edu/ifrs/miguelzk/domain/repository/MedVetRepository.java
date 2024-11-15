package br.edu.ifrs.miguelzk.domain.repository;

import br.edu.ifrs.miguelzk.domain.entities.MedVet;

import java.util.List;

public interface MedVetRepository {
    
    MedVet save(MedVet medVet);

    MedVet findMedVetByCrmv(Long crmv);
    MedVet findMedVetById(Long id);
    List<MedVet> findMedVetAll();
    List<MedVet> findMedVetByLogin(String userName);
    List<MedVet> findMedVetByName(String parteDoNome);

    MedVet update(MedVet medVet);

    void deleteMedVetByCrmv(Long crmv);

}
