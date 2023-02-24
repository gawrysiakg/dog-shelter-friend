package com.example.dogshelter.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "WALKS")
public class Walk {

    @Id
    @GeneratedValue
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "EXIT_TIME")
    private LocalDateTime exitTime;

    @Column(name = "RETURN_TIME")
    private LocalDateTime returnTime;

    @ManyToOne
    @JoinColumn(name = "DOG_ID")
    private Dog dog;

    @ManyToOne
    @JoinColumn(name = "VOLUNTEER_ID")
    private Volunteer volunteer;
}
