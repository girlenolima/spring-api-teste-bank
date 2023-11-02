package com.leno.bank.domain.user;


import com.leno.bank.dtos.UserDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.usertype.UserType;

import java.math.BigDecimal;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
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

    public User(UserDTO userData) {
        this.firstName = userData.firstName();
        this.lastName = userData.lastName();
        this.balance = userData.balance();
        this.type = userData.type();
        this.password = userData.password();
        this.email = userData.email();

    }
}
