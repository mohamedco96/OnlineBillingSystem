/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.billingsystem.servlets;

import com.billingsystem.daos.ServiceDAO;
import com.billingsystem.entities.Service;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author moham
 */
@WebServlet(value = "/addService")

public class ServiceUpdateServlet extends HttpServlet {

    ServiceDAO sd = new ServiceDAO();
    Service s = new Service();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        s.setId(Integer.parseInt(req.getParameter("service_id")));
        s.setName(req.getParameter("service_name"));
        s.setRated(Boolean.parseBoolean(req.getParameter("rate")));
        s.setType(req.getParameter("type"));
        
        int newId = 0;
        boolean updateSuccess = false;
        
        if(s.getId() == 0)
            newId = sd.saveAndReturnId(s);
        else
            updateSuccess = sd.update(s);
        
        resp.setContentType("text/plain");
        if(newId != 0)
            resp.getWriter().write(Integer.toString(newId));
        else if(updateSuccess)
            resp.getWriter().write(Integer.toString(s.getId()));
        else
            resp.getWriter().write("failed");
    }

}
