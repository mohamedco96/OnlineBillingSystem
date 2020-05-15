/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.billingsystem.servlets;

import com.billingsystem.daos.TariffZoneDAO;
import com.billingsystem.entities.TariffZone;
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
@WebServlet(value = "/deleteTarrifZone")
public class TariffZoneDeleteServlet extends HttpServlet {

   TariffZoneDAO tzd = new TariffZoneDAO();
    TariffZone tz = new TariffZone();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            tz.setId(Integer.parseInt(req.getParameter("tarrifZoneId")));
            tzd.deleteTarrifZone(tz);
            resp.getWriter().print("success");
        } catch (Exception e) {
            e.getMessage();
        }

    }

}