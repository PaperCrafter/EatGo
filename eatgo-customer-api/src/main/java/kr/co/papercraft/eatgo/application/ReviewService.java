package kr.co.papercraft.eatgo.application;

import kr.co.papercraft.eatgo.domain.Model.Review;
import kr.co.papercraft.eatgo.domain.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    public Review addReview(Long restaurantId, String name, Integer score,
                            String description){

        Review review = Review.builder()
                .restaurantId(restaurantId)
                .name(name)
                .description(description)
                .score(score)
                .build();

        review.setRestaurantId(restaurantId);

        return reviewRepository.save(review);
    }
}
