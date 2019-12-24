package kr.co.papercraft.eatgo.application;

import kr.co.papercraft.eatgo.domain.Model.Reservation;
import kr.co.papercraft.eatgo.domain.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    ReservationService(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }

    public Reservation addReservation(Long restaurantId, Long userId, String name, String date, String time, Integer partySize) {
        Reservation reservation = Reservation.builder()
                .restaurantId(restaurantId)
                .userId(userId)
                .name(name)
                .date(date)
                .time(time)
                .partySize(partySize)
                .build();

        return reservation;
    }
}
