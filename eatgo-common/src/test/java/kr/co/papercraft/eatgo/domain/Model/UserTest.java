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
}