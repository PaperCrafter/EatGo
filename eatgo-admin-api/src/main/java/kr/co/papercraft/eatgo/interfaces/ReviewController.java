package kr.co.papercraft.eatgo.interfaces;

import kr.co.papercraft.eatgo.application.ReviewService;
import kr.co.papercraft.eatgo.domain.Model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/reviews")
    public List<Review> list(){
        return reviewService.getReviews();
    }
}
