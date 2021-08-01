/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobreporting.servicesController.services.resourceHandlers;
import com.jobreporting.classes.Book;
import com.jobreporting.classes.BookFormated;
import com.jobreporting.generic.loggerManager.LoggerManager;
import com.jobreporting.generic.utilities.GenericUtility;
import com.jobreporting.servicesBusiness.integration.dataAccess.dao.BookingDao;
import java.util.ArrayList;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONArray;
import org.json.JSONObject;
/**
 *
 * @author santi
 */
@Path("/Booking")
public class Booking {
    public static LoggerManager logger = GenericUtility.getLogger(Booking.class.getName());
    static final String MSG_PROCESS_NOT_STARTED = "Proccess could not run.";
    static final String MSG_1 = "Success Operation.";  
    static final String MSG_2 = "Could not found any books";    
    
    @Path("/ListRaw")
    @POST    
    @Produces(MediaType.APPLICATION_JSON)    
    public Response ListRaw(){
        logger.debug("Booking - ListRaw - Welcome");
        String msg = MSG_PROCESS_NOT_STARTED;
        int cod = -1; 
        JSONArray result = new JSONArray();
        BookingDao bookingDao = new BookingDao();
        ArrayList<Book> books = bookingDao.GetAllBooks();  
        
        if(books.size() > 0){
            for(Book book : books){
                JSONObject b = new JSONObject();
                b.put("id",book.getId());
                b.put("idCustomer", book.getIdCustomer());
                b.put("idHotel",book.getIdHotel());
                b.put("roomService",book.getRoomService());
                b.put("gymService",book.getGymservice());
                b.put("numberNights",book.getNumberNights());
                b.put("start",book.getStart());
                b.put("end",book.getEnd());
                b.put("paid",book.getPaid());
                result.put(b);
            }
            cod = 1;
            msg = MSG_1;
        }else{
            cod = 2;
            msg = MSG_2;
        }
        
        JSONObject response = new JSONObject();             
        response.put("msg",msg);
        response.put("cod",cod);
        response.put("result",result);
        return Response.status(200).entity(response.toString()).build();        
    }    
    
    @Path("/ListFormated")
    @POST    
    @Produces(MediaType.APPLICATION_JSON)    
    public Response ListFormated(){
        logger.debug("Booking - ListFormated - Welcome");
        String msg = MSG_PROCESS_NOT_STARTED;
        int cod = -1; 
        JSONArray result = new JSONArray();
        BookingDao bookingDao = new BookingDao();    
        ArrayList<BookFormated> booksFormated = bookingDao.GetAllBooksFormated();
        
        if(booksFormated.size() > 0){
            for(BookFormated book : booksFormated){
               JSONObject bf = new JSONObject();
                bf.put("id",book.getId());
                bf.put("customerName",book.getCustomerName());
                bf.put("customerSurname",book.getCustomerSurname());
                bf.put("hotelName",book.getHotelName());
                bf.put("roomService",book.getRoomService());
                bf.put("gymService",book.getGymService());
                bf.put("numberNights",book.getNumberNights());
                bf.put("start",book.getStart());
                bf.put("end",book.getEnd());
                bf.put("paid",book.getPaid());
                result.put(bf);                
            }            
            cod = 1;
            msg = MSG_1;
        }else{
            cod = 2;
            msg = MSG_2;
        }           
        
        JSONObject response = new JSONObject();             
        response.put("msg",msg);
        response.put("cod",cod);
        response.put("result",result);
        return Response.status(200).entity(response.toString()).build();
    }    
}