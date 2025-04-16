package edu.grinnell.csc207.lootgenerator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LootGenerator {

    /**
     * The path to the dataset (either the small or large set).
     */
    private static final String DATA_SET = "data/large";

    public static void main(String[] args) {
        boolean isRunning = true;

        Generator generator = new Generator(DATA_SET);

        //main controlling loop
        while (isRunning) {

            Monster monster = generator.generateMonster();

            System.out.printf("Fighting %s\n", monster.getClassName());
            System.out.printf("You have slain %s!\n", monster.getClassName());

            Treasure droppedTreasure = generator.generateTreasure(monster);

            System.out.printf("%s dropped:\n\n", monster.getClassName());
            System.out.println(droppedTreasure.toString());
            System.out.println();

            String response = getUserInput("Fight again [y/n]?", "y|n");

            if (response.equals("n")) {
               isRunning = false;
            }

        }

    }

    //credit:https://www.codementor.io/@seranguyen/java-regular-expression-part-2-matching-text-for-validation-1-r3kua75tx
    //credit:https://stackoverflow.com/a/3059367
    public static String getUserInput(String prompt, String regex) {
        Scanner scanner = new Scanner(System.in);
        String input;
        boolean isValid;

        do {
            System.out.print(prompt);
            input = scanner.nextLine().toLowerCase();
            isValid = input.matches(regex);
        } while (!isValid);

        return input;
    }
}
