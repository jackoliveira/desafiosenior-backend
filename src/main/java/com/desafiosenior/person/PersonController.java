package com.desafiosenior.person;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;

import com.desafiosenior.person.Person;
/**
 * personController
 */

@RestController
@RequestMapping("/people")
public class PersonController {

    @Autowired PersonRepository personRepository;

    @GetMapping
    public List<Person> getAll(){
        return personRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Person>> getById(@PathVariable(value = "id") Long id) throws NotFoundException {
        Optional<Person> person = personRepository.findById(id);
        if(person.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(person);
        } else {
            throw new NotFoundException("Pessoa nao encontrada com o id " + id);
        }
    }

    @PostMapping
    public ResponseEntity<Person> store(@RequestBody Person person) {
        Person tempPerson = personRepository.save(person);
        return ResponseEntity.status(HttpStatus.OK).body(tempPerson);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> update(
        @PathVariable(value = "id") Long id, @Valid @RequestBody Person personUpdated) throws NotFoundException {
        Person person = personRepository.findById(id).get();
        if(person.getId() == null) { throw new NotFoundException("Pessoa nao atualizada id " + id); }
        BeanUtils.copyProperties(personUpdated, person, "id");
        person = personRepository.save(person);
        return ResponseEntity.status(HttpStatus.OK).body(person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Person> delete(@PathVariable(value = "id") Long id) throws NotFoundException {
        Person person = personRepository.findById(id).get();
        if (person.getId() == null) {
            throw new NotFoundException("Pessoa nao deletada id " + id);
        }
        personRepository.delete(person);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}