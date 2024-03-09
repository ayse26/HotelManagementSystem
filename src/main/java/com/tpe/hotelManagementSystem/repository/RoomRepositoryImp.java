package com.tpe.hotelManagementSystem.repository;

import com.tpe.hotelManagementSystem.config.HibernateUtils;
import com.tpe.hotelManagementSystem.domain.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RoomRepositoryImp implements RoomRepository{
    @Override
    public Room saveRoom(Room room) {


        try (Session session=HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction= session.beginTransaction();
            session.save(room);
            transaction.commit();
            HibernateUtils.closeSession(session);

            return room;
        }catch (Exception e){
            e.printStackTrace();
        }



        return null;
    }
}
