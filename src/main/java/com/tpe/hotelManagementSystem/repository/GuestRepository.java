package com.tpe.hotelManagementSystem.repository;

import com.tpe.hotelManagementSystem.domain.Guest;

import java.util.List;

public interface GuestRepository {
    void saveGuest(Guest guest);

    Guest findGuestById(Long id);

    List<Guest> findAllGuest();

    void deleteGuestById(Long id);

}
