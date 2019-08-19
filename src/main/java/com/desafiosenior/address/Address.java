package com.desafiosenior.address;

import com.desafiosenior.address.Address;
import com.desafiosenior.person.Person;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@NoArgsConstructor
@Data
@Entity
@Setter
@Table(name = "address")
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "id")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial") private Long id;
    
    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, optional = false) @JoinColumn(name="person_id") private Person person;
    @Column(name = "type", nullable = false) private String type;
    @Column(name = "name", nullable = false) private String name;
    @Column(name = "number", nullable = false) private String number;
    @Column(name = "zipcode", nullable = false) private String zipcode;

}