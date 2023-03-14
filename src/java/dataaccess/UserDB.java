/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.ArrayList;
import models.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import javax.persistence.EntityManager;
/**
 *
 * @author mdkul
 */
//this has to be able to do everything with uses 
public class UserDB {
    public void UserDB(){
    ArrayList<User> users = new ArrayList<>();
    }
    
    public List<User> getall(){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try{
            List<User> users = em.createNamedQuery("User.findAll", User.class).getResultList();
            return users;
        }
        catch(Exception e){
            
        }
        finally {
           em.close();
        }
        return null;
    }
    
    public void add(User user)throws Exception{
        
    }
    
    public User getUser(String email){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try{
           User user = em.find(User.class, email);
           return user;  
        }
        catch(Exception e){
            
        }
        finally {
            em.close();
        }
     return null;
    }
    
    
    public void updateUser(String email, String fname, String lname, String password, int role){
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        
        String sql = "Update user set first_name = ?, last_name = ?, role = ?, password = ? where email = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, role+"");
            ps.setString(4, password);
            ps.setString(5, email);
            ps.executeUpdate();
        } 
        catch(Exception e){
            
        }
            finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }

    
    public void updateUser(String email, String fname, String lname, int role){
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        
        String sql = "Update user set first_name = ?, last_name = ?, role = ? where email = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, role+"");
            ps.setString(4, email);
            ps.executeUpdate();
        } 
        catch(Exception e){
            
        }
            finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
    
    public void delete(String email){
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        
         String sql = "delete from user where email = ?";
         
         try {
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.executeUpdate();
        } 
        catch(Exception e){
            
        }
            finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
    
    public void addUser(String email,  String fname, String lname, String password, int id){
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        
         String sql = "insert into user(email, first_name, last_name, password, role) values(?,?,?,?,?)";
         
         try {
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, fname);
            ps.setString(3, lname);
            ps.setString(4, password);
            ps.setInt(5, id);
            ps.executeUpdate();
        } 
        catch(Exception e){
            
        }
            finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
}
