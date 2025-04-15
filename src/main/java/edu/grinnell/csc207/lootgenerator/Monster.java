/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.grinnell.csc207.lootgenerator;

/**
 *
 * @author jason
 */
public class Monster {

    private String className;
    private String type;
    private int level;
    private String treasureClass;

    public Monster(String className, String type, int level, String treasureClass) {
        this.className = className;
        this.type = type;
        this.level = level;
        this.treasureClass = treasureClass;
    }

    public String getClassName() {
        return className;
    }

    public String getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public String getTreasureClass() {
        return treasureClass;
    }
}