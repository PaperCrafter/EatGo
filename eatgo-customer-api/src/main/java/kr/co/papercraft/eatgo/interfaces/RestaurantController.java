package kr.co.papercraft.eatgo.interfaces;


import kr.co.papercraft.eatgo.application.RestaurantService;
import kr.co.papercraft.eatgo.domain.Model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<Restaurant> list(@RequestParam(required = false, name="region") String region,
                                 @RequestParam(required = false, name ="category") Long categoryId){
        List<Restaurant> restaurants;
        if(region == null && categoryId == null) {
            restaurants = restaurantService.getRestaurants();
        }else{
            restaurants = restaurantService.getFilteredRestaurants(region, categoryId);
        }
        return restaurants;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id){

        Restaurant restaurant = restaurantService.getRestaurant(id);

        return restaurant;
    }

}
