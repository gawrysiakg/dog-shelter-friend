package com.example.dogshelter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WalkDto {

    private Long id;
    private LocalDateTime exitTime;
    private LocalDateTime returnTime;
    private Long dogId;
    private Long volunteerId;
}
