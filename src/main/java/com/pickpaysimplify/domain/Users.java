package com.pickpaysimplify.domain;

import com.pickpaysimplify.dto.UserDTO;
import com.pickpaysimplify.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity(name = "UsersEntity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "document", unique = true)
    private String document;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private UserType type;

    @Column(name = "balance")
    private BigDecimal balence;

    @Column(name = "created")
    private LocalDateTime created;

    public Users(UserDTO data) {
        this.name = data.name();
        this.document = data.document();
        this.email = data.email();
        this.balence = data.balence();
        this.type = data.type();
        this.password = data.password();
        this.created = LocalDateTime.now();
    }
}
