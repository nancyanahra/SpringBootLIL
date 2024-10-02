package com.nancynahra.lil.learning_spring.webservice;

import com.nancynahra.lil.learning_spring.business.ReservationService;
import com.nancynahra.lil.learning_spring.business.RoomReservation;
import com.nancynahra.lil.learning_spring.data.Guest;
import com.nancynahra.lil.learning_spring.data.Room;
import com.nancynahra.lil.learning_spring.util.DateUtils;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WebserviceController implements ErrorController {

    private static final String PATH = "/error";

    private final DateUtils dateUtils;
    private final ReservationService reservationService;

    public WebserviceController(DateUtils dateUtils, ReservationService reservationService) {
        this.dateUtils = dateUtils;
        this.reservationService = reservationService;
    }

    //added two methods below to account for white label error page
    @RequestMapping(value=PATH)
    public String error(){
        return "error handling";
    }

    public String getErrorPath(){
        return PATH;
    }

    @RequestMapping(path="/reservations", method = RequestMethod.GET)
    public List<RoomReservation> getReservations(@RequestParam(value="date", required = false)String dateString){

        Date date = this.dateUtils.createDateFromDateString(dateString);
        return this.reservationService.getRoomReservationsForDate(date);

    }

    // exposing a service layer through REST
    @RequestMapping(path="/guests", method = RequestMethod.GET)
    public List<Guest> getGuests(){

        return this.reservationService.getGuests();

    }


//    @PostMapping("/guests")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void addGuest(@RequestBody Guest newGuest){
//      this.reservationService.addGuest(newGuest);
//    }

    @GetMapping("/rooms")
    public List<Room> getRooms(){

        return this.reservationService.getRooms();
    }

    //why GetMapping vs PostMapping?
    //
    @RequestMapping(path="/addordelete", method = RequestMethod.GET)
    public String displayPage(){

        return this.reservationService.displayPage();

    }

//    public void addGuestUI(Guest newGuest){
//        this.reservationService.addGuestUI(newGuest);
//    }


}
