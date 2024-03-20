package com.tpe.hotelManagementSystem.service;

import com.tpe.hotelManagementSystem.domain.Hotel;
import com.tpe.hotelManagementSystem.exception.HotelResourceNotFoundException;
import com.tpe.hotelManagementSystem.repository.HotelRepository;

import java.util.List;
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

    @Override
    public Hotel findHotelById(Long id) {
        try {
            Hotel foundHotel = hotelRepository.findHotelById(id);
            if (foundHotel!=null){
                System.out.println("----------------------------------");
                System.out.println(foundHotel);
                return foundHotel;
            }else {
                throw new HotelResourceNotFoundException("Hotel not found with id : " + id);
            }
        }catch (HotelResourceNotFoundException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteHotelById(Long id) {

        scanner=new Scanner(System.in);
        Hotel hotelDelete=hotelRepository.findHotelById(id);
        if (hotelDelete==null){
            throw new HotelResourceNotFoundException("Hotel not found with id : " + id);
        }
            System.out.println(hotelDelete);
            System.out.println("Are you sure you want to delete hotel with id: " + id + " Please answer with Y or N");
            String confirmation=scanner.nextLine();
            if (confirmation.equalsIgnoreCase("Y")){
                hotelRepository.deleteHotelById(hotelDelete.getId());
                System.out.println("Hotel is deleted successfully...");
            }else {
                System.out.println("Delete operation cancelled...");
            }
        }

    @Override
    public List<Hotel> findAllHotels() {

        try {
            List<Hotel> hotels = hotelRepository.findAllHotels();
            if (!hotels.isEmpty()){
                System.out.println("List of hotels : ");
               // hotels.forEach(t-> System.out.println(t));
                for (Hotel hotel : hotels) {
                    System.out.println(hotel);
                    System.out.println("*------------------------------------------*");
                }
            }else {
                //throw new HotelResourceNotFoundException("Hotel list is empty");
                System.out.println("Hotel list is empty.");
            }
        } catch (Exception e) {
        System.out.println("An error occurred while retrieving hotels " + e.getMessage());
    }
        return null;

    }

    @Override
    public void updateHotel(Long id, Hotel updateHotel) {
        try {
            Hotel existingHotel=hotelRepository.findHotelById(id);
            if (existingHotel==null){
                throw new HotelResourceNotFoundException("Hotel not found...");
            }
            existingHotel.setName(updateHotel.getName());
            existingHotel.setLocation(updateHotel.getLocation());

            hotelRepository.updateHotel(existingHotel);
        }catch (HotelResourceNotFoundException e){
            System.out.println(e.getMessage());
        }
    }


}



