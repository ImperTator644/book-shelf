package com.luximed.repository;

import com.luximed.model.PersonalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PersonalDataRepository extends JpaRepository<PersonalData, Integer> {

    PersonalData findPersonalDataByPesel(String pesel);

    @Transactional
    void deleteByPesel(String pesel);
}
