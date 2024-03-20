package com.tpe.hotelManagementSystem.repository;

import com.tpe.hotelManagementSystem.config.HibernateUtils;
import com.tpe.hotelManagementSystem.domain.Reservation;
import com.tpe.hotelManagementSystem.exception.ReservationNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ReservationRepositoryImpl implements ReservationRepository{
    @Override
    public Reservation saveReservation(Reservation reservation) {
        try (Session session= HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction=session.beginTransaction();
            session.persist(reservation);
            transaction.commit();
            session.close();

            return reservation;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Reservation findReservationById(Long id) {

        try {
            Session session=HibernateUtils.getSessionFactory().openSession();
            Reservation existingReservation=session.get(Reservation.class,id);
            if (existingReservation!=null){
                System.out.println("-------------------");
                System.out.println(existingReservation);
                return existingReservation;
            }else {
                throw new ReservationNotFoundException("Reservation not found with id: "+id);
            }
        }catch (ReservationNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Reservation> findAll() {

        try {
            Session session=HibernateUtils.getSessionFactory().openSession();
            String hql="from Reservation";
            List<Reservation> reservations=session.createQuery(hql,Reservation.class).getResultList();
            return reservations;
        }catch (ReservationNotFoundException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteReservationById(Long id) {
        try (Session session=HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction= session.beginTransaction();
            Reservation existingReservation=session.get(Reservation.class,id);
            if (existingReservation!=null){
                session.delete(existingReservation);
                transaction.commit();
                HibernateUtils.closeSession(session);
            }else {
                throw new ReservationNotFoundException("Reservation not found with id: "+id);
            }
        }catch (ReservationNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

}
