/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.billingsystem.daos;


import java.util.ArrayList;

/**
 *
 * @author THE PR!NCE
 */
public interface DAO<T> {
     
    T get(int id);
     
    ArrayList<T> getAll();
     
    boolean save(T t);
    
    int saveAndReturnId(T t);
     
    boolean update(T t);
     
    boolean delete(int id);
}






