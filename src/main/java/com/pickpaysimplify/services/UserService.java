package com.pickpaysimplify.services;

import com.pickpaysimplify.domain.Users;
import com.pickpaysimplify.dto.UserDTO;
import com.pickpaysimplify.enums.UserType;
import com.pickpaysimplify.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public Users getUserById(Long sender) throws Exception {
        return repository.findById(sender).orElseThrow(() -> new Exception("User not found!"));
    }

    public void validateTransaction(Users sender, BigDecimal amount) throws Exception {
        if (sender.getType() == UserType.MERCHANT) {
            throw new Exception("User type merchant, is not available for send values");
        }

        if (sender.getBalence().compareTo(amount) < 0) {
            throw new Exception("Insufficient balance");
        }
    }

    public Users saveNewUser(UserDTO data) {
        Users user = new Users(data);
        return this.saveUser(user);
    }

    public Users saveUser(Users user) {
        return repository.save(user);
    }

    public List<Users> listAllUser() throws Exception{
        return repository.findAll();
    }
}
