package com.leno.bank.services;


import com.leno.bank.domain.user.Type;
import com.leno.bank.domain.user.User;
import com.leno.bank.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public void validateTrasaction(User sender, BigDecimal valor) throws Exception {

        if (sender.getType() == Type.LOJISTA) {
            throw new Exception("Lojista nao esta autorizado");
        }

        if (sender.getBalance().compareTo(valor) < 0) {
            throw new Exception("Saldo insuficiente");

        }

    }

    public User findUserById(Long id) throws Exception {
       return this.userRepository.findUserById(id).orElseThrow(()-> new Exception("Usuario nao encontrado"));
    }


    public void saveUser(User user){
        this.userRepository.save(user);
    }

}
