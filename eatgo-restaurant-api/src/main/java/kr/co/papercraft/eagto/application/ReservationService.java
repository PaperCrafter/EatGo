package kr.co.papercraft.eagto.application;

import kr.co.papercraft.eatgo.domain.Model.Reservation;
import kr.co.papercraft.eatgo.domain.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    ReservationService(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getReservations(Long restaurantId){
        return reservationRepository.findAllByRestaurantId(restaurantId);
    }
}


