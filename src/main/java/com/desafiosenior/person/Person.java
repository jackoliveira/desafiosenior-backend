package com.desafiosenior.person;

import com.desafiosenior.address.Address;
import com.desafiosenior.phone.Phone;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Setter
@Entity
@Table(name = "person")
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "id")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial") private Long id;
    
    @Column(name = "name", nullable = false) private String name;
    @Column(name = "age", nullable = false) private Integer age;

    @JsonManagedReference
    @OneToOne(mappedBy = "person", targetEntity = Address.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Address address;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "person", targetEntity = Phone.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Phone> phones = new ArrayList<Phone>();

}