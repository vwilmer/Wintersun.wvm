package com.example.demo.onetomany;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class OneToManyAddress {

    @GeneratedValue
    @Id
    private Long id;

    @Column
    private String street;

}
