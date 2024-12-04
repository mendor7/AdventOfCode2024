// Compile with javac .\Day4\day4.java
// Execute with java Day4.day4 .\Day4\input.txt

package Day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;
import java.util.Collections;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class day4 {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("NO FILE");
            return;
        }

        String filename = args[0];

        // Part 1
        char[][] grid = new char[140][140];
        int x = 0;
        int y = 0;
        int totalXMAS = 0;

        // Part 2
        int totalX_MAS = 0;

        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                // Populate Grid
                for (var i = 0; i < line.length(); i++) {
                    if (x == 140) {
                        x = 0;
                        y++;
                    }
                    grid[x][y] = line.charAt(i);
                    x++;
                }
            }
            scanner.close();

            // Part 1
            for (var i = 0; i < 140; i++) { // Column
                for (var j = 0; j < 140; j++) { // Row
                    if (grid[j][i] == 'X') {
                        var tempX = j;
                        var tempY = i;

                        // Diagonal Up Left
                        if (tempX - 3 >= 0 && tempY - 3 >= 0) {
                            if (grid[tempX - 1][tempY - 1] == 'M' && grid[tempX - 2][tempY - 2] == 'A' && grid[tempX - 3][tempY - 3] == 'S') {
                                totalXMAS++;
                            }
                        }
                        // Left
                        if (tempX - 3 >= 0) {
                            if (grid[tempX - 1][tempY] == 'M' && grid[tempX - 2][tempY] == 'A' && grid[tempX - 3][tempY] == 'S') {
                                totalXMAS++;
                            }
                        }
                        // Diagonal Down Left
                        if (tempX - 3 >= 0 && tempY + 3 < 140) {
                            if (grid[tempX - 1][tempY + 1] == 'M' && grid[tempX - 2][tempY + 2] == 'A' && grid[tempX - 3][tempY + 3] == 'S') {
                                totalXMAS++;
                            }
                        }
                        // Up
                        if (tempY - 3 >= 0) {
                            if (grid[tempX][tempY - 1] == 'M' && grid[tempX][tempY - 2] == 'A' && grid[tempX][tempY - 3] == 'S') {
                                totalXMAS++;
                            }
                        }
                        // Diagonal Up Right
                        if (tempX + 3 < 140 && tempY - 3 >= 0) {
                            if (grid[tempX + 1][tempY - 1] == 'M' && grid[tempX + 2][tempY - 2] == 'A' && grid[tempX + 3][tempY - 3] == 'S') {
                                totalXMAS++;
                            }
                        }
                        // Right
                        if (tempX + 3 < 140) {
                            if (grid[tempX + 1][tempY] == 'M' && grid[tempX + 2][tempY] == 'A' && grid[tempX + 3][tempY] == 'S') {
                                totalXMAS++;
                            }
                        }
                        // Diagonal Down Right
                        if (tempX + 3 < 140 && tempY + 3 < 140) {
                            if (grid[tempX + 1][tempY + 1] == 'M' && grid[tempX + 2][tempY + 2] == 'A' && grid[tempX + 3][tempY + 3] == 'S') {
                                totalXMAS++;
                            }
                        }
                        // Down
                        if (tempY + 3 < 140) {
                            if (grid[tempX][tempY + 1] == 'M' && grid[tempX][tempY + 2] == 'A' && grid[tempX][tempY + 3] == 'S') {
                                totalXMAS++;
                            }
                        }
                    }
                    // System.out.print(grid[j][i] + " ");
                }
                // System.out.print("\n");
            }

            // Part 2
            for (var i = 0; i < 140; i++) { // Column
                for (var j = 0; j < 140; j++) { // Row
                    if (grid[j][i] == 'A') {
                        var tempX = j;
                        var tempY = i;

                        if (tempX - 1 >= 0 && tempY - 1 >= 0 && tempX + 1 < 140 && tempY + 1 < 140) {
                            // M Top Left + M Bottom Left
                            if (grid[tempX - 1][tempY - 1] == 'M' && grid[tempX + 1][tempY + 1] == 'S' 
                                && grid[tempX - 1][tempY + 1] == 'M' && grid[tempX + 1][tempY - 1] == 'S') {
                                totalX_MAS++;
                            }
                            // M Bottom Left = M Bottom Right
                            else if (grid[tempX - 1][tempY + 1] == 'M' && grid[tempX + 1][tempY - 1] == 'S' 
                                && grid[tempX + 1][tempY + 1] == 'M' && grid[tempX - 1][tempY - 1] == 'S') {
                                totalX_MAS++;
                            }
                            // M Bottom Right + M Top Right
                            else if (grid[tempX + 1][tempY + 1] == 'M' && grid[tempX - 1][tempY - 1] == 'S' 
                                && grid[tempX + 1][tempY - 1] == 'M' && grid[tempX - 1][tempY + 1] == 'S') {
                                totalX_MAS++;
                            }
                            // M Top Right + M Top Left
                            else if (grid[tempX + 1][tempY - 1] == 'M' && grid[tempX - 1][tempY + 1] == 'S' 
                                && grid[tempX - 1][tempY - 1] == 'M' && grid[tempX + 1][tempY + 1] == 'S') {
                                totalX_MAS++;
                            }
                        }
                    }
                    // System.out.print(grid[j][i] + " ");
                }
                // System.out.print("\n");
            }

            System.out.println(totalXMAS);
            System.out.println(totalX_MAS);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found " + filename);
        }
    }
}