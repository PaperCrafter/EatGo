package kr.co.papercraft.eatgo.domain;

import kr.co.papercraft.eatgo.domain.Model.Restaurant;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RestorantTests {
    @Test
    public void createion(){
//        Restaurant restaurant = new Restaurant(1004L, "Bob zip", "Seoul");
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .address("Seoul")
                .name("Bob zip")
                .build();
        assertThat(restaurant.getName(), is("Bob zip"));
        assertThat(restaurant.getAddress(), is("Seoul"));
    }

    @Test
    public void information(){
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();

        assertThat(restaurant.getInformation(), is("Bob zip in Seoul"));
    }
}