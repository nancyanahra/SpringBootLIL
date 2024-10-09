package com.nancynahra.lil.learning_spring.data;

import org.springframework.data.jpa.repository.JpaRepository;
import java.sql.Date;


public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Iterable<Reservation> findReservationByReservationDate(Date reservationDate);

    Reservation findReservationByGuestId(Long guestId);



}
