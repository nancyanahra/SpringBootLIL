package com.nancynahra.lil.learning_spring.business;

import com.nancynahra.lil.learning_spring.data.Guest;
import com.nancynahra.lil.learning_spring.data.GuestRepository;
import com.nancynahra.lil.learning_spring.data.Reservation;
import com.nancynahra.lil.learning_spring.data.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class GuestService {

    @Autowired
    private GuestRepository guestRepository;


    public Guest getGuestByLastAndFirstName(String lastName, String firstName){
        return guestRepository.findByLastNameAndFirstName(lastName, firstName);

    }



}
