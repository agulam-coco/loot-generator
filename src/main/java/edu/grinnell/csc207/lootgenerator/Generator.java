/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.grinnell.csc207.lootgenerator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author jason
 */
public class Generator {

    //Backing data types containing various fields loaded into memory
    private List<Monster> monsterArr = new ArrayList<>();

    private Map<String, String[]> treasureLines = new HashMap<>();

    private Map<String, String[]> treasureStatsLines = new HashMap<>();

    private Map<String, String[]> magicPrefixLines = new HashMap<>();

    private Map<String, String[]> magicSuffixLines = new HashMap<>();

    final String DEFAULT_DELIM = "\t";

    //Load data into memory
    public Generator(String DATA_SET) {
        loadMonsters(DATA_SET + "/monstats.txt");
        loadLines(DATA_SET + "/TreasureClassEx.txt", treasureLines);
        loadLines(DATA_SET + "/armor.txt", treasureStatsLines);
        loadLines(DATA_SET + "/MagicPrefix.txt", magicPrefixLines);
        loadLines(DATA_SET + "/MagicSuffix.txt", magicSuffixLines);
    }

    /**
     * Loads all monsters from data set into program memory
     *
     * @param filePath the path to the data set
     */
    //Credit:https://www.digitalocean.com/community/tutorials/java-read-file-line-by-line
    private void loadMonsters(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {

                // separated by tabs 
                String[] columns = line.split(DEFAULT_DELIM);

                if (columns.length > 0) {
                    String tempClass = columns[0];
                    String tempType = columns[1];
                    int tempLevel = Integer.parseInt(columns[2]);
                    String tempTreasureClass = columns[3];

                    monsterArr.add(new Monster(tempClass, tempType, tempLevel, tempTreasureClass));
                }

            }
        } catch (IOException e) {
            // Handle file reading errors
            e.printStackTrace();
        }
    }

    /**
     * Helper function which loads lines of data into memory as a map storing
     * the first value as the key
     *
     * @param filePath the path to the data set
     * @param linesMap the path to the map
     */
    private void loadLines(String filePath, Map linesMap) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(DEFAULT_DELIM);

                //store values as key values in the map
                if (columns.length > 0) {
                    String key = columns[0];
                    String[] value = Arrays.copyOfRange(columns, 1, columns.length);
                    linesMap.put(key, value);
                }

            }
        } catch (IOException e) {
            // Handle file reading errors
            e.printStackTrace();
        }

    }

    /**
     * This method generates a new random monster using data set
     *
     * @return Monster the new monster
     */
    public Monster generateMonster() {
        return (Monster) getRandom(monsterArr);
    }

    /**
     * This function random picks an item from an array and returns the item
     *
     * @param array the array to get random Item
     * @return The random chosen item
     */
    public static <T> T getRandom(List<T> array) {
        int rnd = new Random().nextInt(array.size());
        return array.get(rnd);

    }

    /**
     * This Method randomly generates an integer within the range from min to max
     * @param min the minimum value to generate
     * @param max the maximum value to generate
     * @return  the random value in range
     */
    //credit: https://stackoverflow.com/a/363692
    public int getRandomInt(int min, int max) {
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    /**
     * This method generates either true or false randomly
     * @return either true or false
     */
    //Credit: https://stackoverflow.com/a/15291309
    public boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }

    /**
     * This method generates treasure given a monster.
     * @param monster the monster which is dropping
     * @return the newly created treasure
     */
    public Treasure generateTreasure(Monster monster) {

        //get random value from hash map
        //credit: https://stackoverflow.com/a/929574
        String[] values = getRandom(new ArrayList<String[]>(treasureLines.values()));

        //recursively find base item
        String baseItem = randomRecursiveSearch(getRandom(Arrays.asList(values)), treasureLines);

        //get base Item Stats
        String[] statRanges = treasureStatsLines.get(baseItem);

        //generate stat between range provided
        int baseStat = getRandomInt(Integer.parseInt(statRanges[0]), Integer.parseInt(statRanges[1]));

        Affix prefix = null;
        Affix suffix = null;

        // 50/50 chance for prefix and affix
        if (getRandomBoolean()) {
            prefix = generateRandomAffix(magicPrefixLines);
        }
        if (getRandomBoolean()) {
            suffix = generateRandomAffix(magicSuffixLines);
        }

        return new Treasure(baseItem, baseStat, prefix, suffix);
    }

    /**
     * Generates a random affix from the loaded lines 
     * @param magicAffixLines the data lines to be manipulated
     * @return a new AFfix object
     */
    private Affix generateRandomAffix(Map<String, String[]> magicAffixLines) {
        String randAffix = getRandom(Arrays.asList(magicAffixLines.keySet().toArray(new String[0])));
        String[] affixInfo = magicAffixLines.get(randAffix);
        String mod1Code = affixInfo[0];
        int mod1Number = getRandomInt(Integer.parseInt(affixInfo[1]), Integer.parseInt(affixInfo[2]));

        return new Affix(randAffix, mod1Code, mod1Number);
    }

    /**
     * Recursive method which recursively finds an object in a data set
     * @param item Current item to be searched 
     * @param map the map which is being searched. 
     * @return the found item 
     */
    private String randomRecursiveSearch(String item, Map map) {
        //Base case
        if (!map.containsKey(item)) {
            return item;
        } else {
            //Keep searching
            String[] values = (String[]) map.get(item);

            return randomRecursiveSearch(getRandom(Arrays.asList(values)), map);
        }
    }
}
