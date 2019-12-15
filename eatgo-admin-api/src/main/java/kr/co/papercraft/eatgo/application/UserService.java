package kr.co.papercraft.eatgo.application;

import kr.co.papercraft.eatgo.domain.Model.User;
import kr.co.papercraft.eatgo.domain.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User addUser(User user){
        return userRepository.save(user);
    }

    public User updateUser(Long id, String email, String name, Long level){
        User user = userRepository.findById(id).orElse(null);

        if(email!=null)user.setEmail(email);
        if(name!=null)user.setName(name);
        if(level!=null)user.setLevel(level);

        return userRepository.save(user);
        //return userRepository.save(user);
    }

    public User deactiveUser(long id) {
        //TODO
        //userRepository.p
        User user = userRepository.findById(id).orElse(null);
        user.deactivate();
        return userRepository.save(user);
    }
}
