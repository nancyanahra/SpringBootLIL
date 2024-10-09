package com.nancynahra.lil.learning_spring.web;



import com.nancynahra.lil.learning_spring.business.GuestService;
import com.nancynahra.lil.learning_spring.business.ReservationService;
import com.nancynahra.lil.learning_spring.data.Guest;
import com.nancynahra.lil.learning_spring.data.GuestRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/addordelete")
public class ModifyController {

    private final ReservationService reservationService;
    private final GuestService guestService;




    public ModifyController(ReservationService reservationService, GuestRepository guestRepository, GuestService guestService) {
        this.reservationService = reservationService;


        this.guestService = guestService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showModifyPage(Model model) {
        //public String showModifyPage(Model model)
        model.addAttribute("guest", new Guest());
        return "modify";

    }

    @PostMapping("/addGuest")
    public String submitInput( Model model, Guest guest) {
        System.out.println("User input:" + guest);

        this.reservationService.addGuest(guest);
      //  guestRepository.save(new Guest(firstName, lastName, email, address, country, state, phoneNumber));

        return "redirect:/addordelete";

    }


    @PostMapping("/deleteGuest")
    public String submitInputDel(Model model, @RequestParam String firstNameDel, @RequestParam String lastNameDel) {

        System.out.println("User input to be deleted:" + lastNameDel + ", " + firstNameDel);
        Guest guestToBeDeleted = this.guestService.getGuestByLastAndFirstName(lastNameDel, firstNameDel);

        if (guestToBeDeleted != null) {
            this.reservationService.deleteGuest(guestToBeDeleted);
        } else {
            model.addAttribute("error", "Guest not found");
        }

        return "redirect:/addordelete";
        //return "modify";

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
