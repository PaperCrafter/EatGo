package kr.co.papercraft.eatgo.interfaces;

import kr.co.papercraft.eatgo.application.ReviewService;
import kr.co.papercraft.eatgo.domain.Model.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReviewController.class)
public class ReviewControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private ReviewService reviewService;

    @Test
    public void createWithValidAttribute() throws Exception {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEsIm5hbWUiOiJwYXBlciJ9.N4whJRzIWO1rJCu9YYmGr9VVUNj_mTXBuRBQJBOkjdE";

        given(reviewService.addReview(1L,"paper",3,"맛있네요"))
                .willReturn(Review.builder()
                        .id(2L)
                        .build());

        mvc.perform(post("/restaurants/1/reviews")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"score\":3,\"description\":\"맛있네요\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location","/restaurants/1/reviews/2"));

        verify(reviewService).addReview(eq(1L), eq("paper"), eq(3), eq("맛있네요"));
    }

    @Test
    public void createWithInvalidAttribute() throws Exception {
        given(reviewService.addReview(any(),any(),any(),any())).willReturn(
                Review.builder()
                        .id(1L)
                        .build()
        );

        mvc.perform(post("/restaurants/1/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest());

        verify(reviewService, never()).addReview(any(), any(),any(),any());
    }

}