/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobreporting.servicesBusiness.integration.dataAccess.dao;
import com.jobreporting.classes.BookedHotel;
import com.jobreporting.classes.Hotel;
import com.jobreporting.classes.HotelFormated;
import com.jobreporting.classes.RecommendedHotel;
import com.jobreporting.generic.database.DatabaseConnectionManager;
import com.jobreporting.generic.exception.DatabaseConnectionManagerException;
import com.jobreporting.generic.loggerManager.LoggerManager;
import com.jobreporting.generic.utilities.GenericUtility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author santi
 */
public class HotelDao {
     public static LoggerManager logger = GenericUtility.getLogger(HotelDao.class.getName());
     
     public String GetZone(int idHotel){
        logger.debug("HotelDao - GetZone - Welcome"); 
        logger.debug("HotelDao - idHotel - idHotel: "+idHotel); 
        String zone = "";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = DatabaseConnectionManager.getConnection();
            String sql = "SELECT zone FROM hotels WHERE id=?";
            ps = con.prepareStatement(sql, 1);
            ps.setInt(1, idHotel);
            logger.debug("HotelDao - GetZone - query: "+sql);
            rs = ps.executeQuery();
            if(rs.next()){
                zone = rs.getString("zone");
            }  
        }catch(SQLException sqlEx){
            logger.debug("HotelDao - GetZone - SQLException: "+sqlEx.getMessage());
        }catch(Exception ex){
            logger.debug("HotelDao - GetZone - Exception: "+ex.getMessage());
        }finally{
            try{
                DatabaseConnectionManager.returnConnection(con);
                DatabaseConnectionManager.clearResources(ps, rs);
            }catch(DatabaseConnectionManagerException ex){
                logger.debug("HotelDao - GetZone - DatabaseConnectionManagerException: "+ex.getMessage());
            }
        }        
        return zone;      
    }
                    
    public ArrayList<RecommendedHotel> GetMatchingHotels(int idHotel, int roomService, int gymService, String zone){
        logger.debug("HotelDao - GetMatchingHotels - Welcome"); 
        logger.debug("HotelDao - GetMatchingHotels - idHotel: "+idHotel); 
        logger.debug("HotelDao - GetMatchingHotels - roomService: "+roomService); 
        logger.debug("HotelDao - GetMatchingHotels - gymService: "+gymService); 
        logger.debug("HotelDao - GetMatchingHotels - zone: "+zone); 
        ArrayList<RecommendedHotel> hotels = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = DatabaseConnectionManager.getConnection();
            String sql = "SELECT hotels.name, hotels.address,template.subject,template.header,template.body,template.foot FROM hotels INNER JOIN template ON hotels.id = template.id WHERE hotels.zone=? AND hotels.roomservice=? AND hotels.gymservice=?";
            ps = con.prepareStatement(sql, 1);
            ps.setString(1, zone);
            ps.setInt(2, roomService);
            ps.setInt(3, gymService);
            logger.debug("HotelDao - GetMatchingHotels - query: "+sql);
            rs = ps.executeQuery();
            while(rs.next()){
                RecommendedHotel rdh = new RecommendedHotel();
                rdh.setHotelName(rs.getString("name"));
                rdh.setHotelAddress(rs.getString("address"));
                rdh.setSubject(rs.getString("subject"));
                rdh.setHeader(rs.getString("header"));
                rdh.setBody(rs.getString("body"));
                rdh.setFoot(rs.getString("foot"));
                hotels.add(rdh);
            }        
        }catch(SQLException sqlEx){
            logger.debug("HotelDao - GetZone - SQLException: "+sqlEx.getMessage());
        }catch(Exception ex){
            logger.debug("HotelDao - GetZone - Exception: "+ex.getMessage());
        }finally{
            try{
                DatabaseConnectionManager.returnConnection(con);
                DatabaseConnectionManager.clearResources(ps, rs);
            }catch(DatabaseConnectionManagerException ex){
                logger.debug("HotelDao - GetZone - DatabaseConnectionManagerException: "+ex.getMessage());
            }
        }
        return hotels; 
    }
    
    public BookedHotel GetBookedHotel(int idHotel){
        logger.debug("HotelDao - GetBookedHotel - Welcome"); 
        logger.debug("HotelDao - GetBookedHotel - idHotel: "+idHotel);
        BookedHotel bh = new BookedHotel();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = DatabaseConnectionManager.getConnection();
            String sql = "SELECT name,address FROM hotels WHERE id=?";
            ps = con.prepareStatement(sql, 1);
            ps.setInt(1, idHotel);
            logger.debug("HotelDao - GetBookedHotel - query: "+sql);
            rs = ps.executeQuery();
            if(rs.next()){
                bh.setName(rs.getString("name"));
                bh.setAddress(rs.getString("address"));
            }
        }catch(SQLException sqlEx){
            logger.debug("HotelDao - GetBookedHotel - SQLException: "+sqlEx.getMessage());
        }catch(Exception ex){
            logger.debug("HotelDao - GetBookedHotel - Exception: "+ex.getMessage());
        }finally{
            try{
                DatabaseConnectionManager.returnConnection(con);
                DatabaseConnectionManager.clearResources(ps, rs);
            }catch(DatabaseConnectionManagerException ex){
                logger.debug("HotelDao - GetBookedHotel - DatabaseConnectionManagerException: "+ex.getMessage());
            }
        }
        return bh;        
    }
    
    public ArrayList<Hotel> GetAllHotels(){
        logger.debug("HotelDao - GetAllHotels - Welcome"); 
        ArrayList<Hotel> hotels = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = DatabaseConnectionManager.getConnection();
            String sql = "SELECT * FROM hotels";
            ps = con.prepareStatement(sql, 1);
            logger.debug("HotelDao - GetAllHotels - query: "+sql);
            rs = ps.executeQuery();
            while(rs.next()){
               Hotel hotel = new Hotel();
               hotel.setId(rs.getInt("id"));
               hotel.setIdTemplate(rs.getInt("idtemplate"));
               hotel.setCode(rs.getString("code"));
               hotel.setName(rs.getString("name"));
               hotel.setDescription(rs.getString("description"));
               hotel.setCapacity(rs.getInt("capacity"));
               hotel.setRoomService(rs.getInt("roomservice"));
               hotel.setGymService(rs.getInt("gymservice"));
               hotel.setLatitude(rs.getDouble("latitude"));
               hotel.setLogitude(rs.getDouble("longitude"));
               hotel.setZone(rs.getString("zone"));
               hotel.setAddress(rs.getString("address"));
               hotels.add(hotel);
            }            
        }catch(SQLException sqlEx){
            logger.debug("HotelDao - GetAllHotels - SQLException: "+sqlEx.getMessage());
        }catch(Exception ex){
            logger.debug("HotelDao - GetAllHotels - Exception: "+ex.getMessage());
        }finally{
            try{
                DatabaseConnectionManager.returnConnection(con);
                DatabaseConnectionManager.clearResources(ps, rs);
            }catch(DatabaseConnectionManagerException ex){
                logger.debug("HotelDao - GetAllHotels - DatabaseConnectionManagerException: "+ex.getMessage());
            }
        }
        return hotels;
    }    
    
    public ArrayList<HotelFormated> GetAllHotelsFormated(){
       logger.debug("HotelDao - GetAllHotelsFormated - Welcome"); 
        ArrayList<HotelFormated> hotels = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = DatabaseConnectionManager.getConnection();
            String sql = "SELECT hotels.id,template.code AS template,hotels.code,name,description,capacity,roomservice,gymservice,latitude,longitude,zone, address FROM hotels INNER JOIN template ON hotels.idtemplate = template.id";
            ps = con.prepareStatement(sql, 1);
            logger.debug("HotelDao - GetAllHotelsFormated - query: "+sql);
            rs = ps.executeQuery();
            while(rs.next()){
                HotelFormated hotel = new HotelFormated();
                hotel.setId(rs.getInt("id"));
                hotel.setTemplate(rs.getString("template"));
                hotel.setCode(rs.getString("code"));
                hotel.setName(rs.getString("name"));
                hotel.setDescription(rs.getString("description"));
                hotel.setCapacity(rs.getInt("capacity"));
                hotel.setRoomService(rs.getInt("roomservice"));
                hotel.setGymService(rs.getInt("gymservice"));
                hotel.setLatitude(rs.getDouble("latitude"));
                hotel.setLogitude(rs.getDouble("longitude"));
                hotel.setZone(rs.getString("zone"));
                hotel.setAddress(rs.getString("address"));
                hotels.add(hotel);
            }
        }catch(SQLException sqlEx){
            logger.debug("HotelDao - GetAllHotelsFormated - SQLException: "+sqlEx.getMessage());
        }catch(Exception ex){
            logger.debug("HotelDao - GetAllHotelsFormated - Exception: "+ex.getMessage());
        }finally{
            try{
                DatabaseConnectionManager.returnConnection(con);
                DatabaseConnectionManager.clearResources(ps, rs);
            }catch(DatabaseConnectionManagerException ex){
                logger.debug("HotelDao - GetAllHotelsFormated - DatabaseConnectionManagerException: "+ex.getMessage());
            }
        }
        return hotels;   
    }
}
