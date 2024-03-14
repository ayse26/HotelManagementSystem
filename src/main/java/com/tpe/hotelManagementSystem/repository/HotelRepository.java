package com.tpe.hotelManagementSystem.repository;

import com.tpe.hotelManagementSystem.domain.Hotel;

import java.util.List;

public interface HotelRepository {

    Hotel saveHotel(Hotel hotel);
    Hotel findHotelById(Long id);

    void deleteHotelById(Long id);

    List<Hotel> findAllHotels();
}
