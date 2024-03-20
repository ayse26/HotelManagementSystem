package com.tpe.hotelManagementSystem.service;

import com.tpe.hotelManagementSystem.domain.Guest;

import java.util.List;

public interface GuestService {
    Guest saveGuest();

    void findGuestById(Long id);

    List<Guest> findAllGuest();

    void deleteGuestById(Long id);
}
