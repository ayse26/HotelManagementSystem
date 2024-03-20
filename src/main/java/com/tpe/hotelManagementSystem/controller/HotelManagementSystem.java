package com.tpe.hotelManagementSystem.controller;

import com.tpe.hotelManagementSystem.domain.Hotel;
import com.tpe.hotelManagementSystem.exception.HotelResourceNotFoundException;
import com.tpe.hotelManagementSystem.repository.*;
import com.tpe.hotelManagementSystem.service.*;

import java.util.List;
import java.util.Scanner;

public class HotelManagementSystem {

    public static Scanner scanner;     //menulerde tek tek yazmak yerine burada olusturduk.New lemeyi yapmadik burada,kullanirken yapacagiz


    public static void displayMenuHotelManagementSystem(){  //butun otel menu islemlerinin oldugu display menusu yaziyoruz
        scanner=new Scanner(System.in);

        HotelRepository hotelRepository=new HotelRepositoryImpl();
        HotelService hotelService=new HotelServiceImpl(hotelRepository);

        RoomRepository roomRepository=new RoomRepositoryImp();
        RoomService roomService=new RoomServiceImpl(roomRepository,hotelRepository);

        GuestRepository guestRepository=new GuestRepositoryImpl();
        GuestService guestService=new GuestServiceImpl(guestRepository);

        ReservationRepository reservationRepository=new ReservationRepositoryImpl();
        ReservationService reservationService=new ReservationServiceImpl(reservationRepository,guestRepository,roomRepository);

        boolean exit=false;
        while (!exit){
            System.out.println("==== Hotel Management System  Menu ====");
            System.out.println("1. Hotel Operations");
            System.out.println("2. Room Operations");
            System.out.println("3. Guest Operations");
            System.out.println("4. Reservation Operations");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice= scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    displayHotelOperationsMenu(hotelService);
                    break;
                case 2:
                    displayRoomOperationsMenu(roomService);
                    break;
                case 3:
                    displayGuestOperationsMenu(guestService);
                    break;
                case 4:
                    displayReservationOperationsMenu(reservationService);
                    break;
                case 5:
                    exit=true;
                    System.out.println("Good bye");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again");
                    break;
            }

        }

    }
    private static void displayHotelOperationsMenu(HotelService hotelService){     //method icinde hotelService objesini kullanabilmek icin parametreye ekledik
        System.out.println("HotelOperationMenu"); //Step 14
        scanner = new Scanner(System.in); //Step 15
        boolean exit = false;
        while (!exit) {
            System.out.println("==== Hotel Operations ====");
            System.out.println("1. Add a new hotel");
            System.out.println("2. Find Hotel By ID");
            System.out.println("3. Delete Hotel By ID");
            System.out.println("4. Find All Hotels");
            System.out.println("5. Update Hotel By ID");
            System.out.println("6. Return to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    //saveHotel
                    System.out.println("==== Add a new hotel ====");
                    hotelService.saveHotel();

                    break;
                case 2:
                    //findHotelById
                    System.out.print("Enter the hotel ID: ");
                    Long hotelId = scanner.nextLong();
                    hotelService.findHotelById(hotelId);
                    break;
                case 3:
                    //deleteHotelById
                    System.out.print("Enter the hotel ID to delete: ");
                    Long id = scanner.nextLong();
                    hotelService.deleteHotelById(id);
                    break;            //fk constraint old icin silmiyor,hata veriyor
                case 4:
                    //findAllHotels
                    System.out.println("==== Find All Hotels ====");
                    List<Hotel> hotelList=hotelService.findAllHotels();
                    break;
                case 5:
                    //updateHotelById
                    System.out.println("==== Update Hotel By ID ====");
                    System.out.print("Enter the hotel ID to update: ");
                    Long hotelId1 = scanner.nextLong();
                    scanner.nextLine(); // Consume the newline character
                    try {
                        System.out.print("Enter the updated hotel name: ");
                        String name= scanner.nextLine();
                        System.out.print("Enter the updated hotel location: ");
                        String location=scanner.nextLine();
                        Hotel updatedHotel=new Hotel();
                        updatedHotel.setId(hotelId1);
                        updatedHotel.setName(name);
                        updatedHotel.setLocation(location);
                        hotelService.updateHotel(hotelId1,updatedHotel);
                    }catch (HotelResourceNotFoundException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    exit=true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again");
                    break;
            }
        }
    }

    private static void displayRoomOperationsMenu(RoomService roomService){
        System.out.println("RoomOperationMenu"); //Step 14
        scanner = new Scanner(System.in);  //Step 15

        boolean exit = false;
        while (!exit) {
            System.out.println("==== Room Operations ====");
            System.out.println("1. Add a new room");
            System.out.println("2. Find Room By ID");
            System.out.println("3. Delete Room By ID");
            System.out.println("4. Find All Rooms");
            System.out.println("5. Return to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    //saveRoom
                    System.out.println("==== Add a new room ====");
                    roomService.saveRoom();
                    break;
                case 2:
                    //findRoomById
                    System.out.print("Enter the room ID to find : ");
                    Long roomId = scanner.nextLong();
                    roomService.findRoomById(roomId);
                    break;
                case 3:
                    //deleteRoomById
                    System.out.print("Enter the room ID to delete: ");
                    Long id = scanner.nextLong();
                    roomService.deleteRoomById(id);
                    break;
                case 4:
                    //findAllRooms
                    System.out.println("==== Find All Rooms ====");
                    roomService.findAllRooms();
                    break;
                case 5:
                    exit=true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again");
                    break;
            }
        }
    }

    private static void displayGuestOperationsMenu(GuestService guestService) {
        System.out.println("GuestOperationMenu"); //Step 14
        scanner = new Scanner(System.in);  //Step 15
        boolean exit = false;
        while (!exit) {
            System.out.println("==== Guest Operations ====");
            System.out.println("1. Add a new guest");
            System.out.println("2. Find Guest By ID");
            System.out.println("3. Delete Guest By ID");
            System.out.println("4. Find All Guests");
            System.out.println("5. Return to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    //saveGuest
                    System.out.println("==== Add a new guest ====");
                    guestService.saveGuest();
                    break;
                case 2:
                    //findGuestById
                    System.out.print("Enter the guest id to find: ");
                    Long guestId=scanner.nextLong();
                    guestService.findGuestById(guestId);
                    break;
                case 3:
                    //deleteGuestById
                    System.out.println("==== Delete Guest By ID ====");
                    System.out.print("Enter the guest id to delete: ");
                    Long id=scanner.nextLong();
                    scanner.nextLine(); // Consume the newline character
                    guestService.deleteGuestById(id);
                    break;
                case 4:
                    //findAllGuest
                    System.out.println("==== Find All Guests ====");
                    guestService.findAllGuest();
                    break;
                case 5:
                    exit=true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again");
                    break;
            }
        }
    }
    private static void displayReservationOperationsMenu(ReservationService reservationService){
        System.out.println("ReservationOperationMenu"); //Step 14
        scanner = new Scanner(System.in);  //Step 15
        boolean exit = false;
        while (!exit) {
            System.out.println("==== Reservation Operations ====");
            System.out.println("1. Add a new reservation");
            System.out.println("2. Find Reservation By ID");
            System.out.println("3. Find All Reservations");
            System.out.println("4. Delete Reservation By ID");
            System.out.println("5. Return to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    //saveReservation
                    System.out.println("==== Add a new reservation ====");
                    reservationService.saveReservation();
                    break;
                case 2:
                    //findReservationById
                    System.out.print("Enter the reservation id to find");
                    Long reservationId= scanner.nextLong();
                    scanner.nextLine();
                    reservationService.findReservationById(reservationId);
                    break;
                case 3:
                    //findAll
                    System.out.println("==== Find All Reservations ====");
                    reservationService.findAll();
                    break;
                case 4:
                    //deleteReservationById
                    System.out.println("==== Delete Reservation By ID ====");
                    System.out.print("Enter the reservation id to delete");
                    Long id=scanner.nextLong();
                    scanner.nextLine();
                    reservationService.deleteReservationById(id);
                    break;
                case 5:
                    exit=true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again");
                    break;
            }
        }
    }
}



// HotelRepository hotelRepository = new HotelRepositoryImpl();
// HotelRepository --> interface ,
// HotelRepositoryImpl-->interface degil,interface i implemente eden abstract class
// Interface in methodlarini kullanmak icin obje olustururken interface den degil,implemente eden classtan
// olustururuz.(Interface tipinde -->HotelRepository olur ama cons olarak interface i implemente eden class i --> HotelRepositoryImpl kullaniriz.
// interface i implemente eden birden fazla class olabilir.Method lari override ederken kendine ozgu hal aldigi icin
// kullanmak istedigimiz class i seceriz.

