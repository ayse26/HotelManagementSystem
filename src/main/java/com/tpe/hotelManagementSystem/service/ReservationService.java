package com.tpe.hotelManagementSystem.service;

import com.tpe.hotelManagementSystem.domain.Reservation;

import java.util.List;

public interface ReservationService {

    Reservation saveReservation();
    Reservation findReservationById(Long id);
    List<Reservation> findAll();
    void deleteReservationById(Long id);
}
