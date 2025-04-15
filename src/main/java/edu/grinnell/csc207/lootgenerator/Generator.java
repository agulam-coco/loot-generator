/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.grinnell.csc207.lootgenerator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author jason
 */
public class Generator {
    //Backing list containing all arrays loaded into memory
    public List<Monster> monsterArr = new ArrayList<>();
    
    
     /**
     * Load all monsters into memory
     */
    public Generator(String DATA_SET){
        loadMonsters(DATA_SET + "/monstats.txt");
    }
    
    private void loadMonsters(String DATA_SET){
         try (BufferedReader reader = new BufferedReader(new FileReader(DATA_SET))) {
            String line;
            while ((line = reader.readLine()) != null) {
                /**
                Process each line
                column[0] = class
                column[1] = type
                column[2] = level
                column[3] = treasure class
                */
                
                String[] columns = line.split("\t");
                
                if(columns.length > 0 ){                
                String tempClass = columns[0];
                String tempType = columns[1];
                int tempLevel = Integer.parseInt(columns[2]);
                String tempTreasureClass = columns[3];
                 
                monsterArr.add(new Monster(tempClass, tempType,tempLevel,tempTreasureClass));  
                }
                
                  
            }
        } catch (IOException e) {
            // Handle file reading errors
            e.printStackTrace();
        }
    }
    
    /**
     * This class generates a new random monster using data set
     * @return Monster
     */
    public Monster generateMonster(){
        return (Monster) getRandom(monsterArr);
    }
    
    /**
     * This function random picks an item from an array and returns the item 
     * @param array the array to get random Item
     * @return The random chosen item
     */
    public static <T> T getRandom(List<T> array) {
    int rnd = new Random().nextInt(array.size());
    return array.get(rnd);
    
}
    
}
