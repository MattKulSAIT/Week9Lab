/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dataaccess.RoleDB;
import dataaccess.UserDB;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Role;
import models.User;

/**
 *
 * @author mdkul
 */
public class UserServlet extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDB DBconnection = new UserDB();
        RoleDB roleConnection = new RoleDB();
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Role> roles = new ArrayList<>();
        users = (ArrayList<User>)DBconnection.getall();
        roles = (ArrayList<Role>)roleConnection.getall();
        int size = users.size();
        if (size == 0){
            request.setAttribute("people", "no");
        }
        request.setAttribute("users", users);
        request.setAttribute("roles", roles);
        String theUserEmail;
        String stuff = request.getParameter("userEmail");
        
        String whatToDO = request.getParameter("action");
        if(whatToDO == null || whatToDO.equals("Cancel") || whatToDO.equals("editUser")){
            theUserEmail="";
        }
        if(whatToDO ==  null || whatToDO.equals("Cancel")){
            String bottom = "Add User";
            request.setAttribute("subTitle", bottom);
        }
        else if(whatToDO.equals("editUser")){
            String bottom = "Edit User";
            request.setAttribute("subTitle", bottom);
            theUserEmail = request.getParameter("userEmail");
            theUserEmail = theUserEmail.replace(" ", "+");
            User theUser = DBconnection.getUser(theUserEmail);
            request.setAttribute("userToEdit", theUser);
        }
        else if(whatToDO.equals("Update")){
            String email = stuff.replace(" ", "+");
            String fname = (String)request.getParameter("firstName");
            String lname = (String)request.getParameter("latName");
            String pass = (String)request.getParameter("password");
            String roleId = request.getParameter("therole"); 
            int roleIdNum = Integer.parseInt(roleId);
            if(pass.equals("")){
                DBconnection.updateUser(email, fname, lname, roleIdNum);
            }
            else{
                DBconnection.updateUser(email, fname, lname, pass, roleIdNum);
            }
            ArrayList<User> updateUsers = (ArrayList<User>)DBconnection.getall();
            request.setAttribute("users", updateUsers);
            String bottom = "Add User";
            request.setAttribute("subTitle", bottom);
        }
        else if(whatToDO.equals("deleteUser")){
            String email = stuff.replace(" ", "+");
            DBconnection.delete(email);
            String bottom = "Add User";
            request.setAttribute("subTitle", bottom);
            ArrayList<User> updateUsers = (ArrayList<User>)DBconnection.getall();
            request.setAttribute("users", updateUsers);
            
        }
        if (size == 0){
            request.setAttribute("people", "no");
        }
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDB DBconnection = new UserDB();
        String email = (String)request.getParameter("email");
        String fname = (String)request.getParameter("firstName");
        String lname = (String)request.getParameter("latName");
        String pass = (String)request.getParameter("password");
        String roleId = request.getParameter("therole");
        
        if(email.equals("") || fname.equals("") || lname.equals("") || pass.equals("")){
            request.setAttribute("messUp", "mess");
        }
        else{
            int role = Integer.parseInt(roleId);
            DBconnection.addUser(email, fname, lname, pass, role);
        }
            ArrayList<User> updateUsers = (ArrayList<User>)DBconnection.getall();
            request.setAttribute("users", updateUsers);
            String bottom = "Add User";
            request.setAttribute("subTitle", bottom);
            RoleDB roleConnection = new RoleDB();
            ArrayList<Role> roles = new ArrayList<>();
            roles = (ArrayList<Role>)roleConnection.getall();
            request.setAttribute("roles", roles);
        
        
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }
}
