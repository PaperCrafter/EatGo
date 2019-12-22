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
    public void restaurantOwner(){
        User user = User.builder().level(1L).build();
        assertThat(user.isRestaurantOwner(), is(false));

        user.setRestaurantId(1L);
        assertThat(user.isRestaurantOwner(), is(true));

        assertThat(user.getRestaurantId(), is(1L));
    }

}