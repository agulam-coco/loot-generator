/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt 
 * to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.grinnell.csc207.lootgenerator;

/**
 * Represents a prefix or suffix modifier applied to a treasure item.
 * It includes the affix name, a modifier code, and its numeric value.
 * 
 * @author jason
 */
public class Affix {

    final String affixName;
    final String mod1Code;
    final int mod1Number;

    /**
     * Constructs a new Affix object.
     *
     * @param affixName   the name of the affix
     * @param mod1Code    the modifier code
     * @param mod1Number  the modifier's numeric value
     */
    public Affix(String affixName, String mod1Code, int mod1Number) {
        this.affixName = affixName;
        this.mod1Code = mod1Code;
        this.mod1Number = mod1Number;
    }

    /**
     * Returns the name of the affix.
     *
     * @return the affix name
     */
    public String getName() {
        return affixName;
    }

    /**
     * Returns the modifier description as a string.
     *
     * @return a formatted string of the affix modifier
     */
    @Override
    public String toString() {
        return String.format("%d %s", mod1Number, mod1Code);
    }
}
