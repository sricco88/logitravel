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
public class Hotel {
    private int id;
    private int idTemplate;
    private String code;
    private String name;
    private String description;
    private int capacity;
    private int roomService;
    private int gymService;
    private double latitude;
    private double logitude;
    private String zone;
    private String address;

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
     * @return the idTemplate
     */
    public int getIdTemplate() {
        return idTemplate;
    }

    /**
     * @param idTemplate the idTemplate to set
     */
    public void setIdTemplate(int idTemplate) {
        this.idTemplate = idTemplate;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * @param capacity the capacity to set
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
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
     * @return the gymService
     */
    public int getGymService() {
        return gymService;
    }

    /**
     * @param gymService the gymService to set
     */
    public void setGymService(int gymService) {
        this.gymService = gymService;
    }

    /**
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the logitude
     */
    public double getLogitude() {
        return logitude;
    }

    /**
     * @param logitude the logitude to set
     */
    public void setLogitude(double logitude) {
        this.logitude = logitude;
    }

    /**
     * @return the zone
     */
    public String getZone() {
        return zone;
    }

    /**
     * @param zone the zone to set
     */
    public void setZone(String zone) {
        this.zone = zone;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
    
}
