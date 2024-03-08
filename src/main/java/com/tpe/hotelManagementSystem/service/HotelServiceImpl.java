package com.tpe.hotelManagementSystem.service;

import com.tpe.hotelManagementSystem.domain.Hotel;
import com.tpe.hotelManagementSystem.repository.HotelRepository;
import com.tpe.hotelManagementSystem.repository.HotelRepositoryImpl;

import java.util.Scanner;

public class HotelServiceImpl implements HotelService{

    private static Scanner scanner;

    private final HotelRepository hotelRepository;

    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    //HotelRepository hotelRepository=new HotelRepositoryImpl();   yukaridaki ile ayni sonucu verir.Yukarida obje olusturup,constructor ini cagirdik
                                                                  //Cons yontemi daha guvenilir,daha hizli,maliyetsiz
                                                                  //Obje olusturma maliyetli

    @Override
    public Hotel saveHotel() {
        scanner=new Scanner(System.in);
        Hotel hotel=new Hotel();         //bos bir hotel objesi olusturduk

        System.out.print("Enter hotel Id: ");
        hotel.setId(scanner.nextLong());
        scanner.nextLine();//Consume the newline character
        System.out.print("Enter hotel name: ");
        hotel.setName(scanner.nextLine());
        System.out.print("Enter hotel location: ");
        hotel.setLocation(scanner.nextLine());

        hotelRepository.saveHotel(hotel);
        System.out.println("Hotel saved successfully. Hotel ID : "+hotel.getId());

        return hotel;

    }
}
