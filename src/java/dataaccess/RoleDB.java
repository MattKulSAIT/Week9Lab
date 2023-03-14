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
import models.Role;
import models.User;


//Not sure what all has to be done with role but ya
//User a get all and make it an array list and get a dropdown list
public class RoleDB {

    public RoleDB() {
    }
    
    public Role getRole(int id){
        Role role;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "Select* From role where role_id=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, (id+""));
            rs = ps.executeQuery();
            while (rs.next()) {
                int roleId = rs.getInt(1);
                String roleName = rs.getString(2);
                role= new Role(roleId, roleName);
                return role;
            }
        }
        catch(Exception e){    
        }
        finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        return null;
        
    }
    
    
    
     public List<Role> getall(){
        List<Role> roles = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM role";
        
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
                while(rs.next()){
                    int roleId = rs.getInt(1);
                String roleName = rs.getString(2);
                Role role= new Role(roleId, roleName);
                    roles.add(role);
                }
        }
        catch(Exception e){
            
        }
        finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        return roles;
    }
}
