// Compile with javac .\Day1\day1.java
// Execute with java Day1.day1 .\Day1\input.txt

package Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;
import java.util.Collections;
import java.util.HashMap;

class day1 {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("NO FILE");
            return;
        }

        String filename = args[0];

        // Part 1
        Vector<Integer> left = new Vector<>();
        Vector<Integer> right = new Vector<>();
        int totalDistance = 0;

        // Part 2
        HashMap<Integer, Integer> occurences = new HashMap<Integer, Integer>();
        int totalSimilarity = 0;

        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lineSplit = line.split("\\s+");

                // Integer Retrieval Left
                int num = Integer.parseInt(lineSplit[0]);
                // Part 1
                left.add(num);

                // Integer Retrieval Right
                num = Integer.parseInt(lineSplit[1]);
                // Part 1
                right.add(num);
                // Part 2
                if (occurences.get(num) == null) {
                    occurences.put(num, 1);
                }
                else {
                    occurences.put(num, occurences.get(num) + 1);
                }
            }
            scanner.close();

            // Part 1
            Collections.sort(left);
            Collections.sort(right);

            for (var i = 0; i < left.size(); i++) {
                int distance = left.get(i) - right.get(i);
                if (distance < 0) {
                    distance = -distance;
                }
                totalDistance += distance;
            }
            System.out.println(totalDistance);

            // Part 2
            for (var i = 0; i < left.size(); i++) {
                var occur = occurences.get(left.get(i));
                if (occur != null && occur > 0) {
                    totalSimilarity += left.get(i) * occur;
                }
            }
            System.out.println(totalSimilarity);

        }
        catch (FileNotFoundException e) {
            System.out.println("File not found " + filename);
        }
    }
}