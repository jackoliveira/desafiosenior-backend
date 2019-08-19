package com.desafiosenior.phone;

import java.util.List;
import java.util.Optional;

import javax.management.relation.RelationNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.Null;

import org.springframework.beans.BeanUtils;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;

import com.desafiosenior.person.Person;
import com.desafiosenior.person.PersonRepository;
import com.desafiosenior.phone.PhoneRepository;
/**
 * PessoaController
 */

@RestController
@RequestMapping("/people")
public class PhoneController {

    @Autowired PhoneRepository phoneRepository;
    @Autowired PersonRepository personRepository;

    @GetMapping("/{person_id}/phone")
    public ResponseEntity<List<Phone>> getById(@PathVariable(value = "person_id") Long person_id) {
        if(!personRepository.existsById(person_id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<Phone> phone = phoneRepository.findByPersonId(person_id);
        return ResponseEntity.status(HttpStatus.OK).body(phone);
    }

    @PostMapping("/{person_id}/phone")
    public ResponseEntity<Object> store(@Valid @PathVariable Long person_id, @RequestBody Phone phone) {
        Person person = personRepository.findById(person_id).get();
        phone.setPerson(person);
        Object tmpPhone = phoneRepository.save(phone);
        return ResponseEntity.status(HttpStatus.OK).body(tmpPhone);
    }

    @PutMapping("/{person_id}/phone/")
    public ResponseEntity<Object> update(
        @PathVariable(value = "id") Long id, @Valid @PathVariable Long person_id, @RequestBody Phone phoneUpdated) {
        Person person = personRepository.findById(person_id).get();
        Optional<Phone> phone = phoneRepository.findById(phoneUpdated.getId());
        if (phone.isPresent()) {
            phone.setDdd(phoneUpdated.getDdd());
            phone.setDdi(phoneUpdated.getDdi());
            phone.setType(phoneUpdated.getType());
            phone.setExt(phoneUpdated.getExt());
            phone.setNumber(phoneUpdated.getNumber());
            Object tmpPhone = phoneRepository.save(phone);

            return ResponseEntity.status(HttpStatus.OK).body(tmpPhone);
        } else {
            throw new NotFoundException("Nao foi possivel atualizar o telefone id " + id);
        }
    }

    // Implement this
    @DeleteMapping("/{person_id}/phone/{id}")
    public ResponseEntity<Phone> delete(@PathVariable(value = "person_id") Long person_id) {
        List<Phone> phone = phoneRepository.findByPersonId(person_id);
        if (phone == null) { return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); }
         // phoneRepository.delete(phone);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}