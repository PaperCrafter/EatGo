package kr.co.papercraft.eatgo.interfaces;


import kr.co.papercraft.eatgo.application.RestaurantService;
import kr.co.papercraft.eatgo.domain.MenuItemRepository;
import kr.co.papercraft.eatgo.domain.Restaurant;
import kr.co.papercraft.eatgo.domain.RestaurantRepository;
import kr.co.papercraft.eatgo.domain.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin
@RestController
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired


    @GetMapping("/restaurants")
    public List<Restaurant> list(){
        List<Restaurant> restaurants = restaurantService.getRestaurants();
        return restaurants;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id){

        Restaurant restaurant = restaurantService.getRestaurant(id);

        return restaurant;
    }

    @PostMapping("/restaurants")
    public ResponseEntity<?> create(@Valid @RequestBody Restaurant resource) throws URISyntaxException {
        String name = resource.getName();
        String address = resource.getAddress();

        Restaurant restaurant = restaurantService.addRestaurant(Restaurant.builder()
                .name(name)
                .address(address)
                .build());

        URI location = new URI("/restaurants/" + restaurant.getId());
        return ResponseEntity.created(location).body("{}");
    }

    @PatchMapping("/restaurants/{id}")
    public String update(@PathVariable("id") Long id,
                         @Valid @RequestBody Restaurant ressource){
        String name = ressource.getName();
        String address = ressource.getAddress();

        restaurantService.updateRestaurant(id, name, address);

        return "{}";
    }
}
