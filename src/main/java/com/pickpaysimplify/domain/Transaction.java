package com.pickpaysimplify.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity(name = "Users")
@Table(name = "Users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    @Column(name = "amount")
    private BigDecimal amount;

    @ManyToOne
    private Users sender;

    @ManyToOne
    private Users reciver;

    @Column(name = "created")
    private LocalDateTime created;
}
