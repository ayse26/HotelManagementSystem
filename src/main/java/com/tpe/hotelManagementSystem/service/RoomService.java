package com.tpe.hotelManagementSystem.service;

import com.tpe.hotelManagementSystem.domain.Room;

import java.util.List;

public interface RoomService {
    Room saveRoom();

    Room findRoomById(Long roomId);

    List<Room> findAllRooms();

    void deleteRoomById(Long id);

}
