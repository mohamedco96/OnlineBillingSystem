/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdr;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
 
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;


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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
   try {
            WatchService watcher = FileSystems.getDefault().newWatchService();
            Path myObj = Paths.get("/home/asalah/Desktop/Billi/new");
            myObj.register(watcher, ENTRY_CREATE, ENTRY_MODIFY);
             
            System.out.println("Watch Service registered for dir: " + myObj.getFileName());
                 
             
            while (true) {
                WatchKey key;
                try {
                    key = watcher.take();
                } catch (InterruptedException ex) {
                    return;
                }
                 
                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                     
                    @SuppressWarnings("unchecked")
                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
                    Path fileName = ev.context();
                     
                    System.out.println(kind.name() + ": " + fileName);
                    Scanner myReader = new Scanner(Paths.get("/home/asalah/Desktop/Billi/new/"+fileName));
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] arrOfStr = data.split(",",7);
                Cdr c = new Cdr();
                data d = new data();
                
                System.out.print(data);

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
        (Paths.get("/home/asalah/Desktop/Billi/new/"+fileName),  
        Paths.get("/home/asalah/Desktop/Billi/old/"+fileName)); 
            
            
            
            
                    
                     
                    if (kind == ENTRY_MODIFY &&
                            fileName.toString().equals("DirectoryWatchDemo.java")) {
                        System.out.println("My source file has changed!!!");
                    }
                }
                 
                boolean valid = key.reset();
                if (!valid) {
                    break;
                }
            }
             
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}