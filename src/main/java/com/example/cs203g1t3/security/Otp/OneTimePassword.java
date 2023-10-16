package com.example.cs203g1t3.security.Otp;

import com.example.cs203g1t3.models.User;
import jakarta.persistence.*;
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
    @OneToOne
    @JoinColumn(name="userid")
    private User user;
}
