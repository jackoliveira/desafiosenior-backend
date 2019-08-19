package com.desafiosenior.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafiosenior.address.Address;

@Repository
public interface AddressRepository<Person> extends JpaRepository<Address, Long>{
    Address findByPersonId(Long person_id);
}