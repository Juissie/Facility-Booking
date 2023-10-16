package com.example.cs203g1t3.security.Otp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class OneTimePassword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotBlank
    private Long id;
    @NotBlank
    private int oneTimePasswordCode;
    @NotBlank
    private Date expires;
}
