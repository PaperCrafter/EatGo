package kr.co.papercraft.eatgo.interfaces;

import kr.co.papercraft.eatgo.application.UserService;
import kr.co.papercraft.eatgo.domain.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/users")
    public ResponseEntity<?> create(@RequestBody User resource) throws URISyntaxException {
        String name = resource.getName();
        String email = resource.getEmail();
        String password = resource.getPassword();

        User user = userService.register(name, email, password);

        String url = "/users/"+user.getId();
        return ResponseEntity.created(new URI(url)).body("{}");
    }
}
