package com.tpe.hotelManagementSystem.repository;

import com.tpe.hotelManagementSystem.config.HibernateUtils;
import com.tpe.hotelManagementSystem.domain.Hotel;
import com.tpe.hotelManagementSystem.exception.HotelResourceNotFoundException;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.ResultSet;
import java.util.List;

public class HotelRepositoryImpl implements HotelRepository{


    @Override
    public Hotel saveHotel(Hotel hotel) {
        try(Session session= HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction=session.beginTransaction();
            session.save(hotel);
            transaction.commit();
            HibernateUtils.closeSession(session);    //session.close() yapsakta olurdu
            return hotel;
        }catch (Exception e){
            e.printStackTrace();      //hatanin nerede hangi method da tetiklendigini bildirir
        }



        return null;
    }

    @Override
    public Hotel findHotelById(Long id) {

            Session session=HibernateUtils.getSessionFactory().openSession();
            return  session.get(Hotel.class,id);                                     //database den sadece bilgi getirme islemi yaptigimiz icin transaction a gerek yok
                                                                                   //database de degisim olacagi zaman transaction baslatiyoruz

    }

    @Override
    public void deleteHotelById(Long id) {

        try(Session session=HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction= session.beginTransaction();
            Hotel hotelDelete=session.get(Hotel.class,id);                        //burada yukaridaki methodu da kullanabiliriz
            if (hotelDelete!=null){
                session.delete(hotelDelete);
                transaction.commit();
                HibernateUtils.closeSession(session);
            }else {
                throw new HotelResourceNotFoundException("Hotel not found with id : "+id);
            }
        }catch (HibernateException e){                       //ustteki blokta hata olursa buradaki exc i firlatiyor
            e.printStackTrace();
        }
    }

    @Override
    public List<Hotel> findAllHotels() {

        Session session=HibernateUtils.getSessionFactory().openSession();        //burada try icine almadik farkli yollari da gorelim diye
        List<Hotel> hotels = session.createQuery("from Hotel", Hotel.class).getResultList();   //  boyle de yazabiliriz
        //String hql = "from Hotel";
        //List<Hotel> hotels = session.createQuery(hql,Hotel.class).getResultList();
        return hotels;
    }  //burada transaction acmadik cunku find islemlerinde sadece var olani getiriyoruz.session in create query met ile
       //database de kalici degisiklik yaptigimiz islemlerde transaction i kullaniyoruz



    @Override
    public void updateHotel(Hotel hotel) {
        try (Session session=HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Hotel existingHotel=session.get(Hotel.class,hotel.getId());
            if (existingHotel!=null) {                                 //update ve delete islemlerinde once db de var mı diye kontrol ediyoruz
                existingHotel.setName(hotel.getName());
                existingHotel.setLocation(hotel.getLocation());         //service de de yaptik ayni islemi buradada,yoksa update yapmiyor

                session.update(existingHotel);

            }
            transaction.commit();
            HibernateUtils.closeSession(session);
        }catch (HibernateException e){
            e.printStackTrace();
        }
    }







}
