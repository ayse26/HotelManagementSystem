package com.tpe.hotelManagementSystem.repository;

import com.tpe.hotelManagementSystem.config.HibernateUtils;
import com.tpe.hotelManagementSystem.domain.Address;
import com.tpe.hotelManagementSystem.domain.Guest;
import com.tpe.hotelManagementSystem.exception.GuestNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

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



//    @Override
//    public Guest findGuestById(Long id) {
//
//        Session session=HibernateUtils.getSessionFactory().openSession();
//        return session.get(Guest.class,id);
//
//    }

    //Baska bir yol
    @Override
    public Guest findGuestById(Long guestId) {
        Session session=HibernateUtils.getSessionFactory().openSession();
        CriteriaBuilder builder=session.getCriteriaBuilder();  //criteriaBuilder olusturuldu,methodlari kullanabiliriz
        CriteriaQuery<Guest>query =builder.createQuery(Guest.class);  //sonuclar Guest data tipinde olacak
        Root<Guest> root=query.from(Guest.class);        //root isminde kök olusturuldu               //Guest class dan bilgileri aldi root icine koydu
        query.select(root).where(builder.equal(root.get("id"),guestId));    //sorgu secimi ve filtreleme

        return session.createQuery(query).uniqueResult();

    }

    @Override
    public List<Guest> findAllGuest() {

        try {
            Session session =HibernateUtils.getSessionFactory().openSession();
            String hql="from Guest";
            List<Guest> guestList=session.createQuery(hql, Guest.class).getResultList();
            return guestList;
        }catch(GuestNotFoundException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void deleteGuestById(Long id) {
        try(Session session=HibernateUtils.getSessionFactory().openSession();) {
            Transaction transaction= session.beginTransaction();
            Guest existingGuest=session.get(Guest.class,id);
            if (existingGuest!=null){
                session.delete(existingGuest);
                transaction.commit();
                session.close();
            }else{
                throw new GuestNotFoundException("Guest not found with id: "+id);
            }
        }catch (GuestNotFoundException e){
            System.out.println(e.getMessage());
        }

    }

}
