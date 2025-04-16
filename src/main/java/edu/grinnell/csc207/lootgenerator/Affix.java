/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.grinnell.csc207.lootgenerator;

/**
 *
 * @author jason
 */
public class Affix {

    final String affixName;
    final String mod1Code;
    final int mod1Number;

    public Affix(String affixName, String mod1Code, int mod1Number) {
        this.affixName = affixName;
        this.mod1Code = mod1Code;
        this.mod1Number = mod1Number;
    }

    public String getName(){
        return affixName;
    }
    
    @Override
    public String toString() {
        return String.format("%d %s", mod1Number, mod1Code);
    }
}
