/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.billingsystem.servlets;

import com.billingsystem.daos.tarrifZoneDao;
import com.billingsystem.entities.tarrifZone;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author moham
 */
public class deleteTarrifZone extends HttpServlet {

   tarrifZoneDao tzd = new tarrifZoneDao();
    tarrifZone tz = new tarrifZone();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            tz.setId(Integer.parseInt(req.getParameter("tarrifZoneId")));
            tzd.deleteTarrifZone(tz);
            resp.setContentType("success");
        } catch (Exception e) {
            e.getMessage();
        }

    }

}