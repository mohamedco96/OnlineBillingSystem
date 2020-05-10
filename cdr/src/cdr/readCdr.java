/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdr;

import java.io.File;  
import java.io.FileNotFoundException;  
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Scanner; 

/**
 *
 * @author asalah
 */

public class readCdr {

    public static void main(String[] args) throws ParseException, IOException  {
        try {
            File myObj = new File("/home/asalah/Desktop/Billi/new/test01.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] arrOfStr = data.split(",", 7);
                Cdr c = new Cdr();
                data d = new data();

                System.out.print(arrOfStr.length);
                
                for (String a : arrOfStr) {
                    c.setDial_a(arrOfStr[0]);
                    c.setDial_b(arrOfStr[1]);
                    c.setService_id(Integer.parseInt(arrOfStr[2]));
                    c.setDuration(Integer.parseInt(arrOfStr[3]));
                    c.setStart_date(arrOfStr[4]);
                    c.setStart_time(arrOfStr[5]);
                    c.setExternal_charges(Integer.parseInt(arrOfStr[6]));
                    
                }
                d.save(c);
                

            }
            myReader.close();
            
            Path temp = Files.move 
        (Paths.get("/home/asalah/Desktop/Billi/new/test01.txt"),  
        Paths.get("/home/asalah/Desktop/Billi/old/test11.txt")); 
            
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
