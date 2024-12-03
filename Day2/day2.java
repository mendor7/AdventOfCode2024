// Compile with javac .\Day2\day2.java
// Execute with java Day2.day2 .\Day2\input.txt

package Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;
import java.util.Collections;
import java.util.HashMap;

class day2 {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("NO FILE");
            return;
        }

        String filename = args[0];

        // Part 1
        int totalSafe = 0;
        boolean safe;
        boolean increasing;

        // Part 2
        int totalSafeDampened = 0;

        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lineSplit = line.split("\\s+");

                // Check Safe Part 1
                int num = Integer.parseInt(lineSplit[0]);
                safe = true;
                increasing = true;
                for (var i = 1; i < lineSplit.length; i++) {
                    if (increasing) {
                        int temp = Integer.parseInt(lineSplit[i]);
                        if (temp > num) {
                            int distance = temp - num;
                            if (distance < 1 || distance > 3) {
                                safe = false;
                                break;
                            }
                            num = Integer.parseInt(lineSplit[i]);
                        }
                        else {
                            increasing = false;
                            i = 0;
                            num = Integer.parseInt(lineSplit[0]);
                        }
                    }
                    else {
                        int temp = Integer.parseInt(lineSplit[i]);
                        if (temp < num) {
                            int distance = num - temp;
                            if (distance < 1 || distance > 3) {
                                safe = false;
                                break;
                            }
                            num = Integer.parseInt(lineSplit[i]);
                        }
                        else {
                            safe = false;
                            break;
                        }
                    }
                }
                if (safe) {
                    totalSafe++;
                }
                // Part 2
                else {
                    for (var i = 0; i < lineSplit.length; i++) {
                        boolean safeDampened = true;
                        String[] lineSplitRemoved = new String[lineSplit.length - 1];
                        System.arraycopy(lineSplit, 0, lineSplitRemoved, 0, i);
                        System.arraycopy(lineSplit, i + 1, lineSplitRemoved, i, lineSplit.length - i - 1);

                        int numDampened = Integer.parseInt(lineSplitRemoved[0]);
                        increasing = true;
                        for (var j = 1; j < lineSplitRemoved.length; j++) {
                            if (increasing) {
                                int temp = Integer.parseInt(lineSplitRemoved[j]);
                                if (temp > numDampened) {
                                    int distance = temp - numDampened;
                                    if (distance < 1 || distance > 3) {
                                        safeDampened = false;
                                        break;
                                    }
                                    numDampened = Integer.parseInt(lineSplitRemoved[j]);
                                }
                                else {
                                    increasing = false;
                                    j = 0;
                                    numDampened = Integer.parseInt(lineSplitRemoved[0]);
                                }
                            }
                            else {
                                int temp = Integer.parseInt(lineSplitRemoved[j]);
                                if (temp < numDampened) {
                                    int distance = numDampened - temp;
                                    if (distance < 1 || distance > 3) {
                                        safeDampened = false;
                                        break;
                                    }
                                    numDampened = Integer.parseInt(lineSplitRemoved[j]);
                                }
                                else {
                                    safeDampened = false;
                                    break;
                                }
                            }
                        }
                        if (safeDampened) {
                            totalSafeDampened++;
                            break;
                        }
                    }
                }
            }
            scanner.close();

            System.out.println(totalSafe);
            System.out.println(totalSafe + totalSafeDampened);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found " + filename);
        }
    }
}