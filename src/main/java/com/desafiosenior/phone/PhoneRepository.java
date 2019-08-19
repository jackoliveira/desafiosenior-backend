package com.desafiosenior.phone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.desafiosenior.phone.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long>{
    List<Phone> findByPersonId(Long person_id);
}