package com.nancynahra.lil.learning_spring.web;



import com.nancynahra.lil.learning_spring.business.ReservationService;
import com.nancynahra.lil.learning_spring.data.Guest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/addordelete")
public class ModifyController {

    private final ReservationService reservationService;

    public ModifyController(ReservationService reservationService) {
        this.reservationService = reservationService;

    }


    @RequestMapping(method = RequestMethod.GET)
    public String showModifyPage(Model model) {

        model.addAttribute("message", reservationService.displayPage());
        return "modify";

    }

    @PostMapping("/addGuest")
    public String submitInput(
        @RequestParam("firstName") String firstName,
        @RequestParam("lastName") String lastName,
        @RequestParam("address") String address,
        @RequestParam("email") String email,
        @RequestParam("country") String country,
        @RequestParam("state") String state,
        @RequestParam("phoneNumber") String phoneNumber,
        Model model
    ) {
        System.out.println("User input: " + firstName + lastName + address + email + country + state + phoneNumber);
        model.addAttribute("guest", new Guest(firstName, lastName, email, address, country, state, phoneNumber ));
        return "modify";

    }

    // not too sure what's going on with the naming - why this one can just be addGuest and not addGuestUI
//    @RequestMapping(method = RequestMethod.GET)
//    public String addGuestUI(Model model, Guest newGuest){
//
//        model.addAttribute("newguest", this.reservationService.addGuestUI(newGuest));
//
//        return "modify";
//    }

}
