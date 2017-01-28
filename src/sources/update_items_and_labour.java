package sources;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TimerTask;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sijitend
 */
public class update_items_and_labour  extends TimerTask {


   public static HashMap item_labour = new HashMap(1000);
   BufferedReader br = null;
   String sCurrentLine;
    // run is a abstract method that defines task performed at scheduled time.
    public void run() {
        Thread t = new Thread(new Runnable() {
            public void run() {
                System.out.println("update_items_and_labour Thread start----");
                   // while (true) 
                    {
                        System.out.println("ITEMS : =========================>");
                    try 
                    {
                        br = new BufferedReader(new FileReader("C:\\Users\\sijitend\\Downloads\\javaex_code\\GUIFormExamples\\src\\items"));
                        while ((sCurrentLine = br.readLine()) != null) 
                        {
                            if(!sCurrentLine.matches(".*----.*")){
                            System.out.println(sCurrentLine);
                            item_labour.put(sCurrentLine.split(",")[1], sCurrentLine.split(",")[0]);
                                System.out.println(item_labour.keySet());
                            }
                        }
                    } 
                    catch (Exception ex) 
                    {
                        Logger.getLogger(update_items_and_labour.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    }
            }

        });
        t.start();

    }
public void ff()
{    
    BufferedReader bufferedReader = null;
        try {
            FileReader fileReader = new FileReader("C:\\Users\\sijitend\\Downloads\\EasySwingUIs-SRC\\EasySwingUIs\\JavaApplication17\\src\\javaapplication17\\items");
            bufferedReader = new BufferedReader(fileReader);
        } catch (FileNotFoundException f) {
            System.out.println(f.getMessage());
        }
        String line = null;
        try {
            while ((line = bufferedReader.readLine()) != null) 
            {
                item_labour.put(line.split(",")[1], line.split(",")[0]);
       //         lines.add(line.split(",")[1]);
            }
            bufferedReader.close();
         //   return lines.toArray(new String[lines.size()]);
        } catch (IOException ie) {
            System.out.println(ie.getMessage());
           // return (new String[0]);
        }
    }
}