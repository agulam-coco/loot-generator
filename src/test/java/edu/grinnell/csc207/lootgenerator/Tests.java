package edu.grinnell.csc207.lootgenerator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Tests {
        private static final String DATA_SET = "data/small";

    @Test
    public void testMonsterName(){
        Monster testMonster = new Monster("Hell Bovine","Cow",90,"Cow (H)");
        assertEquals(testMonster.getClassName(), "Hell Bovine", "Monster name in invalid");
        assertEquals(testMonster.getType(),"Cow", "Type does not match");
        assertEquals(testMonster.getLevel(), 90,"Incorrect Level");
        assertEquals(testMonster.getTreasureClass(), "Cow (H)","Invalid Class");

    }
    
   @Test 
   public void testMonsterGenerator(){
       Generator testGenerator = new Generator(DATA_SET);
       Monster testMonster = testGenerator.generateMonster();
       assertEquals(testMonster.getClassName(), "Hell Bovine", "Monster name in invalid");
        assertEquals(testMonster.getType(),"Cow", "Type does not match");
        assertEquals(testMonster.getLevel(), 90,"Incorrect Level");
        assertEquals(testMonster.getTreasureClass(), "Cow (H)","Invalid Class");

   }
}
