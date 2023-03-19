/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.ArrayList;
import models.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
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
    
    //These might also cause a problem
    public void updateUser(String email, String fname, String lname, String password, int role){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans= em.getTransaction();
        User user = new User(email,fname,lname,password);
         try {
           trans.begin();
           Query q = em.createNamedQuery("Role.findByRoleId");
            q.setParameter("roleId", role);
            user.setRole((Role)q.getSingleResult());
           em.merge(user);
           trans.commit();
        } 
        catch(Exception e){
            trans.rollback();
        }
            finally {
            em.close();
        }
    }

    //These might also cause a problem
    public void updateUser(String email, String fname, String lname, int role){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        User thecurrent = em.find(User.class, email); 
        String password = thecurrent.getPassword();
        User user = new User(email,fname,lname,password);
         try {
           trans.begin();
           Query q = em.createNamedQuery("Role.findByRoleId");
            q.setParameter("roleId", role);
            user.setRole((Role)q.getSingleResult());
           em.merge(user);
           trans.commit();
        } 
        catch(Exception e){
            trans.rollback();
        }
            finally {
            em.close();
        }
    }
    
    public void delete(String email){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans= em.getTransaction();
       
         try {
           User user = em.find(User.class, email); 
           trans.begin();
           em.remove(user);
           trans.commit();
        } 
        catch(Exception e){
            trans.rollback();
        }
            finally {
            em.close();
        }
    }
    
    
    public void addUser(String email, String fname, String lname, String password, int id) {
    EntityManager em = DBUtil.getEmFactory().createEntityManager();
    EntityTransaction trans = null;
    try {
        trans = em.getTransaction();
        trans.begin();
        User user = new User(email,fname,lname,password);
        Query q = em.createNamedQuery("Role.findByRoleId");
        q.setParameter("roleId", id);
        user.setRole((Role)q.getSingleResult());
        em.persist(user);
        trans.commit();
    } catch(Exception e){
        if (trans != null && trans.isActive()) {
            trans.rollback();
        }
    } finally {
        em.close();
    }
}
}
