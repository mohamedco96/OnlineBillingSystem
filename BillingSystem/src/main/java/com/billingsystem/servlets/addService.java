/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.billingsystem.servlets;

import com.billingsystem.daos.ServiceDao;
import com.billingsystem.entities.Service;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author moham
 */
public class addService extends HttpServlet {
    
ServiceDao sd = new ServiceDao();
    Service s = new Service();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        s.setName(req.getParameter("service_name"));
        s.setRated(Boolean.parseBoolean(req.getParameter("rate")));
        s.setType(req.getParameter("type"));
        sd.save(s);

    }

}
