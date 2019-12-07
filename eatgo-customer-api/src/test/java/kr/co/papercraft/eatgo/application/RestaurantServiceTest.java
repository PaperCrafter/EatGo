package kr.co.papercraft.eatgo.application;

import kr.co.papercraft.eatgo.domain.*;
import kr.co.papercraft.eatgo.domain.Model.MenuItem;
import kr.co.papercraft.eatgo.domain.Model.Restaurant;
import kr.co.papercraft.eatgo.domain.Model.Review;
import kr.co.papercraft.eatgo.domain.Repository.MenuItemRepository;
import kr.co.papercraft.eatgo.domain.Repository.RestaurantRepository;
import kr.co.papercraft.eatgo.domain.Repository.ReviewRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

public class RestaurantServiceTest {
    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private ReviewRepository reviewRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockRestaurantRepository();
        mockMenuItemRepository();
        mockReviewRepository();
        restaurantService = new RestaurantService(restaurantRepository, menuItemRepository,reviewRepository);
    }

    private void mockRestaurantRepository() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = Restaurant.builder()
                .id(1L)
                .categoryId(1L)
                .address("Seoul")
                .name("Bob zip")
                .build();
        restaurants.add(restaurant);

        given(restaurantRepository.findAll()).willReturn(restaurants);

        given(restaurantRepository.findById(1L)).willReturn(Optional.of(restaurant));

        given(restaurantRepository.findAllByAddressContainingAndCategoryId("Seoul", 1L))
                .willReturn(restaurants);

    }

    private void mockMenuItemRepository(){
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("김치전골"));
        given(menuItemRepository.findAllByRestaurantId(1004L)).willReturn(menuItems);
    }

    private void mockReviewRepository(){
        List<Review> reviews = new ArrayList<>();
        reviews.add(Review.builder().name("paper").score(5).description("맛있어요").build());
        given(reviewRepository.findAllByRestaurantId(1L)).willReturn(reviews);
    }

    @Test
    public void getRestaurantsWithExisted(){
        List<Restaurant> restaurants = restaurantService.getRestaurants();
        Restaurant restaurant = restaurants.get(0);
        assertThat(restaurant.getId(), is(1L));
    }

    @Test
    public void getFilteredRestaurantsWithExisted(){
        List<Restaurant> restaurants = restaurantService.getFilteredRestaurants("Seoul", 1L);
        Restaurant restaurant = restaurants.get(0);
        assertThat(restaurant.getId(), is(1L));

    }

    @Test
    public void getRestaurants(){
        String region = "Seoul";
        Long categoryId = 1L;
        List<Restaurant> restaurants = restaurantService.getFilteredRestaurants(region, categoryId);
        Restaurant restaurant = restaurants.get(0);

        assertThat(restaurant.getId(), is(1L));
        assertThat(restaurant.getCategoryId(), is(1L));
    }

    @Test
    public void getRestaurant(){
        Restaurant restaurant = restaurantService.getRestaurant(1L);
        assertThat(restaurant.getId(), is(1L));

        Review reviews = restaurant.getReviews().get(0);
        assertThat(reviews.getName(), is("paper"));
    }

    @Test(expected = RestaurantNotFoundException.class)
    public void getRestaurantWithNotExisted(){
        restaurantService.getRestaurant(404L);
    }

}