/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobreporting.servicesBusiness.integration.dataAccess.dao;
import com.jobreporting.classes.Book;
import com.jobreporting.classes.BookFormated;
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
public class BookingDao {
    public static LoggerManager logger = GenericUtility.getLogger(BookingDao.class.getName());
    
    public ArrayList<Book> GetBooking(int idCustomer){
        logger.debug("BookingDao - GetBooking - Welcome");
        logger.debug("BookingDao - GetBooking - idCustomer: "+idCustomer);        
        ArrayList<Book> books = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = DatabaseConnectionManager.getConnection();
            String sql = "SELECT * FROM book WHERE idcustomer=?";
            ps = con.prepareStatement(sql, 1);
            ps.setInt(1, idCustomer);
            logger.debug("BookingDao - GetBooking - query: "+sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setIdCustomer(rs.getInt("idcustomer"));
                book.setIdHotel(rs.getInt("idhotel"));
                book.setRoomService(rs.getInt("roomservice"));
                book.setGymservice(rs.getInt("gymservice"));
                book.setNumberNights(rs.getInt("numbernights"));
                book.setStart(rs.getString("start"));
                book.setEnd(rs.getString("end"));
                books.add(book);
            }
        }catch(SQLException sqlEx){
            logger.debug("BookingDao - GetBooking - SQLException: "+sqlEx.getMessage());
        }catch(Exception ex){
            logger.debug("BookingDao - GetBooking - Exception: "+ex.getMessage());
        }finally{
            try{
                DatabaseConnectionManager.returnConnection(con);
                DatabaseConnectionManager.clearResources(ps, rs);
            }catch(DatabaseConnectionManagerException ex){
                logger.debug("BookingDao - GetBooking - DatabaseConnectionManagerException: "+ex.getMessage());
            }
        }        
        return books;       
    }
    
    public ArrayList<Book> GetAllBooks(){
        logger.debug("BookingDao - GetAllBooks - Welcome");
        ArrayList<Book> books = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = DatabaseConnectionManager.getConnection();
            String sql = "SELECT * FROM book";
            ps = con.prepareStatement(sql, 1);
            logger.debug("BookingDao - GetAllBooks - query: "+sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setIdCustomer(rs.getInt("idcustomer"));
                book.setIdHotel(rs.getInt("idhotel"));
                book.setRoomService(rs.getInt("roomservice"));
                book.setGymservice(rs.getInt("gymservice"));
                book.setNumberNights(rs.getInt("numbernights"));
                book.setStart(rs.getString("start"));
                book.setEnd(rs.getString("end"));
                book.setPaid(rs.getInt("paid"));
                books.add(book);
            }         
        }catch(SQLException sqlEx){
            logger.debug("BookingDao - GetAllBooks - SQLException: "+sqlEx.getMessage());
        }catch(Exception ex){
            logger.debug("BookingDao - GetAllBooks - Exception: "+ex.getMessage());
        }finally{
            try{
                DatabaseConnectionManager.returnConnection(con);
                DatabaseConnectionManager.clearResources(ps, rs);
            }catch(DatabaseConnectionManagerException ex){
                logger.debug("BookingDao - GetAllBooks - DatabaseConnectionManagerException: "+ex.getMessage());
            }
        }        
        return books;        
    }
    
    public ArrayList<BookFormated> GetAllBooksFormated (){
        logger.debug("BookingDao - GetAllBooksFormated - Welcome");
        ArrayList<BookFormated> books = new ArrayList<>();        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = DatabaseConnectionManager.getConnection();
            String sql = "SELECT book.id,customers.name AS customername,customers.surname,hotels.name as hotelname,book.roomservice,book.gymservice,book.numbernights,book.start,book.end,book.paid FROM book INNER JOIN customers ON book.idcustomer = customers.id INNER JOIN hotels ON book.idhotel = hotels.id;";
            ps = con.prepareStatement(sql, 1);
            logger.debug("BookingDao - GetAllBooks - query: "+sql);
            rs = ps.executeQuery();
            while(rs.next()){                
                BookFormated bf = new BookFormated();                
                bf.setId(rs.getInt("id"));
                bf.setCustomerName(rs.getString("customername"));
                bf.setCustomerSurname(rs.getString("surname"));
                bf.setHotelName(rs.getString("hotelname"));
                int roomService = rs.getInt("roomservice");
                if(roomService == 1){
                    bf.setRoomService("Yes");
                }else{
                    bf.setRoomService("No");
                }
                int gymService = rs.getInt("roomservice");
                if(gymService == 1){
                    bf.setGymService("Yes");
                }else{
                    bf.setGymService("No");
                }
                bf.setNumberNights(rs.getInt("numbernights"));
                
                String start = rs.getString("start");
                String[] startArr = start.split("-");
                String startDef = startArr[2]+"/"+startArr[1]+"/"+startArr[0];
                bf.setStart(startDef);
                
                String end = rs.getString("end");
                String[] endArr = end.split("-");
                String endDef = endArr[2]+"/"+endArr[1]+"/"+endArr[0];
                bf.setEnd(endDef);
                
                int paid = rs.getInt("paid");
                if(paid == 1){
                    bf.setPaid("Yes");
                }else{
                    bf.setPaid("No");
                }                
                books.add(bf);               
            }           
        }catch(SQLException sqlEx){
            logger.debug("BookingDao - GetAllBooksFormated - SQLException: "+sqlEx.getMessage());
        }catch(Exception ex){
            logger.debug("BookingDao - GetAllBooksFormated - Exception: "+ex.getMessage());
        }finally{
            try{
                DatabaseConnectionManager.returnConnection(con);
                DatabaseConnectionManager.clearResources(ps, rs);
            }catch(DatabaseConnectionManagerException ex){
                logger.debug("BookingDao - GetAllBooksFormated - DatabaseConnectionManagerException: "+ex.getMessage());
            }
        }   
         return books; 
    }
}
