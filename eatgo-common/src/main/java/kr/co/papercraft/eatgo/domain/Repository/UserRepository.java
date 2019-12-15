package kr.co.papercraft.eatgo.domain.Repository;


import kr.co.papercraft.eatgo.domain.Model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long>{

    List<User> findAll();

    User save(User user);
}
