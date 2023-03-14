
package models;

import java.io.Serializable;

public class User implements Serializable {
    public String email;
    public String firstName;
    public String lastName;
    public String password;
    public Role role;
    
    //Im making role and int for now just so I can use the stuff
    public User(String email, String firstName, String lastName, String password, Role role){
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this. password = password;
        this.role = role;
    }
    
    public String getEmail(){
        return email;
    }
    
     public String getfName(){
        return firstName;
    }
     
     public String getlName(){
        return lastName;
    }
     
     public Role getRole(){
        return role;
    }
}
