package com.leno.bank.services;


import com.leno.bank.domain.user.Type;
import com.leno.bank.domain.user.User;
import com.leno.bank.dtos.UserDTO;
import com.leno.bank.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

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


    public User createUser(UserDTO userData){
        User newUser = new User(userData);
        this.saveUser(newUser);

        return  newUser;
    }

    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

}
