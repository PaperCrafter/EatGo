package kr.co.papercraft.eatgo.application;

import kr.co.papercraft.eatgo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    MenuItemRepository menuItemRepository;

    public RestaurantService(RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository){
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
    }

    public List<Restaurant> getRestaurants(){
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants;
    }

    public Restaurant getRestaurant(Long id){
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(()-> new RestaurantNotFoundException(id));

        List<MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(id);
        restaurant.setMeuItems(menuItems);
        return restaurant;
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        return (Restaurant) restaurantRepository.save(restaurant);
    }

    @Transactional
    public Restaurant updateRestaurant(Long id, String name, String address) {
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
        restaurant.updateInformation(name, address);
        return restaurant;
    }
}
