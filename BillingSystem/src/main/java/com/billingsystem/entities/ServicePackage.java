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
    private RatePlan RatePlan;
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

    public RatePlan getRatePlan() {
        return RatePlan;
    }

    public void setRatePlan(RatePlan RatePlan) {
        this.RatePlan = RatePlan;
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