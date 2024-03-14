package com.tpe.hotelManagementSystem.service;

import com.tpe.hotelManagementSystem.domain.Hotel;

import java.util.List;

public interface HotelService {

    Hotel saveHotel();                  //repository ile ayni isimde olmak zorunda degil

    Hotel findHotelById(Long id);

    void deleteHotelById(Long id);

    List<Hotel> findAllHotels();
}
