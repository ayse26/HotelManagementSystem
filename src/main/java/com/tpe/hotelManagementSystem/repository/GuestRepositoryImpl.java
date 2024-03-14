package com.tpe.hotelManagementSystem.repository;

import com.tpe.hotelManagementSystem.config.HibernateUtils;
import com.tpe.hotelManagementSystem.domain.Address;
import com.tpe.hotelManagementSystem.domain.Guest;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class GuestRepositoryImpl implements GuestRepository{
    @Override
    public void saveGuest(Guest guest) {
        try (Session session= HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction= session.beginTransaction();

            Address address=new Address();
            address.setStreet(guest.getAddres().getStreet());
            address.setCity(guest.getAddres().getCity());
            address.setCountry(guest.getAddres().getCountry());
            address.setZipCode(guest.getAddres().getZipCode());

            guest.setAddres(address);
            session.persist(guest);
            transaction.commit();
            HibernateUtils.closeSession(session);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
