/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.billingsystem.entities;

/**
 *
 * @author THE PR!NCE
 */
public class RatePlan {

    private int id;
    private String name;
    private float monthlyFees;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMonthlyFees() {
        return monthlyFees;
    }

    public void setMonthlyFees(float monthlyFees) {
        this.monthlyFees = monthlyFees;
    }
    
}
