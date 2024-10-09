package com.nancynahra.lil.learning_spring.business;

import com.nancynahra.lil.learning_spring.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//Final Flow:
//User submits the form on modify.html.
//Data is sent to the WebserviceController, which binds the form data to a Guest object.
//The Guest object is passed to the ReservationService, where it is saved to the database using the guestRepository.
//Upon successful submission, the user is redirected back to the modify page.


@Service
public class ReservationService {
    @Autowired
    private final RoomRepository roomRepository;
    @Autowired
    private final GuestRepository guestRepository;
    @Autowired
    private final ReservationRepository reservationRepository;

    public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    public void deleteReservation(Reservation reservation) {

        this.reservationRepository.delete(reservation);


    }

    public List<RoomReservation> getRoomReservationsForDate(Date date) {
        Iterable<Room> rooms = this.roomRepository.findAll();
        Map<Long, RoomReservation> roomReservationMap = new HashMap();
        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservationMap.put(room.getId(), roomReservation);
        });
        Iterable<Reservation> reservations = this.reservationRepository.findReservationByReservationDate(new java.sql.Date(date.getTime()));
        reservations.forEach(reservation -> {
            RoomReservation roomReservation = roomReservationMap.get(reservation.getRoomId());
            roomReservation.setDate(date);
            Guest guest = this.guestRepository.findById(reservation.getGuestId()).get();
            roomReservation.setFirstName(guest.getFirstName());
            roomReservation.setLastName(guest.getLastName());
            roomReservation.setGuestId(guest.getGuestId());
        });
        List<RoomReservation> roomReservations = new ArrayList<>();
        for (Long id : roomReservationMap.keySet()) {
            roomReservations.add(roomReservationMap.get(id));
        }
        roomReservations.sort(new Comparator<RoomReservation>() {
            @Override
            public int compare(RoomReservation o1, RoomReservation o2) {
                if (o1.getRoomName().equals(o2.getRoomName())) {
                    return o1.getRoomNumber().compareTo(o2.getRoomNumber());
                }
                return o1.getRoomName().compareTo(o2.getRoomName());
            }
        });
        return roomReservations;
    }


    public List<Guest> getGuests(){
        Iterable<Guest> guests = this.guestRepository.findAll();
        List<Guest> guestList = new ArrayList<>();
        guests.forEach(guestList::add);
        guestList.sort( new Comparator<Guest>() {
             @Override
            public int compare(Guest o1, Guest o2) {
                if (o1.getLastName().equals(o2.getLastName())) {

                    return o1.getFirstName().compareTo(o2.getFirstName());
                }
                return o1.getLastName().compareTo(o2.getLastName());
            }
        });
            return guestList;
        }


    public void addGuest( Guest newGuest) {

        Guest savedGuest = guestRepository.save(newGuest);


        System.out.println("Guest saved: " + savedGuest);
        //save method will return the object that's just been saved



    }



    // Couldn't use addGuest for 2 mappings in WebService Controller so I made this new method
    public String addGuestUI (Guest newGuest) {

        if(null == newGuest){
            throw new RuntimeException("Guest cannot be null");

        }
        //save method will return the object that's just been saved
        this.guestRepository.save(newGuest);

        return newGuest.toString();

    }

    public List<Room> getRooms() {
        Iterable<Room> rooms = this.roomRepository.findAll();
        List<Room> roomList = new ArrayList<>();
        rooms.forEach(room -> {roomList.add(room);});

        roomList.sort(new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                return o1.getRoomNumber().compareTo(o2.getRoomNumber());
            }


        });
        return roomList;
    }

    // Should this method be in this service class? Or somewhere else
    public void deleteGuest(Guest guestToBeDeleted) {

        // add stuff here about finding if that guest has a room reservation


        Reservation guestReservation = reservationRepository.findReservationByGuestId(guestToBeDeleted.getGuestId());

        if(guestReservation == null){
            guestRepository.delete(guestToBeDeleted);
        }else{
            System.out.println("delete the reservation for " + guestToBeDeleted.getFirstName());
            reservationRepository.delete(guestReservation);
            guestRepository.delete(guestToBeDeleted);
        }

        System.out.println("Guest deleted: " + guestToBeDeleted);

    }

    public String displayPage() {

        return "Displaying the modify page";

    }
}





