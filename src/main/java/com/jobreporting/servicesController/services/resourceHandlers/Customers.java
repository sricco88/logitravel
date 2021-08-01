/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobreporting.servicesController.services.resourceHandlers;
import com.jobreporting.classes.Customer;
import com.jobreporting.generic.loggerManager.LoggerManager;
import com.jobreporting.generic.utilities.GenericUtility;
import com.jobreporting.servicesBusiness.integration.dataAccess.dao.CustomerDao;
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
@Path("/Customers")
public class Customers {
    public static LoggerManager logger = GenericUtility.getLogger(Customers.class.getName());
    static final String MSG_PROCESS_NOT_STARTED = "Proccess could not run.";
    static final String MSG_1 = "Success Operation.";  
    static final String MSG_2 = "Could not found any customer in database.";
    
    @Path("/List")
    @POST    
    @Produces(MediaType.APPLICATION_JSON)    
    public Response List(){
        logger.debug("Customers - Listing Custormers");
        String msg = MSG_PROCESS_NOT_STARTED;
        int cod = -1; 
        JSONArray result = new JSONArray();
        CustomerDao customerDao = new CustomerDao();
        ArrayList<Customer> customers = customerDao.List();        
        
        if(customers.size() > 0){
            for(Customer customer : customers){
                JSONObject cs = new JSONObject();
                cs.put("id",customer.getId());
                cs.put("code",customer.getCode());
                cs.put("name", customer.getName());
                cs.put("surname",customer.getSurname());
                cs.put("dni",customer.getDni());
                cs.put("gender",customer.getGender());
                cs.put("age", customer.getAge());
                cs.put("phoneNumber",customer.getPhoneNumber());
                cs.put("email",customer.getEmail());
                cs.put("address",customer.getAddress());
                result.put(cs);
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
