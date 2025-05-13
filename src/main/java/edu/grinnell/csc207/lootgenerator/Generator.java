/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt 
 * to change this license
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
 * Generator class that loads data from files and provides methods for
 * generating random monsters and treasure.
 * 
 * Credit:
 * - https://stackoverflow.com/a/363692
 * - https://stackoverflow.com/a/15291309
 * - https://stackoverflow.com/a/929574
 * - https://www.digitalocean.com/community/tutorials/java-read-file-line-by-line
 */
public class Generator {

    private List<Monster> monsterArr = new ArrayList<>();
    private Map<String, String[]> treasureLines = new HashMap<>();
    private Map<String, String[]> treasureStatsLines = new HashMap<>();
    private Map<String, String[]> magicPrefixLines = new HashMap<>();
    private Map<String, String[]> magicSuffixLines = new HashMap<>();

    private final String defaultDelim = "\t";

    /**
     * Loads data into memory from the given dataset path.
     *
     * @param dataSet the base path of the dataset
     */
    public Generator(String dataSet) {
        loadMonsters(dataSet + "/monstats.txt");
        loadLines(dataSet + "/TreasureClassEx.txt", treasureLines);
        loadLines(dataSet + "/armor.txt", treasureStatsLines);
        loadLines(
            dataSet + "/MagicPrefix.txt", magicPrefixLines
        );
        loadLines(
            dataSet + "/MagicSuffix.txt", magicSuffixLines
        );
    }

    /**
     * Loads all monsters from the data set into program memory.
     *
     * @param filePath the path to the data set
     */
    private void loadMonsters(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(defaultDelim);

                if (columns.length > 0) {
                    String tempClass = columns[0];
                    String tempType = columns[1];
                    int tempLevel = Integer.parseInt(columns[2]);
                    String tempTreasureClass = columns[3];

                    monsterArr.add(
                        new Monster(tempClass, tempType, tempLevel, tempTreasureClass)
                    );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads lines of data into memory, storing the first value as the key.
     *
     * @param filePath the path to the data file
     * @param linesMap the map to populate
     */
    private void loadLines(String filePath, Map<String, String[]> linesMap) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(defaultDelim);

                if (columns.length > 0) {
                    String key = columns[0];
                    String[] value = Arrays.copyOfRange(columns, 1, columns.length);
                    linesMap.put(key, value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates a new random monster using the data set.
     *
     * @return a random Monster
     */
    public Monster generateMonster() {
        return getRandom(monsterArr);
    }

    /**
     * Randomly picks an item from a list and returns it.
     *
     * @param <T>   the type of elements in the list
     * @param array the list to choose from
     * @return the randomly selected item
     */
    public static <T> T getRandom(List<T> array) {
        int rnd = new Random().nextInt(array.size());
        return array.get(rnd);
    }

    /**
     * Randomly generates an integer within the range from min to max (inclusive).
     *
     * @param min the minimum value
     * @param max the maximum value
     * @return the generated integer
     */
    public int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    /**
     * Randomly returns either true or false.
     *
     * @return true or false
     */
    public boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }

    /**
     * Generates treasure based on a given monster.
     *
     * @param monster the monster to generate treasure for
     * @return a new Treasure object
     */
    public Treasure generateTreasure(Monster monster) {
        String[] values = getRandom(new ArrayList<>(treasureLines.values()));
        String baseItem = randomRecursiveSearch(
            getRandom(Arrays.asList(values)), treasureLines
        );

        String[] statRanges = treasureStatsLines.get(baseItem);
        int baseStat = getRandomInt(
            Integer.parseInt(statRanges[0]), Integer.parseInt(statRanges[1])
        );

        Affix prefix = null;
        Affix suffix = null;

        if (getRandomBoolean()) {
            prefix = generateRandomAffix(magicPrefixLines);
        }
        if (getRandomBoolean()) {
            suffix = generateRandomAffix(magicSuffixLines);
        }

        return new Treasure(baseItem, baseStat, prefix, suffix);
    }

    /**
     * Generates a random affix from the given affix map.
     *
     * @param magicAffixLines the affix data map
     * @return a new Affix object
     */
    private Affix generateRandomAffix(Map<String, String[]> magicAffixLines) {
        String randAffix = getRandom(
            Arrays.asList(magicAffixLines.keySet().toArray(new String[0]))
        );
        String[] affixInfo = magicAffixLines.get(randAffix);
        String mod1Code = affixInfo[0];
        int mod1Number = getRandomInt(
            Integer.parseInt(affixInfo[1]), Integer.parseInt(affixInfo[2])
        );

        return new Affix(randAffix, mod1Code, mod1Number);
    }

    /**
     * Recursively searches through a map to find a base item.
     *
     * @param item the current item to check
     * @param map  the map to search in
     * @return the final base item
     */
    private String randomRecursiveSearch(String item, Map<String, String[]> map) {
        if (isFinalUnit(map, item)) {
            return item;
        } else {
            String[] values = map.get(item);
            return randomRecursiveSearch(getRandom(Arrays.asList(values)), map);
        }
    }

    /**
     * Checks if the given item is a terminal entry in the map.
     *
     * @param map  the map to search
     * @param item the item to check
     * @return true if the item is a final unit, false otherwise
     */
    private static boolean isFinalUnit(Map<String, String[]> map, String item) {
        return !map.containsKey(item);
    }
}
