/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.billingsystem.entities;

import java.util.Vector;
import java.util.logging.Logger;
import java.util.Date;

/**
 *
 * @author omega
 */
public class DetailedBill {
    Double tax ;
    Double total ;
    int id ;
    Vector<BillServices> vec = new Vector<>(); 
    Customer customer = new Customer();
    Vector <RecService> vec2 = new Vector<>();

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    

    public Vector<RecService> getVec2() {
        return vec2;
    }

    public void setVec2(Vector<RecService> vec2) {
        this.vec2 = vec2;
    }

   public class RecService {
   Service svc ;
   float cost ;
   Date billdate ;
   boolean paid ;
    
    
        public void setSvc(Service svc) {
            this.svc = svc;
        }

        public void setCost(float cost) {
            this.cost = cost;
        }

        public void setBilldate(Date billdate) {
            this.billdate = billdate;
        }

        public void setPaid(boolean paid) {
            this.paid = paid;
        }

        public Service getSvc() {
            return svc;
        }

        public float getCost() {
            return cost;
        }

        public Date getBilldate() {
            return billdate;
        }

        public boolean isPaid() {
            return paid;
        }
  
    
    }
   

    
    public Double getTax() {
       return tax ;
       
    }

   
    public Double getTotal() {
       
        return total;
    }

    public Vector<BillServices> getVec() {
        return vec;
    }

    public Customer getCustomer() {
        return customer;
    }

    
    public void setTax(Double tax) {
        this.tax = tax;
    }

   
    public void setTotal(Double toatl) {
        this.total = toatl;
    }

    public void setVec(Vector<BillServices> vec) {
        this.vec = vec;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    
 public  class BillServices{
   ServicePackage svc_pkg ;
   Service svc ;
   float rate ;
   float volume_spent; 
   float cost ;
   float used ;
   float free_units ;
   Date billdate ;
   RatePlan ratePlan;
   boolean paid ;

        public void setPaid(boolean paid) {
            this.paid = paid;
        }

        public boolean isPaid() {
            return paid;
        }
        public void setRatePlan(RatePlan ratePlan) {
            this.ratePlan = ratePlan;
        }

        public RatePlan getRatePlan() {
            return ratePlan;
        }
        public Date getBilldate() {
            return billdate;
        }

        public void setBilldate(Date billdate) {
            this.billdate = billdate;
        }
   
        public ServicePackage getSvc_pkg() {
            return svc_pkg;
        }

        public Service getSvc() {
            return svc;
        }

        public float getRate() {
            return rate;
        }

        public float getVolume_spent() {
            return volume_spent;
        }

        public float getCost() {
            return cost;
        }

        public float getUsed() {
            return used;
        }

        public float getFree_units() {
            return free_units;
        }

        public void setSvc_pkg(ServicePackage svc_pkg) {
            this.svc_pkg = svc_pkg;
        }

        public void setSvc(Service svc) {
            this.svc = svc;
        }

        public void setRate(float rate) {
            this.rate = rate;
        }

        public void setVolume_spent(float volume_spent) {
            this.volume_spent = volume_spent;
        }

        public void setCost(float cost) {
            this.cost = cost;
        }

        public void setUsed(float used) {
            this.used = used;
        }

        public void setFree_units(float free_units) {
            this.free_units = free_units;
        }
        
   } 
  
  
}
