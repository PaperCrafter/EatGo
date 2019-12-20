package kr.co.papercraft.eatgo.domain.Model;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void create(){
        User user = User.builder()
                .email("whdgml1996@naver.com")
                .name("paper")
                .level(3L)
                .build();

        assertThat(user.getName(), is("paper"));
        //assertThat();
    }

    @Test
    public void accessTokenWithPassword(){
        User user = User.builder().password("ACCESSTOKEN").build();
        assertThat(user.getAcessToken(), is("ACCESSTOKE"));
    }

    @Test
    public void accessTokenWithoutPassword(){
        User user = new User();
        assertThat(user.getAcessToken(), is(""));
    }
}