// Compile with javac .\Day3\day3.java
// Execute with java Day3.day3 .\Day3\input.txt

package Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;
import java.util.Collections;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class day3 {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("NO FILE");
            return;
        }

        String filename = args[0];

        // Part 1
        int totalMul = 0;

        // Part 2
        int totalMulEnabled = 0;
        boolean enabled = true;

        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                // Part 1
                // Find mul(###,###)
                String regex = "mul\\(\\d{1,3},\\d{1,3}\\)";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(line);

                Vector<String> lineSplit = new Vector<>();

                while (matcher.find()) {
                    lineSplit.add(matcher.group());
                }

                for (var i = 0; i < lineSplit.size(); i++) {
                    String[] temp = lineSplit.get(i).split(",");
                    
                    String left = temp[0].replaceAll("mul\\(", "");
                    String right = temp[1].replaceAll("\\)", "");

                    totalMul += Integer.parseInt(left) * Integer.parseInt(right);
                }

                // Part 2
                String lineTemp = line;
                lineTemp = lineTemp.replaceAll("do\\(\\)", "mul\\(333,333\\)");
                lineTemp = lineTemp.replaceAll("don't\\(\\)", "mul\\(666,666\\)");

                // Find mul(###,###)
                String regexEnabled = "mul\\(\\d{1,3},\\d{1,3}\\)";
                Pattern patternEnabled = Pattern.compile(regexEnabled);
                Matcher matcherEnabled = patternEnabled.matcher(lineTemp);

                Vector<String> lineSplitEnabled = new Vector<>();

                while (matcherEnabled.find()) {
                    lineSplitEnabled.add(matcherEnabled.group());
                }

                for (var i = 0; i < lineSplitEnabled.size(); i++) {
                    String[] temp = lineSplitEnabled.get(i).split(",");
                    
                    String left = temp[0].replaceAll("mul\\(", "");
                    String right = temp[1].replaceAll("\\)", "");

                    int leftInt = Integer.parseInt(left);
                    int rightInt = Integer.parseInt(right);

                    if (leftInt == 333 && rightInt == 333) {
                        enabled = true;
                    }
                    else if (leftInt == 666 && rightInt == 666) {
                        enabled = false;
                    }
                    else {
                        if (enabled) {
                            totalMulEnabled += leftInt * rightInt;
                        }
                    }
                }
            }
            scanner.close();

            System.out.println(totalMul);
            System.out.println(totalMulEnabled);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found " + filename);
        }
    }
}