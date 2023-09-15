package com.example.cs203g1t3.models;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Facility {

    @Id
    @GeneratedValue
    private Long facilityId;
    
}
