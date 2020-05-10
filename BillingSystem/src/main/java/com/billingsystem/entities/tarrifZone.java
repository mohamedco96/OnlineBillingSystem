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
public class tarrifZone {

    private int id;
    private String name;
    private boolean same_net;
    private boolean local;
    private boolean roaming;

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

    public boolean isSame_net() {
        return same_net;
    }

    public void setSame_net(boolean same_net) {
        this.same_net = same_net;
    }

    public boolean isLocal() {
        return local;
    }

    public void setLocal(boolean local) {
        this.local = local;
    }

    public boolean isRoaming() {
        return roaming;
    }

    public void setRoaming(boolean roaming) {
        this.roaming = roaming;
    }

}
