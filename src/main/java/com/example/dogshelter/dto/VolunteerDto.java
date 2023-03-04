package com.example.dogshelter.dto;

import com.example.dogshelter.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Role role;
}
