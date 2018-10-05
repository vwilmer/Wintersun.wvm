package com.example.demo.onetomany;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class OneToManyCustomer {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OneToManyAddress> addresses = new ArrayList<>();

}
