package com.tpe.hotelManagementSystem.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "t_reservation")
public class Reservation {
    @GeneratedValue(generator = "sequence",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sequence",                                              //name yukaridakiyle ayni olmak zorunda
                        sequenceName = "reservation_id",
                        initialValue = 1000,                                           //id baslangic degeri
                        allocationSize = 1 )                                            //id uretilirken kacar kacar artacak
                                                                                      //Stratejim yukarida verilen isim olsun
    @Id
    private long id;

    @Column(nullable = false)
    private LocalDate checkinDate;

    @Column(nullable = false)
    private LocalDate checkioutDate;

    @ManyToOne
    @JoinColumn(name="guest_id",nullable = false)
    private Guest guest;

    @ManyToOne
    @JoinColumn(name = "room_id",nullable = false)
    private Room room;

    public long getId() {
        return id;
    }

//    public void setId(long id) {
//        this.id = id;
//    }

    public LocalDate getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(LocalDate checkinDate) {
        this.checkinDate = checkinDate;
    }

    public LocalDate getCheckioutDate() {
        return checkioutDate;
    }

    public void setCheckioutDate(LocalDate checkioutDate) {
        this.checkioutDate = checkioutDate;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", checkinDate=" + checkinDate +
                ", checkioutDate=" + checkioutDate +
//                ", guest=" + guest +
//                ", room=" + room +
                '}';
    }
}

