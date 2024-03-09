package com.tpe.hotelManagementSystem.config;

import com.tpe.hotelManagementSystem.domain.Hotel;
import com.tpe.hotelManagementSystem.domain.Room;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//baglantilarimizi yaptigimiz package;config
public class HibernateUtils {

    private static final SessionFactory sessionFactory;

    static{
        try {
            Configuration configuration=new Configuration().
                    configure("hibernate.cfg.xml");
                   // addAnnotatedClass(Hotel.class).addAnnotatedClass(Room.class);  //bunu ekleme gerek yok artik.Cunku
                                                                                     // hibernate.cfg.xml de ekledik bunu(Mapping entity classes)
            sessionFactory=configuration.buildSessionFactory();      //konfigurasyon dosyamizdan sessionFactory olusturduk

        }catch (Throwable ex){
            System.err.println("Initial SessionFactory is failed." +ex);   // err: hatanin kirmizi renkte yazilmasini sagliyor
            throw new ExceptionInInitializerError(ex);        //bunu yazmasakta olur
        }
    }

    //getSessionFactory methodu
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    //sessionFactory shutDown
    public static void shutDown(){
        getSessionFactory().close();
    }

    //sessionClose
    public static void closeSession(Session session){
        if (session!=null && session.isOpen()){
            session.close();
        }
    }


}

//Uygulama ayaga kalkar kalkmaz islemesini istedigimiz icin static seciyoruz,degismesin hepsini bunu kullansin diye de final


/*

"util" Java'da "utility" (yardımcı) kısaltmasıdır. "util" paketleri, genellikle sık kullanılan yardımcı sınıfları
ve işlevleri içerir.Java'da, standart kütüphane olan Java API'sinde (Application Programming Interface - Uygulama Programlama Arayüzü)
java.util paketi, genel amaçlı yardımcı sınıfları içerir. Bu paket, koleksiyonlar (Listeler, Setler, Haritalar), zaman işlemleri,
rastgele sayı üretimi gibi çeşitli işlevleri içerir. Bu nedenle, birçok Java programı, java.util paketindeki sınıfları ve işlevleri
kullanarak farklı görevleri gerçekleştirir.

 */