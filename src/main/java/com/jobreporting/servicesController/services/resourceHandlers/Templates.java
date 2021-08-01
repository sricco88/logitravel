/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobreporting.servicesController.services.resourceHandlers;
import com.jobreporting.classes.Template;
import com.jobreporting.generic.loggerManager.LoggerManager;
import com.jobreporting.generic.utilities.GenericUtility;
import com.jobreporting.servicesBusiness.integration.dataAccess.dao.TemplateDao;
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
@Path("Template")
public class Templates {
    public static LoggerManager logger = GenericUtility.getLogger(Templates.class.getName());
    static final String MSG_PROCESS_NOT_STARTED = "Proccess could not run.";
    static final String MSG_1 = "Success Operation.";  
    static final String MSG_2 = "There is not template in database";
    
      
    @Path("/ListRaw")
    @POST    
    @Produces(MediaType.APPLICATION_JSON)    
    public Response ListRaw(){
        logger.debug("Template - ListRaw - Welcome");
        String msg = MSG_PROCESS_NOT_STARTED;        
        int cod = -1; 
        JSONArray result = new JSONArray();
        TemplateDao templateDao = new TemplateDao();
        ArrayList<Template> templates = templateDao.GetAllTemplates();
        
        if(templates.size() > 0){
            for(Template template : templates){
                JSONObject t = new JSONObject();
                t.put("id",template.getId());
                t.put("code",template.getCode());
                t.put("subject",template.getSubject());
                t.put("header",template.getHeader());
                t.put("body",template.getBody());
                t.put("foot",template.getFoot());
                result.put(t);
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
