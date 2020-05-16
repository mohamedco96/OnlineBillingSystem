/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.billingsystem.entities;

import java.time.LocalDateTime;

/**
 *
 * @author omega
 */
public class Bills {
    Float total ; 
   
    int id ;
    Boolean paid ;
    LocalDateTime bill_date;

    public void setTotal(Float total) {
        this.total = total;
    }

   

    public void setId(int id) {
        this.id = id;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public void setBill_date(LocalDateTime bill_date) {
        this.bill_date = bill_date;
    }
    

    public Float getTotal() {
        return total;
    }

   
    public int getId() {
        return id;
    }

    public Boolean getPaid() {
        return paid;
    }

    public LocalDateTime getBill_date() {
        return bill_date;
    }
    
    
    
    
    
}
