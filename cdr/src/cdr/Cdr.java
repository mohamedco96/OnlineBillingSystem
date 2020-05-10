/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdr;

/**
 *
 * @author asalah
 */
public class Cdr {

    private String dial_a;
    private String dial_b;
    private int service_id;
    private int duration;
    private String start_date;
    private String start_time;
    private int external_charges;
    
    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }
    

    public String getDial_a() {
        return dial_a;
    }

    public void setDial_a(String dial_a) {
        this.dial_a = dial_a;
    }

    public String getDial_b() {
        return dial_b;
    }

    public void setDial_b(String dial_b) {
        this.dial_b = dial_b;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }



    public int getExternal_charges() {
        return external_charges;
    }

    public void setExternal_charges(int external_charges) {
        this.external_charges = external_charges;
    }

}
