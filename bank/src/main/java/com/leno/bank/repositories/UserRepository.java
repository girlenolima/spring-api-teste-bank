package com.leno.bank.repositories;

import com.leno.bank.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User > findyUserByDocument(String document);
    Optional<User > findyUserById(Long id);

}
