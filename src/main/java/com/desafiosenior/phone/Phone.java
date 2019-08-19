package com.desafiosenior.phone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.desafiosenior.person.Person;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Telefone
 */
@NoArgsConstructor
@Entity
@Data
@Table(name = "phone")
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "id")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
        private Long id;

    @JsonBackReference
    @ManyToOne @JoinColumn(name="person_id") private Person person;
    @Column(name = "type", nullable = false) private String type;
    @Column(name = "ddi", nullable = false) private String ddi;
    @Column(name = "ddd", nullable = false) private String ddd;
    @Column(name = "number", nullable = false) private String number;
    @Column(name = "ext", nullable = true) private String ext;
 
}