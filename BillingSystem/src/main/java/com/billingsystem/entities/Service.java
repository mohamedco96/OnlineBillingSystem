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
public class Service {
    private int id;
    private String name;
    private boolean rated;
    private String type;

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

    public boolean isRated() {
        return rated;
    }

    public void setRated(boolean rated) {
        this.rated = rated;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

  
    
}
