/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Database_Tables.Users;
import database.Database;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author moham
 */
public class addCustomer extends HttpServlet {

    Users user;
    Database db = new Database();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        user = new Users(req.getParameter("name"), req.getParameter("nid"), req.getParameter("dnum"),
                 req.getParameter("addr"), req.getParameter("email"), req.getParameter("profile"));
                      
        boolean addCustomer = db.addCustomer(user);
        resp.sendRedirect("./dashboard.jsp");
       
        
        
    }



}