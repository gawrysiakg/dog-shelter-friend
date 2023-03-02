package com.example.dogshelter.dto;

import com.example.dogshelter.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VolunteerDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String name;
    private String password;
    private String email;
    private int phone;
   // private List<Role> roles = new ArrayList<>();
    private Role role;


//    public VolunteerDto(Long id, String firstName, String lastName, String name, String password, String email, int phone, Role role) {
//        this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.name = name;
//        this.password = password;
//        this.email = email;
//        this.phone = phone;
//        this.role=role;
//    }
}
