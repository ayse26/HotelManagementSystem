package com.tpe.hotelManagementSystem.repository;

import com.tpe.hotelManagementSystem.config.HibernateUtils;
import com.tpe.hotelManagementSystem.domain.Hotel;
import com.tpe.hotelManagementSystem.domain.Room;
import com.tpe.hotelManagementSystem.exception.RoomResourceNotFoundExceptions;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RoomRepositoryImp implements RoomRepository{
    @Override
    public Room saveRoom(Room room) {


        try (Session session=HibernateUtils.getSessionFactory().openSession()){            //hazir methodlardan bir session actik
            Transaction transaction= session.beginTransaction();  //session dan transaction uretildi
            session.persist(room);        //room objesi db e kaydedildi
            transaction.commit();             //commit lemezsek db de kalici olmaz
            HibernateUtils.closeSession(session);    //session kapatildi.          session.close() ayni islevi yapar

            return room;
        }catch (Exception e){
            e.printStackTrace();     //hatanin nerde ve hangi method da tetiklendigini gosterir
            return null;
        }

    }

    @Override
    public Room findRoomById(Long id) {

        Session session=HibernateUtils.getSessionFactory().openSession();
        Room room=session.get(Room.class,id);
        return room;

    }


    @Override
    public void deleteRoomByID(Long id) {
        try (Session session=HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction=session.beginTransaction();

            Room deleteRoom=session.get(Room.class,id);
            if (deleteRoom!=null){
                // hotel listesinden room u kaldırmak gerek
                Hotel hotel=deleteRoom.getHotel();
                if (hotel!=null){
                    hotel.getRooms().remove(deleteRoom);
                }
                // Oda silme islemi
                session.delete(deleteRoom);
            }

            transaction.commit();
            HibernateUtils.closeSession(session);
        }catch (Exception e){
            e.printStackTrace();
        }

    }



    @Override
    public List<Room> findAllRooms() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            String hql = "FROM Room";
            List<Room> rooms = session.createQuery(hql, Room.class).getResultList();         //if(rooms.isEmpty()) neden yazmadik????????????
            return rooms;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
