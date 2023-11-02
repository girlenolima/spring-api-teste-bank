package com.leno.bank.domain.user;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.usertype.UserType;

import java.math.BigDecimal;

@Entity(name="users")
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String document;
    @Column(unique = true)
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private Type type;



    public User() {

    }
}
