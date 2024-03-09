package com.tpe.hotelManagementSystem.service;

import com.tpe.hotelManagementSystem.domain.Room;
import com.tpe.hotelManagementSystem.repository.RoomRepository;
import com.tpe.hotelManagementSystem.repository.RoomRepositoryImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RoomServiceImpl implements RoomService{

    private Scanner scanner;

    private List<Room> roomList = new ArrayList<>();

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room save() {
        scanner=new Scanner(System.in);
        Room room=new Room();

        System.out.println("Room ID: ");
        room.setId(scanner.nextLong());
        System.out.println("Room number :");
        room.setNumber(scanner.nextLine());
        System.out.println("Room capacity :");
        room.setCapacity(scanner.nextInt());
        scanner.nextLine();

        roomList.add(room);

        roomRepository.saveRoom(room);

        return room;
    }
}
