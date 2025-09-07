import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Dice {
    private static final Scanner in = new Scanner(System.in);
    private static final Random rng = new Random();

    public static void main (String[] args) {
        System.out.println("Dice Simulator 🎲");

        while (true) {
            System.out.print("\nEnter dice (example: 2d6) or type 'quit':");
            String input = in.nextLine().trim().toLowerCase();

            if (input.equals("quit")) {
                System.out.println("Goodbye!");
                break;
            }

            int numDice, numSides;

            try {
                // Accept dice notation like 2d6
                if (input.contains("d")) {
                    String[] parts = input.split("d");
                    numDice = Integer.parseInt(parts[0]);
                    numSides = Integer.parseInt(parts[1]);
                } else {
                    // fallback: ask manually
                    System.out.println("How many dice?");
                    numDice = Integer.parseInt(input);
                    System.out.println("How many sides per die?");
                    numSides = Integer.parseInt(in.nextLine());
                }

                if (numDice <= 0 || numSides <= 1) {
                    System.out.println("Please enter valid positive numbers (sides > 1).");
                    continue;
                }

                rollDice(numDice, numSides);

            } catch (Exception e) {
                System.out.println("Invalid input, please try again!");
            }
        }
    }
    private static void rollDice(int numDice, int numSides) {
        int[] results = new int[numDice];
        int total = 0;

        System.out.println("\nRolling" + numDice + "d" + numSides + "...");
        for (int i = 0; i < numDice; i++) {
            results[i] = rng.nextInt(numSides) + 1;
            total += results[i];
            printDieAscii(results[i], numSides);
        }

        System.out.println("\nResults:" + Arrays.toString(results));
        System.out.println("Total:" + total);
        System.out.println("Highest:" + Arrays.stream(results).max().getAsInt());
        System.out.println("Lowest:" + Arrays.stream(results).min().getAsInt());
        System.out.printf("Average: %.2f\n", Arrays.stream(results).average().getAsDouble());

        // Median calculation
        int[] sorted = results.clone();
        Arrays.sort(sorted);
        double median;
        if (sorted.length % 2 == 0) {
            median = (sorted[sorted.length / 2 - 1] + sorted[sorted.length / 2]) / 2.0;
        } else {
            median = sorted[sorted.length / 2];
        }
        System.out.println("Median" + median);
    }

    private static void printDieAscii(int value, int sides) {
        // Only pretty ASCII for 6-sided dice
        if (sides !=6) {
            System.out.println("Die: [" + value + "]");
            return;
        }

        String[][] faces = {
                {       "+-----+",
                        "|     |",
                        "|  *  |",
                        "|     |",
                        "+-----+"
                },
                {
                        "+-----+",
                        "|*    |",
                        "|     |",
                        "|    *|",
                        "+-----+"
                },
                {
                        "+-----+",
                        "|*    |",
                        "|  *  |",
                        "|    *|",
                        "+-----+"
                },
                {
                        "+-----+",
                        "|*   *|",
                        "|     |",
                        "|*   *|",
                        "+-----+"
                },
                {
                        "+-----+",
                        "|*   *|",
                        "|  *  |",
                        "|*   *|",
                        "+-----+"
                },
                {
                        "+-----+",
                        "|*   *|",
                        "|*   *|",
                        "|*   *|",
                        "+-----+"
                }
        };

        for (String line : faces[value - 1]) {
            System.out.println(line);
        }


            }
        }




