package ru.kartanerud.buba.karta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kartanerud.buba.karta.models.User;
import ru.kartanerud.buba.karta.repo.UserRepo;

import java.security.Principal;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }
    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepo.findByEmail(principal.getName());
    }
}
