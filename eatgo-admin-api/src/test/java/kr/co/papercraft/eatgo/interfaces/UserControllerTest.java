package kr.co.papercraft.eatgo.interfaces;

import kr.co.papercraft.eatgo.application.UserService;
import kr.co.papercraft.eatgo.domain.Model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void list() throws Exception {

        List<User> users = new ArrayList<>();
        users.add(User.builder()
                .email("papercrafter@naver.com")
                .name("paper")
                .level(1L)
                .build());
        given(userService.getUsers()).willReturn(users);

        mvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("paper")));
    }
//    {"email":"ppaper@naver.com", "name":"ppap", "level":3}
    @Test
    public void create() throws Exception {

        String email = "ppaper@naver.com";
        String name = "ppap";

        User user = User.builder()
                .id(1L)
                .email(email)
                .name(name)
                .level(3L)
                .build();

        given(userService.addUser(any())).willReturn(user);

        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"ppaper@naver.com\", \"name\":\"ppap\", \"level\":3}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/users/1"))
                .andExpect(content().string("{}"));;
//
//
//        verify(userService).addUser(user);

    }

    @Test
    public void update() throws Exception{
        Long id = 1L;
        String email = "ppaper@naver.com";
        String name = "ppap";
        Long level = 3L;

        User user = User.builder()
                .id(id)
                .email(email)
                .name(name)
                .level(level)
                .build();

//        given(userService.updateUser(user)).willReturn(user);

        mvc.perform(patch("/users/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"email\":\"ppaper@naver.com\",\"name\":\"ppap\", \"level\":3}"))
                .andExpect(status().isOk());

        verify(userService).updateUser(eq(id), eq(email), eq(name), eq(level));
//                .andExpect(header().string("location", "/users/1"))
//                .andExpect(content().string("{}"));;
    }

    @Test
    public void deactivate() throws Exception {
        mvc.perform(delete("/users/1"))
                .andExpect(status().isOk());

        verify(userService).deactiveUser(1L);
    }

}
