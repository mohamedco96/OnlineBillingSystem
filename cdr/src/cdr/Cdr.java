/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdr;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author asalah
 */
public class Cdr {

    private String origin;
    private String dest;
    private int service_id;
    private int volume;
    private LocalDate date;
    private String time;
    private int external_fees;
    
    public LocalDate getStart_date() {
        
        return date;
    }

    public void setStart_date(LocalDate date) {
        
     
        this.date=date;
    }

    public String getStart_time() {
        return time;
    }

    public void setStart_time(String time) {
        this.time = time;
    }
    

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDial_b() {
        return dest;
    }

    public void setDial_b(String dest) {
        this.dest = dest;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public int getDuration() {
        return volume;
    }

    public void setDuration(int volume) {
        this.volume = volume;
    }



    public int getExternal_charges() {
        return external_fees;
    }

    public void setExternal_charges(int external_fees) {
        this.external_fees = external_fees;
    }

   

}
