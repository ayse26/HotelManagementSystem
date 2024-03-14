package com.tpe.hotelManagementSystem.repository;

import com.tpe.hotelManagementSystem.domain.Room;

import java.util.List;

public interface RoomRepository {
    Room saveRoom(Room room);

    Room findRoomById(Long id);

    List<Room> findAllRooms();

    void deleteRoomByID(Long id);
}
