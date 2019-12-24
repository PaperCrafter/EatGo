package kr.co.papercraft.eatgo.domain.Repository;

import kr.co.papercraft.eatgo.domain.Model.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

}
