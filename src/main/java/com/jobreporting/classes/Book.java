/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobreporting.classes;

/**
 *
 * @author santi
 */
public class Book {
    private int id;
    private int idCustomer;
    private int idHotel;
    private int roomService;
    private int gymservice;
    private int numberNights;
    private String start;
    private String end;
    private int paid;
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * @return the idCustomer
     */
    public int getIdCustomer() {
        return idCustomer;
    }

    /**
     * @param idCustomer the idCustomer to set
     */
    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    /**
     * @return the idHotel
     */
    public int getIdHotel() {
        return idHotel;
    }

    /**
     * @param idHotel the idHotel to set
     */
    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    /**
     * @return the roomService
     */
    public int getRoomService() {
        return roomService;
    }

    /**
     * @param roomService the roomService to set
     */
    public void setRoomService(int roomService) {
        this.roomService = roomService;
    }

    /**
     * @return the gymservice
     */
    public int getGymservice() {
        return gymservice;
    }

    /**
     * @param gymservice the gymservice to set
     */
    public void setGymservice(int gymservice) {
        this.gymservice = gymservice;
    }

    /**
     * @return the numberNights
     */
    public int getNumberNights() {
        return numberNights;
    }

    /**
     * @param numberNights the numberNights to set
     */
    public void setNumberNights(int numberNights) {
        this.numberNights = numberNights;
    }

    /**
     * @return the start
     */
    public String getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(String start) {
        this.start = start;
    }

    /**
     * @return the end
     */
    public String getEnd() {
        return end;
    }

    /**
     * @param end the end to set
     */
    public void setEnd(String end) {
        this.end = end;
    }

    /**
     * @return the paid
     */
    public int getPaid() {
        return paid;
    }

    /**
     * @param paid the paid to set
     */
    public void setPaid(int paid) {
        this.paid = paid;
    }    
}
