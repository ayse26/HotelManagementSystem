package com.tpe.hotelManagementSystem.repository;

import com.tpe.hotelManagementSystem.domain.Hotel;

public interface HotelRepository {

    Hotel saveHotel(Hotel hotel);
    Hotel findHotelById(Long id);

}
