package edu.grinnell.csc207.lootgenerator;

import java.util.Scanner;

/**
 * The LootGenerator class runs an interactive loop where a monster is generated,
 * defeated, and a treasure drop is shown to the user.
 * The user can choose to continue or exit.
 * 
 * Dataset path is currently hardcoded to use the large set in the data directory.
 * 
 * Credit:
 * - https://www.codementor.io/@seranguyen/
 *   java-regular-expression-part-2-matching-text-for-validation-1-r3kua75tx
 * - https://stackoverflow.com/a/3059367
 * 
 */
public class LootGenerator {

    /**
     * The path to the dataset (either the small or large set).
     */
    private static final String DATA_SET = "data/large";

    /**
     * Main method to run the loot generation simulation.
     *
     * @param args the command-line arguments (not used)
     */
    public static void main(String[] args) {
        boolean isRunning = true;
        Generator generator = new Generator(DATA_SET);

        // main controlling loop
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

    /**
     * Prompts the user for input and validates it against a regular expression.
     *
     * @param prompt the message to display to the user
     * @param regex the regular expression to validate the input
     * @return the valid user input in lowercase
     */
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
