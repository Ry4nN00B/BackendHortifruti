package me.ry4nn00b.hortifruti.Service;

import me.ry4nn00b.hortifruti.Model.UserModel;
import me.ry4nn00b.hortifruti.Repository.IUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final IUserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(IUserRepository repository) {
        this.repository = repository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    //Method's
    public UserModel register(UserModel user){

        //Encrypt password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);

    }

    public Optional<UserModel> authentic(String email, String password) {
        Optional<UserModel> user = repository.findByEmail(email);
        if (user.isPresent()) {
            boolean correctPassword = passwordEncoder.matches(password, user.get().getPassword());
            if (correctPassword) {
                return user;
            }
        }
        return Optional.empty();
    }



}
