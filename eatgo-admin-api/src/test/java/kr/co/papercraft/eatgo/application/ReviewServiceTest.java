package kr.co.papercraft.eatgo.application;

import kr.co.papercraft.eatgo.domain.Model.Review;
import kr.co.papercraft.eatgo.domain.Repository.ReviewRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

public class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        reviewService = new ReviewService(reviewRepository);
    }

    @Test
    public void getReviews(){
        List<Review> mockReviews = reviewRepository.findAll();
        mockReviews.add(Review.builder().description("good").name("paper").score(3).build());
        given(reviewRepository.findAll()).willReturn(mockReviews);

        List<Review> reviews = reviewService.getReviews();
        Review review = reviews.get(0);
        assertThat(review.getDescription(), is("good"));
    }

}