/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.grinnell.csc207.lootgenerator;

/**
 *
 * @author jason
 */
public class Treasure {

    private String baseItem;
    private int baseStat;
    private Affix prefix;
    private Affix suffix;

    public Treasure(String baseItem, int baseStat, Affix prefix, Affix suffix) {
        this.baseItem = baseItem;
        this.baseStat = baseStat;
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public String getBaseItem() {
        return baseItem;
    }

    public int getBaseStat() {
        return baseStat;
    }

    public Affix getPrefix() {
        return prefix;
    }

    public Affix getSuffix() {
        return suffix;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (prefix != null) {
            sb.append(prefix.getName());
            sb.append(" ");
        }

        sb.append(baseItem);

        if (suffix != null) {
            sb.append(" ");
            sb.append(suffix.getName());
        }

        sb.append("\n");

        sb.append("Defense: ");
        sb.append(baseStat);
        sb.append("\n");

        if (prefix != null) {
            sb.append(prefix.toString());

        }
        if (suffix != null) {
            sb.append("\n");
            sb.append(suffix.toString());
        }
        return sb.toString();
    }
}
