package kr.co.papercraft.eagto.application;

import kr.co.papercraft.eatgo.domain.Model.Reservation;
import kr.co.papercraft.eatgo.domain.Repository.ReservationRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
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
    public void getReservations(){
        Long restaurantId = 2L;

        List<Reservation> reservations = reservationService.getReservations(restaurantId);

        verify(reservationRepository).findAllByRestaurantId(eq(restaurantId));
    }
}