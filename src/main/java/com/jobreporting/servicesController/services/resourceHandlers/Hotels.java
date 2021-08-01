/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobreporting.servicesController.services.resourceHandlers;
import com.jobreporting.classes.Hotel;
import com.jobreporting.classes.HotelFormated;
import com.jobreporting.generic.loggerManager.LoggerManager;
import com.jobreporting.generic.utilities.GenericUtility;
import com.jobreporting.servicesBusiness.integration.dataAccess.dao.HotelDao;
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
@Path("Hotels")
public class Hotels {
    public static LoggerManager logger = GenericUtility.getLogger(Hotels.class.getName());
    static final String MSG_PROCESS_NOT_STARTED = "Proccess could not run.";
    static final String MSG_1 = "Success Operation.";  
    static final String MSG_2 = "There is not hotels in database";
    
    @Path("/ListRaw")
    @POST    
    @Produces(MediaType.APPLICATION_JSON)    
    public Response ListRaw(){
        logger.debug("Hotels - ListRaw - Welcome");
        String msg = MSG_PROCESS_NOT_STARTED;        
        int cod = -1; 
        JSONArray result = new JSONArray();
        HotelDao hotelDao = new HotelDao();
        ArrayList<Hotel> hotels = hotelDao.GetAllHotels();
        
        if(hotels.size() > 0){
            for(Hotel hotel : hotels){
                JSONObject h = new JSONObject();
                h.put("id",hotel.getId());
                h.put("idTemplate",hotel.getIdTemplate());
                h.put("code",hotel.getCode());
                h.put("name",hotel.getName());
                h.put("description",hotel.getDescription());
                h.put("capacity",hotel.getCapacity());
                h.put("roomService",hotel.getRoomService());
                h.put("gymService",hotel.getGymService());
                h.put("latitude",hotel.getLatitude());
                h.put("longitud",hotel.getLogitude());
                h.put("zone",hotel.getZone());
                h.put("address",hotel.getAddress());
                result.put(h);
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
        logger.debug("Hotels - ListFormated - Welcome");
        String msg = MSG_PROCESS_NOT_STARTED;        
        int cod = -1; 
        JSONArray result = new JSONArray();
        HotelDao hotelDao = new HotelDao();
        ArrayList<HotelFormated> hotels = hotelDao.GetAllHotelsFormated();
        
        if(hotels.size() > 0){
            for(HotelFormated hotel : hotels){
                JSONObject h = new JSONObject();
                h.put("id",hotel.getId());
                h.put("templateCode",hotel.getTemplate());
                h.put("code",hotel.getCode());
                h.put("name",hotel.getName());
                h.put("description",hotel.getDescription());
                h.put("capacity",hotel.getCapacity());
                h.put("roomService",hotel.getRoomService());
                h.put("gymService",hotel.getGymService());
                h.put("latitude",hotel.getLatitude());
                h.put("longitud",hotel.getLogitude());
                h.put("zone",hotel.getZone());
                h.put("address",hotel.getAddress());
                result.put(h);
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
