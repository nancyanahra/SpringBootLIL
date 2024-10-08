package com.nancynahra.lil.learning_spring.web;

import com.nancynahra.lil.learning_spring.business.GuestService;
import com.nancynahra.lil.learning_spring.business.ReservationService;
import com.nancynahra.lil.learning_spring.data.Guest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/guests")
public class GuestInfoController {

    private final ReservationService reservationService;
    private final GuestService guestService;

    public GuestInfoController(ReservationService reservationService) {

        this.reservationService = reservationService;
        this.guestService = new GuestService();
    }

    @RequestMapping(method=RequestMethod.GET)
    public String getGuests(Model model) {

        //made method in Reservation Service to make list of guests
      // List<Guest> guestInfo = this.reservationService.getGuests();
        model.addAttribute("guests", this.reservationService.getGuests());

        return "hotel-guests";

    }

    public Guest getGuestByFullName(@RequestParam String lastName, @RequestParam String firstName) {

        return guestService.getGuestByLastAndFirstName(lastName, firstName);
    }



}
