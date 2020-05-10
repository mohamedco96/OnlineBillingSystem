/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.billingsystem.servlets;

import com.billingsystem.daos.ServiceDao;
import com.billingsystem.daos.tarrifZoneDao;
import com.billingsystem.entities.Service;
import com.billingsystem.entities.tarrifZone;
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
public class addTarrifZone extends HttpServlet {

    tarrifZoneDao tzd = new tarrifZoneDao();
    tarrifZone tz = new tarrifZone();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        tz.setName(req.getParameter("tarrifZoneName"));
        tz.setSame_net(Boolean.parseBoolean(req.getParameter("sameNetwork")));
        tz.setLocal(Boolean.parseBoolean(req.getParameter("Local")));
        tz.setRoaming(Boolean.parseBoolean(req.getParameter("Romaing")));
        tzd.save(tz);

    }

}
