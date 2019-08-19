package com.desafiosenior.address;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafiosenior.person.Person;
import com.desafiosenior.person.PersonRepository;
import com.desafiosenior.address.AddressRepository;
/**
 * PessoaController
 */

@RestController
@RequestMapping("/people")
public class AddressController {

    @Autowired AddressRepository addressRepository;
    @Autowired PersonRepository personRepository;

    @GetMapping("/{person_id}/address")
    public ResponseEntity<Address> getById(@PathVariable(value = "person_id") Long person_id) {
        if(!personRepository.existsById(person_id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Address address = addressRepository.findByPersonId(person_id);
        return ResponseEntity.status(HttpStatus.OK).body(address);
    }

    @PostMapping("/{person_id}/address")
    public ResponseEntity<Object> store(@Valid @PathVariable Long person_id, @RequestBody Address address) {
        Person person = personRepository.findById(person_id).get();
        address.setPerson(person);
        Object tmpAddress = addressRepository.save(address);
        return ResponseEntity.status(HttpStatus.OK).body(tmpAddress);
    }

    @PutMapping("/{person_id}/address/")
    public ResponseEntity<Object> update(
        @PathVariable(value = "id") Long id, @Valid @PathVariable Long person_id, @RequestBody Address addressUpdated) {
        Person person = personRepository.findById(person_id).get();
        Address address = person.getAddress();
        address.setName(addressUpdated.getName());
        address.setNumber(addressUpdated.getNumber());
        address.setType(addressUpdated.getType());
        address.setZipcode(addressUpdated.getZipcode());
        
        Object tmpAddress = addressRepository.save(address);
        return ResponseEntity.status(HttpStatus.OK).body(tmpAddress);
    }

    @DeleteMapping("/{person_id}/address")
    public ResponseEntity<Address> delete(@PathVariable(value = "person_id") Long person_id) {
        Address address = addressRepository.findByPersonId(person_id);
        if (address == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        addressRepository.delete(address);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}