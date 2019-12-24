package kr.co.papercraft.eagto.interfaces;

import kr.co.papercraft.eagto.application.ReservationService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ReservationControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReservationService reservationService;

    @Test
    public void list() throws Exception {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEsIm5hbWUiOiJwYXBlciJ9.N4whJRzIWO1rJCu9YYmGr9VVUNj_mTXBuRBQJBOkjdE";

        mvc.perform(get("/reservations")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());

    }

}