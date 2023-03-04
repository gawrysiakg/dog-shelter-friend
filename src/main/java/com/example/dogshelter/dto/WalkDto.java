package com.example.dogshelter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WalkDto {

    private Long id;
    private LocalDate walkDate;
    private String volunteerName;
    private String dogName;
}
