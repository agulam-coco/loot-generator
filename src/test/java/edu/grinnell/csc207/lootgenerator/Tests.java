package edu.grinnell.csc207.lootgenerator;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {

    private static final String DATA_SET = "data/small";

    @Test
    public void testMonsterName() {
        Monster testMonster = new Monster("Hell Bovine", "Cow", 90, "Cow (H)");
        assertEquals(testMonster.getClassName(), "Hell Bovine", "Monster name in invalid");
        assertEquals(testMonster.getType(), "Cow", "Type does not match");
        assertEquals(testMonster.getLevel(), 90, "Incorrect Level");
        assertEquals(testMonster.getTreasureClass(), "Cow (H)", "Invalid Class");

    }

    @Test
    public void testMonsterGenerator() {
        Generator testGenerator = new Generator(DATA_SET);
        Monster testMonster = testGenerator.generateMonster();
        assertEquals(testMonster.getClassName(), "Hell Bovine", "Monster name in invalid");
        assertEquals(testMonster.getType(), "Cow", "Type does not match");
        assertEquals(testMonster.getLevel(), 90, "Incorrect Level");
        assertEquals(testMonster.getTreasureClass(), "Cow (H)", "Invalid Class");

    }

    @Test
    public void testTreasureGenerator() {

        Generator testGenerator = new Generator(DATA_SET);
        Monster testMonster = testGenerator.generateMonster();

        Treasure testTreasure = testGenerator.generateTreasure(testMonster);
        List<String> validBaseItems = Arrays.asList(
                "Quilted Armor", "Buckler", "Leather Armor",
                "Embossed Plate", "Sun Spirit", "Fury Visor",
                "Sacred Rondache", "Mage Plate", "Diadem"
        );

        System.out.println(testTreasure.toString());
        assertEquals(validBaseItems.contains(testTreasure.getBaseItem()), true, "Generated base item should be a valid base item");

    }
}
