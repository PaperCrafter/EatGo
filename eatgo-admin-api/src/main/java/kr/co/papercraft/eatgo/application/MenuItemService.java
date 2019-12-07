package kr.co.papercraft.eatgo.application;

import kr.co.papercraft.eatgo.domain.Model.MenuItem;
import kr.co.papercraft.eatgo.domain.Repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    public MenuItemService(MenuItemRepository menuItemRepository){
        this.menuItemRepository = menuItemRepository;
    }

    public List<MenuItem> getMenuItems(Long restaurantId){
        return menuItemRepository.findAllByRestaurantId(restaurantId);
    }


    public void bulkUpdate(Long restaurantId, List<MenuItem> menuItems) {
        for(MenuItem menuItem : menuItems){
            update(restaurantId, menuItem);
        }
//        MenuItem menuItem = MenuItem.builder().build();
//        menuItemRepository.saveAll(menuItems);
        //TODO:UPDATE

    }

    private void update(Long restaurantId, MenuItem menuItem) {
        if(menuItem.isDestroy()){
            menuItemRepository.deleteById(menuItem.getId());
            return;
        }
        menuItem.setRestaurantId(restaurantId);
        menuItemRepository.save(menuItem);
    }

    public List<MenuItem> findAllByRestaurantId(long restaurantId) {
        return menuItemRepository.findAllByRestaurantId(restaurantId);
    }
}
