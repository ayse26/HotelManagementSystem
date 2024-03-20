package com.tpe.hotelManagementSystem.service;

import com.tpe.hotelManagementSystem.domain.Address;
import com.tpe.hotelManagementSystem.domain.Guest;
import com.tpe.hotelManagementSystem.exception.GuestNotFoundException;
import com.tpe.hotelManagementSystem.repository.GuestRepository;

import java.util.List;
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
        //id otomatik generat edildigi icin sormadik
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

    @Override
    public void findGuestById(Long id) {

        try {
            Guest foundGuest=guestRepository.findGuestById(id);
            if (foundGuest!=null){
                System.out.println("--------------------");
                System.out.println(foundGuest);
            }else {
                throw new GuestNotFoundException("Guest not found with id: "+id);
            }
        }catch (GuestNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Guest> findAllGuest() {
        try {
            List<Guest> guestList=guestRepository.findAllGuest();
            if (!guestList.isEmpty()) {
                guestList.forEach(t-> System.out.println(t));
            }else {
                System.out.println("Guest not found");

            }
        }catch (GuestNotFoundException e){
            System.out.println(e.getMessage());
        }
        return null;



    }

    @Override
    public void deleteGuestById(Long id) {

        Guest existinGuest=guestRepository.findGuestById(id);
        if (existinGuest!=null){
            guestRepository.deleteGuestById(id);
            System.out.println("Hotel is deleted successfully...");
        }else {
            System.out.println("Guest not found with id: "+id);
        }

    }
}
