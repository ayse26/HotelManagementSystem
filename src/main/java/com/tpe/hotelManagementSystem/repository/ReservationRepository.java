package com.tpe.hotelManagementSystem.repository;

import com.tpe.hotelManagementSystem.domain.Reservation;

import java.util.List;

public interface ReservationRepository {

     Reservation saveReservation(Reservation reservation);
     Reservation findReservationById(Long id);
     List<Reservation> findAll();
     void deleteReservationById(Long id);
}
