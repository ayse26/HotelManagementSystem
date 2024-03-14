package com.tpe.hotelManagementSystem.service;

import com.tpe.hotelManagementSystem.domain.Address;
import com.tpe.hotelManagementSystem.domain.Guest;
import com.tpe.hotelManagementSystem.repository.GuestRepository;

import java.util.Scanner;

public class GuestServiceImpl implements GuestService{

    private static Scanner scanner;
    private final GuestRepository guestRepository;

    public GuestServiceImpl(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Override
    public Guest saveGuest() {
        scanner=new Scanner(System.in);



        Guest guest=new Guest();
        System.out.print("Enter guest name: ");
        guest.setName(scanner.nextLine());

        Address address=new Address();

        System.out.println("Please enter your address");
        System.out.println("------------------------------");
        System.out.print("Enter guest street: ");
        address.setStreet(scanner.nextLine());
        System.out.print("Enter guest city: ");
        address.setCity(scanner.nextLine());
        System.out.print("Enter guest country: ");
        address.setCountry(scanner.nextLine());
        System.out.print("Enter guest zipcode: ");
        address.setZipCode(scanner.nextInt());

        guest.setAddres(address);

        guestRepository.saveGuest(guest);
        System.out.println("Guest saved successfully. Guest id : " + guest.getId());

        return guest;
    }
}
