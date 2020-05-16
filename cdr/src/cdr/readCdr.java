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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
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
            Path myObj = Paths.get("./src/new");
            myObj.register(watcher, ENTRY_CREATE);

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
                    Scanner myReader = new Scanner(Paths.get("./src/new/" + fileName));
                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                        String[] arrOfStr = data.split(",", 7);
                        Cdr c = new Cdr();
                        data d = new data();

                        System.out.println(data);

                        System.out.println(arrOfStr.length);

                        //(String a : arrOfStr) 
                        c.setOrigin(arrOfStr[0]);
                        c.setDial_b(arrOfStr[1]);
                        c.setService_id(Integer.parseInt(arrOfStr[2]));
                        c.setDuration(Integer.parseInt(arrOfStr[3]));

                        String isoDate = arrOfStr[4].substring(0, 4) + "-" + arrOfStr[4].substring(4, 6) + "-" + arrOfStr[4].substring(6, 8);
                        LocalDate date = LocalDate.parse(isoDate, DateTimeFormatter.ISO_DATE);
                        
                        System.out.println(date);
                        c.setStart_date(date);
                        System.out.println(c.getStart_date());
                        c.setStart_time(arrOfStr[5]);
                        c.setExternal_charges(Integer.parseInt(arrOfStr[6]));

                        d.save(c);

                    }
                    myReader.close();
                    try {
                        Path temp = Files.move(Paths.get("./src/new/" + fileName),
                                Paths.get("./src/old/" + fileName));
                    } catch (Exception ex) {
                        System.err.println(ex);
                    }

                    if (kind == ENTRY_MODIFY
                            && fileName.toString().equals("DirectoryWatchDemo.java")) {
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
