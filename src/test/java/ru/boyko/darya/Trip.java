package ru.boyko.darya;

import java.util.Date;

public class Trip {
    public Integer ID;
    public String DepartureCity;
    public String DestinationCity;
    public Date OutboundDepartureDateTime;
    public Date OutboundArrivalDateTime;
    public Date ReturnDepartureDateTime;
    public Date ReturnArrivalDateTime;
    public String HotelName;
    public String HotelAddress;
    public Person[] Persons;
    public Integer Price;
    public Boolean Finished;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getDepartureCity() {
        return DepartureCity;
    }

    public void setDepartureCity(String departureCity) {
        DepartureCity = departureCity;
    }

    public String getDestinationCity() {
        return DestinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        DestinationCity = destinationCity;
    }

    public Date getOutboundDepartureDateTime() {
        return OutboundDepartureDateTime;
    }

    public void setOutboundDepartureDateTime(Date outboundDepartureDateTime) {
        OutboundDepartureDateTime = outboundDepartureDateTime;
    }

    public Date getOutboundArrivalDateTime() {
        return OutboundArrivalDateTime;
    }

    public void setOutboundArrivalDateTime(Date outboundArrivalDateTime) {
        OutboundArrivalDateTime = outboundArrivalDateTime;
    }

    public Date getReturnDepartureDateTime() {
        return ReturnDepartureDateTime;
    }

    public void setReturnDepartureDateTime(Date returnDepartureDateTime) {
        ReturnDepartureDateTime = returnDepartureDateTime;
    }

    public Date getReturnArrivalDateTime() {
        return ReturnArrivalDateTime;
    }

    public void setReturnArrivalDateTime(Date returnArrivalDateTime) {
        ReturnArrivalDateTime = returnArrivalDateTime;
    }

    public String getHotelName() {
        return HotelName;
    }

    public void setHotelName(String hotelName) {
        HotelName = hotelName;
    }

    public String getHotelAddress() {
        return HotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        HotelAddress = hotelAddress;
    }

    public Person[] getPersons() {
        return Persons;
    }

    public void setPersons(Person[] persons) {
        Persons = persons;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }

    public Boolean getFinished() {
        return Finished;
    }

    public void setFinished(Boolean finished) {
        Finished = finished;
    }

    public static class Person{
        public String FirstName;
        public String LastName;
        public String PassportNumber;
    }
}
