package kr.co.papercraft.eatgo.domain.Repository;

import kr.co.papercraft.eatgo.domain.Model.MenuItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {
    List<MenuItem> findAllByRestaurantId(Long restaurantId);

    void deleteById(Long id);
}
