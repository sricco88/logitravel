/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobreporting.servicesBusiness.integration.dataAccess.dao;
import com.jobreporting.classes.Customer;
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
public class CustomerDao{
    public static LoggerManager logger = GenericUtility.getLogger(CustomerDao.class.getName());
    
    public int GetID(String name, String surname){
        logger.debug("CustomerDao - GetID - Welcome");
        logger.debug("CustomerDao - GetID - name: "+name);
        logger.debug("CustomerDao - GetID - surname: "+surname);
        int idCustomer = -1;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = DatabaseConnectionManager.getConnection();
            String sql = "SELECT id FROM customers WHERE name=? AND surname=?";
            ps = con.prepareStatement(sql, 1);
            ps.setString(1, name);
            ps.setString(2, surname);
            logger.debug("CustomerDao - GetID - query: "+sql);
            rs = ps.executeQuery();
            if(rs.next()){
                idCustomer = rs.getInt("id");
            }            
        }catch(SQLException sqlEx){
            logger.debug("ParteDao - BuscarIDWorker - SQLException: "+sqlEx.getMessage());
        }catch(Exception ex){
            logger.debug("ParteDao - BuscarIDWorker - Exception: "+ex.getMessage());
        }finally{
            try{
                DatabaseConnectionManager.returnConnection(con);
                DatabaseConnectionManager.clearResources(ps, rs);
            }catch(DatabaseConnectionManagerException ex){
                logger.debug("CustomerDao - GetID - DatabaseConnectionManagerException: "+ex.getMessage());
            }
        }        
        return idCustomer;        
    }
    
    public boolean HasCustomerAnyBook(int idCustomer){
        logger.debug("CustomerDao - HasCustomerAnyBook - Welcome");
        logger.debug("CustomerDao - HasCustomerAnyBook - idCustomer: "+idCustomer);
        boolean hasAnyBook = false;        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = DatabaseConnectionManager.getConnection();
            String sql = "SELECT id FROM book WHERE idcustomer=?";
            ps = con.prepareStatement(sql, 1);
            ps.setInt(1, idCustomer);
            logger.debug("CustomerDao - HasCustomerAnyBook - query: "+sql);
            rs = ps.executeQuery();
            if(rs.next()){
                hasAnyBook = true;
            }
        }catch(SQLException sqlEx){
            logger.debug("CustomerDao - HasCustomerAnyBook - SQLException: "+sqlEx.getMessage());
        }catch(Exception ex){
            logger.debug("CustomerDao - HasCustomerAnyBook - Exception: "+ex.getMessage());
        }finally{
            try{
                DatabaseConnectionManager.returnConnection(con);
                DatabaseConnectionManager.clearResources(ps, rs);
            }catch(DatabaseConnectionManagerException ex){
                logger.debug("CustomerDao - HasCustomerAnyBook - DatabaseConnectionManagerException: "+ex.getMessage());
            }
        }        
        return hasAnyBook;
        
    }
    
    public ArrayList<Customer> List(){
        logger.debug("CustomerDao - List - Welcome");
        ArrayList<Customer> customers = new ArrayList<>(); 
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = DatabaseConnectionManager.getConnection();
            String sql = "SELECT * FROM customers";
            ps = con.prepareStatement(sql, 1);
            logger.debug("CustomerDao - List - query: "+sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setCode(rs.getString("code"));
                customer.setName(rs.getString("name"));
                customer.setSurname(rs.getString("surname"));
                customer.setDni(rs.getString("dni"));
                customer.setGender(rs.getString("gender"));
                customer.setAge(rs.getInt("age"));
                customer.setPhoneNumber(rs.getString("phonenumber"));
                customer.setEmail(rs.getString("phonenumber"));
                customer.setAddress(rs.getString("address"));
                customers.add(customer);
            }             
        }catch(SQLException sqlEx){
            logger.debug("CustomerDao - List - SQLException: "+sqlEx.getMessage());
        }catch(Exception ex){
            logger.debug("CustomerDao - List - Exception: "+ex.getMessage());
        }finally{
            try{
                DatabaseConnectionManager.returnConnection(con);
                DatabaseConnectionManager.clearResources(ps, rs);
            }catch(DatabaseConnectionManagerException ex){
                logger.debug("CustomerDao - List - DatabaseConnectionManagerException: "+ex.getMessage());
            }
        }        
        return customers;
    }
    
    
    
}