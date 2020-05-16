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
    private int RatePlanId;
    private Service service;
    private TimePackage timePackage;
    private TariffZone tarrifZone;
    private int free_units;
    private float rate;
    
    public ServicePackage(){
        this.service=new Service();
        this.timePackage=new TimePackage();
        this.tarrifZone=new TariffZone();
    }
    public int getRatePlanId() {
        return RatePlanId;
    }

    public void setRatePlanId(int RatePlanId) {
        this.RatePlanId = RatePlanId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public TimePackage getTimePackage() {
        return timePackage;
    }

    public void setTimePackage(TimePackage timePackage) {
        this.timePackage = timePackage;
    }

    public TariffZone getTarrifZone() {
        return tarrifZone;
    }

    public void setTarrifZone(TariffZone tarrifZone) {
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
