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

        given(reviewService.addReview(any(),any())).willReturn(
                Review.builder()
                        .id(1L)
                        .build()
        );

        mvc.perform(post("/restaurants/1/reviews")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"bob-zip\", \"score\":3, \"description\":\"맛있어요\"}"))
                    .andExpect(status().isCreated())
        .andExpect(header().string("location","/restaurants/1/reviews/1"));

        verify(reviewService).addReview(eq(1L), any());
    }

    @Test
    public void createWithInvalidAttribute() throws Exception {
        given(reviewService.addReview(any(),any())).willReturn(
                Review.builder()
                        .id(1L)
                        .build()
        );

        mvc.perform(post("/restaurants/1/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest());

        verify(reviewService, never()).addReview(eq(1L), any());
    }

}