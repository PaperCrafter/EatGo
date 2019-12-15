package kr.co.papercraft.eatgo.application;

import kr.co.papercraft.eatgo.domain.Model.User;
import kr.co.papercraft.eatgo.domain.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(String name, String email, String password){
        Optional<User> existed = userRepository.findByEmail(email);
        if(existed.isPresent()){
            throw new EmailExistedException(email);
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);

        User user = User.builder()
                .name(name)
                .email(email)
                .password(encodedPassword)
                .level(1L)
                .build();
        return userRepository.save(user);
    }
}
