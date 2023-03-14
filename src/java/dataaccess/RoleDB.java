/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import models.Role;
import models.User;


//Not sure what all has to be done with role but ya
//User a get all and make it an array list and get a dropdown list
public class RoleDB {

    public RoleDB() {
    }
    
    public Role getRole(int id){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try{
           Role role = em.find(Role.class, id);
           return role;  
        }
        catch(Exception e){
            
        }
        finally {
            em.close();
        }
     return null;   
    }
    
    
    
     public List<Role> getall(){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try{
            List<Role> roles = em.createNamedQuery("Role.findAll", Role.class).getResultList();
            return roles;
        }
        catch(Exception e){
            
        }
        finally {
           em.close();
        }
        return null;
    }
}
