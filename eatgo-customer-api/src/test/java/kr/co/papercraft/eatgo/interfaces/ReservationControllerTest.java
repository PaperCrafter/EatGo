package kr.co.papercraft.eatgo.interfaces;

import kr.co.papercraft.eatgo.application.ReservationService;
import kr.co.papercraft.eatgo.domain.Model.Reservation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReservationService reservationService;

    @Test
    public void create() throws Exception {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEsIm5hbWUiOiJwYXBlciJ9.N4whJRzIWO1rJCu9YYmGr9VVUNj_mTXBuRBQJBOkjdE";

        Long userId = 1L;
        String name = "paper";
        String date = "2019/12/24";
        String time = "15:00";
        Integer partySize = 4;

        Reservation reservation = Reservation.builder().id(1L).build();
        given(reservationService.addReservation( 2L, userId, name, date, time, partySize))
                .willReturn(reservation);

        mvc.perform(post("/restaurants/2/reservations")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"date\":\"2019/12/24\", \"time\":\"15:00\", \"partySize\":4 }"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/restaurants/2/reservations/1"));

        verify(reservationService).addReservation(eq(2L), eq(userId), eq(name), eq(date), eq(time), eq(partySize));
    }


}