package kr.co.papercraft.eatgo.interfaces;

import kr.co.papercraft.eatgo.application.MenuItemService;
import kr.co.papercraft.eatgo.domain.Model.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

//    @Autowired
//    private RestaurantService restaurantService;

    @PatchMapping("/restaurants/{restaurantId}/menuitems")
    public String bulkUpdate(@PathVariable("restaurantId") Long restaurantId,
                             @RequestBody List<MenuItem> menuItems){
        menuItemService.bulkUpdate( restaurantId, menuItems);

        return "";
    }

}
