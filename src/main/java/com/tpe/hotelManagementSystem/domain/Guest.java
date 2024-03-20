package com.tpe.hotelManagementSystem.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_guests")
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)      //id i otomatik uretilsin diye
    private Long id;
    private String name;

    @Embedded                          //gomulu .Adress class inin tablosu olmayacak ama guess class in tablosunda adres bilgileri olacak
    private Address addres;

    private LocalDateTime createDate;


    @PrePersist
    public void Prepersist(){           //daha db e eklenmeden once bu islemi yap
        createDate=LocalDateTime.now();
    }



    @OneToMany(mappedBy = "guest",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)       //Eager iken tum bilgileri(room,reservation,guest) getiriyor  ,
                                                                                                               // Lazy iken sadece guest ile ilgileri geririr  ,deneme amacli lazy yaptik
    private List<Reservation> reservations=new ArrayList<>();

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Long getId() {
        return id;
    }

 //   public void setId(Long id) {
 //       this.id = id;
 //   }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddres() {
        return addres;
    }

    public void setAddres(Address addres) {
        this.addres = addres;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

//    public void setCreateDate(LocalDateTime createDate) {        //otomatik olustugu icin setter a gerek yok
//        this.createDate = createDate;
//    }


    @Override
    public String toString() {
        return "Guest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", addres=" + addres +
                ", createDate=" + createDate +
//                ", reservations=" + reservations +
                '}';
    }
}


/*
hibernate.cfg.xml dosyamda
 <!-- Mapping entity classes -->
        <mapping class="com.tpe.hotelManagementSystem.domain.Hotel" />
        <mapping class="com.tpe.hotelManagementSystem.domain.Room" />

bu kisima domain e ekledigim class larin hepsini eklemem lazim.Son yazdigimiz address ve guest claslari ni da ekliyoruz
Yoksa hata verir
 */