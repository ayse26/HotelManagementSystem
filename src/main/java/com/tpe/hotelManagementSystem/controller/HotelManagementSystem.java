package com.tpe.hotelManagementSystem.controller;

import java.util.Scanner;

public class HotelManagementSystem {

    public static Scanner scanner;     //menulerde tek tek yazmak yerine burada olusturduk.New lemeyi yapmadik burada,kullanirken yapacagiz

    public static void displayMenuHotelManagementSystem(){  //butun otel menu islemlerinin oldugu display menusu yaziyoruz
        scanner=new Scanner(System.in);

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
                    displayHotelOperationsMenu();
                    break;
                case 2:
                    displayRoomOperationsMenu();
                    break;
                case 3:
                    displayGuestOperationsMenu();
                    break;
                case 4:
                    displayReservationOperationsMenu();
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
    private static void displayHotelOperationsMenu(){
        System.out.println("Hotel Operation Menu");
    }

    private static void displayRoomOperationsMenu(){
        System.out.println("Room Operation Menu");
    }

    private static void displayGuestOperationsMenu(){
        System.out.println("Guest Operation Menu");
    }

    private static void displayReservationOperationsMenu(){
        System.out.println("Reservation Operation Menu");
    }



    //HOMEWORK:Bu class i bitir
}
