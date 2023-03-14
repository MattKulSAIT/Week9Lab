/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author mdkul
 */
public class Role {
   public int id;
   public String name;

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Role(int id){
        this.id = id;
        
    }
   
   public String getName(){
       return name;
   }
   
   public int getId(){
       return id;
   }
}
