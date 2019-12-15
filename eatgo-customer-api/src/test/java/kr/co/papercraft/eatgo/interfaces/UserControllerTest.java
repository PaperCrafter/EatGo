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

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void create() throws Exception {
        User mockUser = User.builder()
                .id(1L)
                .name("paper")
                .email("paper@naver.com")
                .level(1L)
                .password("paper")
                .build();

        given(userService.register("paper","paper@naver.com", "paper")).willReturn(mockUser);

        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"paper\",\"email\":\"paper@naver.com\",\"password\":\"paper\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/users/1"));

        verify(userService).register(eq("paper"), eq("paper@naver.com"), eq("paper"));

    }
}