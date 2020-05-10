/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.billingsystem.entities;

/**
 *
 * @author moham
 */
public class ServicePackage {

    private int id;
    private int rate_plan_id;
    private Service service;
    private timePackage timePackage;
    private tarrifZone tarrifZone;
    private int free_units;
    private float rate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRate_plan_id() {
        return rate_plan_id;
    }

    public void setRate_plan_id(int rate_plan_id) {
        this.rate_plan_id = rate_plan_id;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public timePackage getTimePackage() {
        return timePackage;
    }

    public void setTimePackage(timePackage timePackage) {
        this.timePackage = timePackage;
    }

    public tarrifZone getTarrifZone() {
        return tarrifZone;
    }

    public void setTarrifZone(tarrifZone tarrifZone) {
        this.tarrifZone = tarrifZone;
    }

    public int getFree_units() {
        return free_units;
    }

    public void setFree_units(int free_units) {
        this.free_units = free_units;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

}
