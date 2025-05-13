/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt 
 * to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.grinnell.csc207.lootgenerator;

/**
 * Represents a monster with a class name, type, level, and treasure class.
 * Used to determine loot drops in the loot generation system.
 * 
 * @author jason
 */
public class Monster {

    private String className;
    private String type;
    private int level;
    private String treasureClass;

    /**
     * Constructs a new Monster.
     *
     * @param className the name of the monster class
     * @param type the type/category of the monster
     * @param level the level of the monster
     * @param treasureClass the name of the treasure class the monster can drop
     */
    public Monster(String className, String type, int level, String treasureClass) {
        this.className = className;
        this.type = type;
        this.level = level;
        this.treasureClass = treasureClass;
    }

    /**
     * Gets the class name of the monster.
     *
     * @return the class name
     */
    public String getClassName() {
        return className;
    }

    /**
     * Gets the type of the monster.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the level of the monster.
     *
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Gets the treasure class of the monster.
     *
     * @return the treasure class
     */
    public String getTreasureClass() {
        return treasureClass;
    }
}
