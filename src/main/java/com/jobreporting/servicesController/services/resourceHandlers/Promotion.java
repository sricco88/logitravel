package com.jobreporting.servicesController.services.resourceHandlers;
import com.jobreporting.classes.Book;
import com.jobreporting.classes.BookedHotel;
import com.jobreporting.classes.RecommendedHotel;
import com.jobreporting.generic.loggerManager.LoggerManager;
import com.jobreporting.generic.utilities.GenericUtility;
import com.jobreporting.servicesBusiness.integration.dataAccess.dao.BookingDao;
import com.jobreporting.servicesBusiness.integration.dataAccess.dao.CustomerDao;
import com.jobreporting.servicesBusiness.integration.dataAccess.dao.HotelDao;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
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
@Path("/Promotion")
public class Promotion{
    public static LoggerManager logger = GenericUtility.getLogger(Promotion.class.getName());
    static final String MSG_PROCESS_NOT_STARTED = "Proccess could not run.";
    static final String MSG_1 = "Success Operation.";  
    static final String MSG_2 = "The name of the customer cannot be empty.";
    static final String MSG_3 = "The surname of the customer cannot be empty.";
    static final String MSG_4 = "The customer does not exist.";
    static final String MSG_5 = "Unable to find any book for customer ";
    static final String MSG_6 = "Unable to retrive customer booking information.";
    static final String MSG_7 = "Unable to get the id of the booked hotel";
    static final String MSG_8 = "Unable to get the zone of the booked hotel";
    static final String MSG_9 = "Could not fuound any hotel to recommend to customer ";
    
    @Path("/SendPromotion")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)    
    public Response SendPromotion(String query){
        logger.debug("SendPromotion - query: "+query);     
        
        //RETRIEVE REQUEST PARAMETERS
        JSONObject jsonObjectRequest = new JSONObject(query);       
        String name = jsonObjectRequest.get("name").toString();
        String surname = jsonObjectRequest.get("surname").toString();       
          
        String msg = MSG_PROCESS_NOT_STARTED;
        int cod = -1; 
        JSONArray result = new JSONArray();
        
        //1-VALIDATE NAME AND SURNAME
        if(!name.equals("") && !surname.equals("")){
            //2-VALIDATE IF CUSTOMER EXISTS RETRIVING ITS ID
            CustomerDao customerDao = new CustomerDao();
            int idCustomer = customerDao.GetID(name, surname);
            logger.debug("SendPromotion - idCustomer: "+idCustomer);
            if(idCustomer != -1){
                //3-CHECK IF CUSTOMER HAS DONE ANY BOOK
                boolean hasCustomerAnyBook = customerDao.HasCustomerAnyBook(idCustomer);
                if(hasCustomerAnyBook){
                    BookingDao bookingDao = new BookingDao();
                    ArrayList<Book> books = bookingDao.GetBooking(idCustomer);
                    
                    if(books.size() > 0){
                        JSONObject client = new JSONObject();
                        client.put("Action","Sending email/sms to client...");
                        client.put("clientName",name);
                        client.put("clientSurname",surname);
                        result.put(client);
                        
                        //ALGORITH:
                        //FOREACH BOOK DO
                        //GET ALL HOTELS THAT ARE CLOSE TO ONE BOOKED: WE USE FIELD ZONA FOR SIMPLICITY, BUT COULD USE CORDINATES AND CALCULATE DISTANCE WITH GOOGLE MAPS API
                        //GET THE HOTELS THAT MATCH WITH CUSTOMER PREFERENCES. FOR SIMPLICITY WE USE 2 FIELDS: ROOMSERVICE AND GYMSERVICE
                        //GET HOTEL TEMPLATE THAT MATCH STEP BEFORE
                        HotelDao hotelDao = new HotelDao();
                        ArrayList<RecommendedHotel> recommendedHotels;
                        for(Book book : books){
                            int idHotel = book.getIdHotel(); 
                            BookedHotel  bh = new BookedHotel();
                            bh = hotelDao.GetBookedHotel(idHotel);
                            //GET ZONE HOTEL
                            if(idHotel != -1){                                
                                String zone = hotelDao.GetZone(idHotel);
                                if(!zone.equals("")){
                                    int roomService = book.getRoomService();
                                    int gymService = book.getGymservice();
                                    recommendedHotels = hotelDao.GetMatchingHotels(idHotel,roomService,gymService,zone);
                                    JSONArray hotels = new JSONArray();
                                    
                                    if(recommendedHotels.size() > 0){                                        
                                        JSONObject bookedHotelInfo = new JSONObject();
                                        bookedHotelInfo.put("Name Booked Hotel",bh.getName());
                                        bookedHotelInfo.put("Address Booked Hotel",bh.getAddress());                                        
                                        hotels.put(bookedHotelInfo);
                                        
                                        JSONArray recomendations = new JSONArray();
                                        for(RecommendedHotel rh : recommendedHotels){
                                            JSONObject recommend = new JSONObject();
                                            recommend.put("Name Rocommended Hotel", rh.getHotelName());
                                            recommend.put("Address Recommended Hotel",rh.getHotelAddress());
                                            recommend.put("Subject",rh.getSubject());
                                            recommend.put("Header",rh.getHeader());
                                            recommend.put("Body",rh.getBody());
                                            recommend.put("Foot",rh.getFoot());
                                            recomendations.put(recommend);                                            
                                        }
                                        hotels.put(recomendations);                                   
                                    }else{
                                        cod = 9;
                                        msg = MSG_9+ name+" "+surname;
                                    }
                                    result.put(hotels);
                                }else{
                                    cod = 8;
                                    msg = MSG_8;
                                }
                            }else{
                                cod = 7;
                                msg = MSG_7;
                            }
                            cod = 1;
                            msg = MSG_1;
                        }                      
                    }else{
                       cod = 6;
                       msg = MSG_6;
                    }               
                }else{
                    cod = 5;
                    msg = MSG_5 + name+" "+surname;
                }               
            }else{
                cod = 4;
                msg = MSG_4;
            }     
        }else{
            if(name.equals("")){
                cod = 2;
                msg = MSG_2;
            }else if(surname.equals("")){
                cod = 3;
                msg = MSG_3;
            }
        }        
        
        JSONObject response = new JSONObject();             
        response.put("msg",msg);
        response.put("cod",cod);
        response.put("result",result);
        return Response.status(200).entity(response.toString()).build(); 
    }    
  }


               