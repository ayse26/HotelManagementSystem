package com.tpe.hotelManagementSystem.service;

import com.tpe.hotelManagementSystem.domain.Guest;
import com.tpe.hotelManagementSystem.domain.Reservation;
import com.tpe.hotelManagementSystem.domain.Room;
import com.tpe.hotelManagementSystem.exception.GuestNotFoundException;
import com.tpe.hotelManagementSystem.exception.ReservationNotFoundException;
import com.tpe.hotelManagementSystem.exception.RoomResourceNotFoundExceptions;
import com.tpe.hotelManagementSystem.repository.GuestRepository;
import com.tpe.hotelManagementSystem.repository.ReservationRepository;
import com.tpe.hotelManagementSystem.repository.RoomRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ReservationServiceImpl implements ReservationService{

    private static Scanner scanner;

    //constructor injection ReservationRepository,GuestRepository,RoomRepository (daha gormedik injection)

    private final ReservationRepository reservationRepository;
    private final GuestRepository guestRepository;
    private final RoomRepository roomRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository, GuestRepository guestRepository, RoomRepository roomRepository) {
        this.reservationRepository = reservationRepository;
        this.guestRepository = guestRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public Reservation saveReservation() {
        scanner=new Scanner(System.in);

        //guest e ait bilgileri al
        System.out.print("Enter your guest id: ");
        Long guestId=scanner.nextLong();
        scanner.nextLine();

        //room ile ilgili bilgiler
        System.out.print("Enter room id: ");
        Long roomId=scanner.nextLong();
        scanner.nextLine();

        //giris cikis tarihlerini alalim
        System.out.println("Enter check-in date (yyyy-MM-dd)");
        LocalDate checkInDate=LocalDate.parse(scanner.nextLine());   // parse() methodu ; String bir ifadeyi tam sayiya ceviren method
                                                                     //parse methodu sayesinde bu tarihi alacak LocalDate turune cevirecek
        System.out.println("Enter check-out date (yyyy-MM-dd)");
        LocalDate checkOutDate=LocalDate.parse(scanner.nextLine());

        try {
            Guest existingGuest = guestRepository.findGuestById(guestId);
            if (existingGuest==null){
                throw new GuestNotFoundException("Guest not found with id: "+guestId);
            }
            Room existingRoom = roomRepository.findRoomById(roomId);
            if (existingRoom==null){
                throw new RoomResourceNotFoundExceptions("Room not found with id: "+roomId);
            }
            Reservation reservation=new Reservation();
            reservation.setGuest(existingGuest);
            reservation.setRoom(existingRoom);
            reservation.setCheckinDate(checkInDate);
            reservation.setCheckioutDate(checkOutDate);

            reservationRepository.saveReservation(reservation);
            System.out.println("Reservation saved successfully...");
            return reservation;
        }catch (GuestNotFoundException | RoomResourceNotFoundExceptions e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Reservation findReservationById(Long id) {

        Reservation existingReservation=reservationRepository.findReservationById(id);
        if (existingReservation!=null){
            return existingReservation;
        }else {
            throw new ReservationNotFoundException("Reservation not found with id: "+id);
        }

    }

    @Override
    public List<Reservation> findAll() {

        try {
            List<Reservation>existingReservations=reservationRepository.findAll();
            if (!existingReservations.isEmpty()){
                existingReservations.forEach(t-> System.out.println(t));
                return existingReservations;
            }else{
                throw new ReservationNotFoundException("Reservation not found");
            }
        }catch (ReservationNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteReservationById(Long id) {
       try {
           Reservation deletedReservation=reservationRepository.findReservationById(id);
           if (deletedReservation!=null) {
               reservationRepository.deleteReservationById(id);
               System.out.println("Reservation is deleted successfully...");
           }else {
               System.out.println("Reservation not found with id: "+id);
           }
       }catch (Exception e){
           e.printStackTrace();
       }
    }


}

//odalar kac kisi,odalar da yer var mı,local date ile daha once check in yapilmis mi yapilmamis mi ona bakilabilir,
//odalar listenirken bir kisi tarafindan reserve edildi diye ekrana yazilabilir...
//pronoisdate diye birsey var iki zaman araligindaki gunleri sayan.Bununla checkout arasindaki zamani kontrol edip
//eger sifirdan buyukse oda dolu seklinde soylenebilir
