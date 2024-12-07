// Compile with javac .\Day5\day5.java
// Execute with java Day5.day5 .\Day5\input.txt

package Day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;
import java.util.Collections;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class day5 {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("NO FILE");
            return;
        }

        String filename = args[0];

        // Part 1
        Vector<String> rules = new Vector<>();
        Vector<String> numbers = new Vector<>();
        HashMap<Integer, Vector<Integer>> occurences = new HashMap<Integer, Vector<Integer>>();
        int offset = 0;
        int totalMiddle = 0;
        boolean correct;

        // Part 2
        Vector<Vector<Integer>> incorrect = new Vector<>();
        int totalIncorrectMiddle = 0;

        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                // Gather Data
                if (offset < 1176) {
                    rules.add(line);
                    offset++;
                }
                else {
                    numbers.add(line);
                    offset++;
                }
            }
            scanner.close();

            // Part 1
            // Get Rules
            for (int i = 0; i < rules.size(); i++) {
                String[] lineSplit = rules.get(i).split(",");
                int num = Integer.parseInt(lineSplit[0]);
                if (occurences.get(num) == null) {
                    Vector<Integer> temp = new Vector<>();
                    temp.add(Integer.parseInt(lineSplit[1]));
                    occurences.put(num, temp);
                }
                else {
                    Vector<Integer> temp = occurences.get(Integer.parseInt(lineSplit[0]));
                    temp.add(Integer.parseInt(lineSplit[1]));
                    occurences.put(Integer.parseInt(lineSplit[0]), temp);
                }
            }

            // Check correct
            for (int i = 0; i < numbers.size(); i++) {
                correct = true;
                String[] lineSplit = numbers.get(i).split(",");
                Vector<Integer> checks = new Vector<>();
                for (int j = lineSplit.length - 1; j >= 0; j--) {
                    int num = Integer.parseInt(lineSplit[j]);
                    checks.add(Integer.parseInt(lineSplit[j]));
                    for (int k = 0; k < checks.size() - 1; k++) {
                        if (!occurences.get(num).contains(checks.get(k))) {
                            correct = false;
                            break;
                        }
                    }
                    // Removing for Part 2
                    // if (!correct) {
                    //     break;
                    // }
                }
                if (correct) {
                    totalMiddle += Integer.parseInt(lineSplit[(lineSplit.length + 1) / 2 - 1]);
                }
                else {
                    incorrect.add(checks);
                }
            }

            // Part 2
            for (int i = 0; i < incorrect.size(); i++) {
                Vector<Integer> reOrdered = new Vector<>();
                for (int j = 0; j < incorrect.get(i).size(); j++) {
                    boolean contains = false;
                    int num = incorrect.get(i).get(j);
                    for (int k = j + 1; k < incorrect.get(i).size(); k++) {
                        if (occurences.get(num).contains(incorrect.get(i).get(k))) {
                            contains = true;
                            break;
                        }
                    }
                    if (!contains) {
                        reOrdered.add(num);
                        incorrect.get(i).remove(j);
                        j = -1;
                    }
                    if (incorrect.get(i).size() == 1) {
                        reOrdered.add(incorrect.get(i).get(0));
                    }
                }
                totalIncorrectMiddle += reOrdered.get((reOrdered.size() + 1) / 2 - 1);
            }

            System.out.println(totalMiddle);
            System.out.println(totalIncorrectMiddle);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found " + filename);
        }
    }
}