/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.billingsystem.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author THE PR!NCE
 */
public class Customer {
    private int id;
    private String nid;
    private String name;
    private String phone;
    private String email;
    private String address;
    private Date billingDate;
    private RatePlan ratePlan;
    private List<Service> addOnServices=new ArrayList<>();

    public Customer() {
        this.ratePlan = new RatePlan();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(Date billingDate) {
        this.billingDate = billingDate;
    }

    public RatePlan getRatePlan() {
        return ratePlan;
    }

    public void setRatePlan(RatePlan ratePlan) {
        this.ratePlan = ratePlan;
    }

    public List<Service> getAddOnServices() {
        return addOnServices;
    }

    public void setAddOnServices(List<Service> addOnServices) {
        this.addOnServices = addOnServices;
    }
    
}
