/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobreporting.servicesBusiness.integration.dataAccess.dao;
import com.jobreporting.classes.Template;
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
public class TemplateDao {
    public static LoggerManager logger = GenericUtility.getLogger(TemplateDao.class.getName());
    
    public ArrayList<Template> GetAllTemplates(){
        logger.debug("TemplateDao - GetAllTemplates - Welcome"); 
        ArrayList<Template> templates = new ArrayList<>();
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = DatabaseConnectionManager.getConnection();
            String sql = "SELECT * FROM template";
            ps = con.prepareStatement(sql, 1);            
            logger.debug("TemplateDao - GetAllTemplates - query: "+sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Template t = new Template();
                t.setId(rs.getInt("id"));
                t.setCode(rs.getString("code"));
                t.setSubject(rs.getString("subject"));
                t.setHeader(rs.getString("header"));
                t.setBody(rs.getString("body"));
                t.setFoot(rs.getString("foot"));
                templates.add(t);
            }  
        }catch(SQLException sqlEx){
            logger.debug("TemplateDao - GetAllTemplates - SQLException: "+sqlEx.getMessage());
        }catch(Exception ex){
            logger.debug("TemplateDao - GetAllTemplates - Exception: "+ex.getMessage());
        }finally{
            try{
                DatabaseConnectionManager.returnConnection(con);
                DatabaseConnectionManager.clearResources(ps, rs);
            }catch(DatabaseConnectionManagerException ex){
                logger.debug("TemplateDao - GetAllTemplates - DatabaseConnectionManagerException: "+ex.getMessage());
            }
        }        
        return templates; 
    }    
}
