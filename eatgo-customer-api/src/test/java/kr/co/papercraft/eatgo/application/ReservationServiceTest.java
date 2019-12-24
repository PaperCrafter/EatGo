package kr.co.papercraft.eatgo.application;

import kr.co.papercraft.eatgo.domain.Model.Reservation;
import kr.co.papercraft.eatgo.domain.Repository.ReservationRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;

    @Mock
    private ReservationRepository reservationRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        reservationService = new ReservationService(reservationRepository);
    }

    @Test
    public void addReservation(){
        Long restaurantId = 2L;
        Long userId = 1L;
        String name = "paper";
        String date = "2019/12/24";
        String time = "15:00";
        Integer partySize = 4;

        Reservation mockReservation = Reservation.builder()
                .name(name)
                .time(time)
                .userId(userId)
                .build();

        given(reservationRepository.save(any())).will(invocation -> {
            Reservation reservation = invocation.getArgument(0);
            return reservation;
        });

        Reservation reservation=reservationService.addReservation(restaurantId, userId, name, date, time, partySize);

        assertThat(reservation.getName(), is(name));

        verify(reservationRepository).save(any(Reservation.class));
    }

}