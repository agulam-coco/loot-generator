/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt 
 * to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.grinnell.csc207.lootgenerator;

/**
 * Represents a treasure item with a base item, base stat, and optional prefix and suffix.
 * 
 * @author jason
 */
public class Treasure {

    private String baseItem;
    private int baseStat;
    private Affix prefix;
    private Affix suffix;

    /**
     * Constructs a new Treasure object.
     *
     * @param baseItem the name of the base item
     * @param baseStat the base stat value
     * @param prefix   the prefix affix (can be null)
     * @param suffix   the suffix affix (can be null)
     */
    public Treasure(String baseItem, int baseStat, Affix prefix, Affix suffix) {
        this.baseItem = baseItem;
        this.baseStat = baseStat;
        this.prefix = prefix;
        this.suffix = suffix;
    }

    /**
     * Gets the base item name.
     *
     * @return the base item name
     */
    public String getBaseItem() {
        return baseItem;
    }

    /**
     * Gets the base stat value.
     *
     * @return the base stat
     */
    public int getBaseStat() {
        return baseStat;
    }

    /**
     * Gets the prefix affix.
     *
     * @return the prefix, or null if none
     */
    public Affix getPrefix() {
        return prefix;
    }

    /**
     * Gets the suffix affix.
     *
     * @return the suffix, or null if none
     */
    public Affix getSuffix() {
        return suffix;
    }

    /**
     * Returns a string representation of this treasure.
     *
     * @return the string describing the treasure
     */
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
