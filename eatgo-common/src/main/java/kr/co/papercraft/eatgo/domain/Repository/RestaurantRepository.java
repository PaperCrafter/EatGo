package kr.co.papercraft.eatgo.domain.Repository;

import kr.co.papercraft.eatgo.domain.Model.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    List<Restaurant> findAll();

    List<Restaurant> findAllByAddressContainingAndCategoryId(
            String region, Long categoryId);

    Optional<Restaurant> findById(Long id);

    Restaurant save(Restaurant restaurant);


}
