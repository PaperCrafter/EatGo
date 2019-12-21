package kr.co.papercraft.eatgo.interfaces;

import kr.co.papercraft.eatgo.application.EmailNotExistedException;
import kr.co.papercraft.eatgo.application.PasswordWrongException;
import kr.co.papercraft.eatgo.application.UserService;
import kr.co.papercraft.eatgo.domain.Model.User;
import kr.co.papercrafter.eatgo.utils.JwtUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(SessionController.class)
public class SessionControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtUtil jwtUtil;

    @Test
    public void createWithVaildAttribute() throws Exception {
        Long id = 1L;
        String name = "paper";
        String email = "paper@naver.com";
        String password = "paper";

        User mockUser = User.builder()
                .id(id)
                .name(name)
                .password("ACCESSTOKEN")
                .build();

        given(userService.authenticate(email, password)).willReturn(mockUser);

        given(jwtUtil.createToken(id, name)).willReturn("header.payload.signature");

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"paper@naver.com\",\"password\":\"paper\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/session"))
                .andExpect(content().string(containsString("{\"accessToken\":\"header.payload.signature\"}")))
                .andExpect(content().string(containsString(".")));

        verify(userService).authenticate(eq(email), eq(password));
    }

    @Test
    public void createWithNotExistedEmail() throws Exception {
        given(userService.authenticate("ppap@naver.com", "x"))
                .willThrow(EmailNotExistedException.class);

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"ppap@naver.com\",\"password\":\"x\"}"))
                .andExpect(status().isBadRequest());

        verify(userService).authenticate(eq("ppap@naver.com"), eq("x"));
    }

    @Test
    public void createWithWrongPassword() throws Exception {
        given(userService.authenticate("paper@naver.com", "x"))
            .willThrow(PasswordWrongException.class);

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"paper@naver.com\",\"password\":\"x\"}"))
                .andExpect(status().isBadRequest());

        verify(userService).authenticate(eq("paper@naver.com"), eq("x"));
    }
}