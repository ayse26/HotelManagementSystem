package com.tpe.hotelManagementSystem.exception;

//ilk önce RuntimeException class i extend ediyoruz,sonra generate ile mesaji kendimiz belirleyecegimiz cons seciyoruz

public class HotelResourceNotFoundException extends RuntimeException{

    public HotelResourceNotFoundException(String message) {  // Generate->Constructor->RuntimeException(message:String)
        super(message);
    }

}
