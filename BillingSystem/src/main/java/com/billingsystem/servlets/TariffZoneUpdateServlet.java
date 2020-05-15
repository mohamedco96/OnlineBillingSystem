/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.billingsystem.servlets;

import com.billingsystem.daos.ServiceDAO;
import com.billingsystem.daos.TariffZoneDAO;
import com.billingsystem.entities.Service;
import com.billingsystem.entities.TariffZone;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author moham
 */
@WebServlet(value = "/addTarrifZone")
public class TariffZoneUpdateServlet extends HttpServlet {

    TariffZoneDAO tzd = new TariffZoneDAO();
    TariffZone tz = new TariffZone();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        tz.setId(Integer.parseInt(req.getParameter("tarrifZoneId")));
        tz.setName(req.getParameter("tarrifZoneName"));
        tz.setSame_net(Boolean.parseBoolean(req.getParameter("sameNetwork")));
        tz.setLocal(Boolean.parseBoolean(req.getParameter("Local")));
        tz.setRoaming(Boolean.parseBoolean(req.getParameter("Romaing")));
//        tzd.saveAndReturnId(tz);
        
        int newId = 0;
        boolean updateSuccess = false;
        
        if(tz.getId() == 0)
            newId = tzd.saveAndReturnId(tz);
        else{
            updateSuccess = tzd.update(tz);}
        
        resp.setContentType("text/plain");
        if(newId != 0)
            resp.getWriter().print(Integer.toString(newId));
        else if(updateSuccess)
            resp.getWriter().print(Integer.toString(tz.getId()));
//        else
//            resp.getWriter().print("failed");
    


    }

}
