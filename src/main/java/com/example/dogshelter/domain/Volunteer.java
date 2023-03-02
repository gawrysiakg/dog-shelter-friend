package com.example.dogshelter.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//import javax.persistence.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "VOLUNTEER")
public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PASSWORD")
    private String password;

//    @Column(name = "ROLE")
//    private String role;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE")
    private int phone;

//
//    @OneToMany( fetch = FetchType.EAGER, cascade=CascadeType.ALL)//mappedBy = "user",
//    private List<Role> roles = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE")
    private Role role;

    @OneToMany(targetEntity = Walk.class,
            cascade = CascadeType.ALL,
//            fetch = FetchType.EAGER,
            mappedBy = "volunteer")
     private List<Walk> walkList = new ArrayList<>();


    public Volunteer(Long id, String firstName, String lastName, String name, String password, String email, int phone, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.role=role;
    }
}
