package kr.co.papercraft.eatgo.application;

import kr.co.papercraft.eatgo.domain.*;
import kr.co.papercraft.eatgo.domain.Model.MenuItem;
import kr.co.papercraft.eatgo.domain.Model.Restaurant;
import kr.co.papercraft.eatgo.domain.Model.Review;
import kr.co.papercraft.eatgo.domain.Repository.MenuItemRepository;
import kr.co.papercraft.eatgo.domain.Repository.RestaurantRepository;
import kr.co.papercraft.eatgo.domain.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    MenuItemRepository menuItemRepository;

    @Autowired
    ReviewRepository reviewRepository;

    public RestaurantService(RestaurantRepository restaurantRepository,
                             MenuItemRepository menuItemRepository,
                             ReviewRepository reviewRepository){
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
        this.reviewRepository = reviewRepository;
    }

    public List<Restaurant> getRestaurants(){
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants;
    }

    public List<Restaurant> getFilteredRestaurants(String region, Long categoryId) {
        List<Restaurant> restaurants = restaurantRepository.findAllByAddressContainingAndCategoryId(region, categoryId);
        return restaurants;
    }

    public Restaurant getRestaurant(Long id){
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(()-> new RestaurantNotFoundException(id));

        List<MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(id);
        List<Review> reviews = reviewRepository.findAllByRestaurantId(id);
        restaurant.setMeuItems(menuItems);
        restaurant.setReviews(reviews);
        return restaurant;
    }

}
