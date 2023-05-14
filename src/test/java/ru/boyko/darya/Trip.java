package ru.boyko.darya;

import java.util.Date;

public class Trip {
    public Integer id;
    public String departureCity;
    public String destinationCity;

    public Date outboundDepartureDateTime;
    public Date outboundArrivalDateTime;
    public Date returnDepartureDateTime;
    public Date returnArrivalDateTime;
    public String hotelName;
    public String hotelAddress;
    public Person[] persons;
    public Integer price;
    public Boolean finished;


    public static class Person{
        public String firstName;
        public String lastName;
        public String passportNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public Date getOutboundDepartureDateTime() {
        return outboundDepartureDateTime;
    }

    public void setOutboundDepartureDateTime(Date outboundDepartureDateTime) {
        this.outboundDepartureDateTime = outboundDepartureDateTime;
    }

    public Date getOutboundArrivalDateTime() {
        return outboundArrivalDateTime;
    }

    public void setOutboundArrivalDateTime(Date outboundArrivalDateTime) {
        this.outboundArrivalDateTime = outboundArrivalDateTime;
    }

    public Date getReturnDepartureDateTime() {
        return returnDepartureDateTime;
    }

    public void setReturnDepartureDateTime(Date returnDepartureDateTime) {
        this.returnDepartureDateTime = returnDepartureDateTime;
    }

    public Date getReturnArrivalDateTime() {
        return returnArrivalDateTime;
    }

    public void setReturnArrivalDateTime(Date returnArrivalDateTime) {
        this.returnArrivalDateTime = returnArrivalDateTime;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public Person[] getPersons() {
        return persons;
    }

    public void setPersons(Person[] persons) {
        this.persons = persons;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

}
