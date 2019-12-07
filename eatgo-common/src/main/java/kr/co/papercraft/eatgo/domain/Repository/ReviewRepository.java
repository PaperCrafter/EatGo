package kr.co.papercraft.eatgo.domain.Repository;

import kr.co.papercraft.eatgo.domain.Model.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {
    Review save(Review review);

    List<Review> findAll();

    List<Review> findAllByRestaurantId(Long restaurantId);
}
