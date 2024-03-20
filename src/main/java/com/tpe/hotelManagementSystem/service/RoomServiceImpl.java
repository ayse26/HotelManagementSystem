package com.tpe.hotelManagementSystem.service;

import com.tpe.hotelManagementSystem.domain.Hotel;
import com.tpe.hotelManagementSystem.domain.Room;
import com.tpe.hotelManagementSystem.exception.HotelResourceNotFoundException;
import com.tpe.hotelManagementSystem.exception.RoomResourceNotFoundExceptions;
import com.tpe.hotelManagementSystem.repository.HotelRepository;
import com.tpe.hotelManagementSystem.repository.RoomRepository;
import com.tpe.hotelManagementSystem.repository.RoomRepositoryImp;

import javax.management.relation.RoleInfoNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RoomServiceImpl implements RoomService{

    private static Scanner scanner;

    private final RoomRepository roomRepository;

    private final HotelRepository hotelRepository;

    public RoomServiceImpl(RoomRepository roomRepository, HotelRepository hotelRepository) {
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
    }

    @Override
    public Room saveRoom() {
        scanner=new Scanner(System.in);
        Room room=new Room();            //oda bilgilerini kaydetmek için boş bir oda objesi oluşturduk

        System.out.println("Enter room ID: ");    //kaydedilecek oda bilgileri alındı
        room.setId(scanner.nextLong());
        scanner.nextLine();
        System.out.println("Enter room number :");
        room.setNumber(scanner.nextLine());
        System.out.println("Enter room capacity :");
        room.setCapacity(scanner.nextInt());
        scanner.nextLine();


        System.out.println("Enter hotel ID :");     //hangi hotelin odası olacağı soruldu
        Long hotelId=scanner.nextLong();
        scanner.nextLine();

        try {
            Hotel existingHotel=hotelRepository.findHotelById(hotelId);
            if (existingHotel==null){      //id ye ait hotel varlığı kontrol edildi
                throw new HotelResourceNotFoundException("Hotel not found with id : "+ hotelId);
            }else {
                room.setHotel(existingHotel);                  //odaya ait hotel verildi
                Room savedRoom = roomRepository.saveRoom(room);          //oda kaydedildi
                existingHotel.getRooms().add(savedRoom);          //hotele ait oda listeleri eklendi
                System.out.println("Room saved successfully. Room id " + savedRoom.getId());
            }
        }catch (HotelResourceNotFoundException e){
            System.out.println(e.getMessage());
        }
        return room;
    }

    @Override
    public Room findRoomById(Long roomId) {

      try {
          Room room=roomRepository.findRoomById(roomId);
          if (room!=null){
              System.out.println("---------------------------------");
              System.out.println(room);
              return room;
          }else {
              throw new RoomResourceNotFoundExceptions("Room not found with id :"+roomId);
          }
          }catch (RoomResourceNotFoundExceptions e){
          System.out.println(e.getMessage());
          return null;
      }

    }


    @Override
    public void deleteRoomById(Long id) {

        try {
            Room deleteRoom = roomRepository.findRoomById(id);
            if (deleteRoom!=null){
                roomRepository.deleteRoomByID(id);
                System.out.println("Room  deleted successfully. ID: " + id);
            }else{
                throw new RoomResourceNotFoundExceptions("Room is not found with id: "+id);
            }
        }catch (RoomResourceNotFoundExceptions e){
            System.out.println(e.getMessage());
        }

    }


    @Override
    public List<Room> findAllRooms() {

        try {
            List<Room>rooms=roomRepository.findAllRooms();
            if (!rooms.isEmpty()){
                for (Room room : rooms) {
                    System.out.println(room);
                }
                //rooms.forEach(t-> System.out.println(t));
            }else{
                throw new RuntimeException("No rooms found.");
            }
            return rooms;       //?????????????
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            return new ArrayList<>();      // Return boş bir ArrayList
        }

    }


}
