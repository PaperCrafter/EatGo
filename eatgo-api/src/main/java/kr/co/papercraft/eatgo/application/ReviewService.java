package kr.co.papercraft.eatgo.application;

import kr.co.papercraft.eatgo.domain.Review;
import kr.co.papercraft.eatgo.domain.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    public Review addReview(Long restaurantId, Review review){
        return reviewRepository.save(review);
    }
}
