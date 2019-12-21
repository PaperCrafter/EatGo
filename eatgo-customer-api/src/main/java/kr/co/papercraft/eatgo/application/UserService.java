package kr.co.papercraft.eatgo.application;

import kr.co.papercraft.eatgo.domain.Model.User;
import kr.co.papercraft.eatgo.domain.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(String name, String email, String password){
        Optional<User> existed = userRepository.findByEmail(email);
        if(existed.isPresent()){
            throw new EmailExistedException(email);
        }

        String encodedPassword = passwordEncoder.encode(password);

        User user = User.builder()
                .name(name)
                .email(email)
                .password(encodedPassword)
                .level(1L)
                .build();

        return userRepository.save(user);
    }

    public User authenticate(String email, String password){
        User user = userRepository.findByEmail(email).orElseThrow(()->new EmailNotExistedException(email));

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new PasswordWrongException();
        }
        String encodedPassword = passwordEncoder.encode(password);

        return user;
    }
}
