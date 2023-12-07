import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Path inputPath = Paths.get("input.txt");
        /*String testInput = "467..114..\n" +
                "...*......\n" +
                "..35..633.\n" +
                "......#...\n" +
                "617*......\n" +
                ".....+.58.\n" +
                "..592.....\n" +
                "......755.\n" +
                "...$.*....\n" +
                ".664.598..";
                */

        try {
            int result = 0;
            List<String> input = Files.readAllLines(inputPath);
            //List<String> input = List.of(testInput.split("\n"));
            //populate the grid
            int gridHeight = input.size();
            int gridWidth = input.get(0).length();
            System.out.println(gridHeight + " " + gridWidth);
            char[][] grid = new char[gridHeight][gridWidth];
            List<Character> specialCharacters = new ArrayList<>();
            specialCharacters.add('@');
            specialCharacters.add('#');
            specialCharacters.add('$');
            specialCharacters.add('%');
            specialCharacters.add('&');
            specialCharacters.add('*');
            specialCharacters.add('-');
            specialCharacters.add('+');
            specialCharacters.add('=');
            specialCharacters.add('/');
            for (int i = 0; i < gridHeight; i++) {
                for (int j = 0; j < gridWidth; j++) {
                    grid[i][j] = input.get(i).charAt(j);
                }
            }

            //Traverse grid searching for potential gears (asterisks)
            System.out.println("Gear Locations and ratios: ");
            for (int i = 0; i < gridHeight; i++) {
                List<Integer> partNums = new ArrayList<Integer>();
                System.out.print("Line " + (i + 1) + ": ");
                for (int j = 0; j < gridWidth; j++) {
                    char position = grid[i][j];
                    if (position == '*') {
                        int gearRatio = 0;
                        //Scan around asterisk for part numbers
                        Boolean offUpperEdge = (i > 0);
                        Boolean offLowerEdge = (i < (gridHeight - 1));
                        Boolean offLeftEdge = (j > 0);
                        Boolean offRightEdge = (j < (gridWidth - 1));

                        //Upper Left corner
                        if(offUpperEdge && offLeftEdge) {
                            if (Character.isDigit(grid[i - 1][j - 1])) {
                                int partNum = ParsePartNumber(grid, j - 1, i - 1);
                                if(!partNums.contains(partNum)) partNums.add(partNum);
                            }
                        }
                        //Upper Middle
                        if(offUpperEdge) {
                            if (Character.isDigit(grid[i - 1][j])) {
                                int partNum = ParsePartNumber(grid, j, i - 1);
                                if(!partNums.contains(partNum)) partNums.add(partNum);
                            }
                        }
                        //Upper Right Corner
                        if(offUpperEdge && offRightEdge) {
                            if (Character.isDigit(grid[i - 1][j + 1])) {
                                int partNum = ParsePartNumber(grid, j + 1, i - 1);
                                if(!partNums.contains(partNum)) partNums.add(partNum);
                            }
                        }
                        //Middle Left
                        if(offLeftEdge) {
                            if (Character.isDigit(grid[i][j - 1])) {
                                int partNum = ParsePartNumber(grid, j - 1, i);
                                if(!partNums.contains(partNum)) partNums.add(partNum);
                            }
                        }
                        //Lower Left Corner
                        if(offLowerEdge && offLeftEdge) {
                            if (Character.isDigit(grid[i + 1][j - 1])) {
                                int partNum = ParsePartNumber(grid, j - 1, i + 1);
                                if(!partNums.contains(partNum)) partNums.add(partNum);
                            }
                        }
                        //Middle Right
                        if(offRightEdge) {
                            if (Character.isDigit(grid[i][j + 1])) {
                                int partNum = ParsePartNumber(grid, j + 1, i);
                                if(!partNums.contains(partNum)) partNums.add(partNum);
                            }
                        }
                        //Lower Right
                        if(offLowerEdge && offRightEdge) {
                            if (Character.isDigit(grid[i + 1][j + 1])) {
                                int partNum = ParsePartNumber(grid, j + 1, i + 1);
                                if(!partNums.contains(partNum)) partNums.add(partNum);
                            }
                        }
                        //Lower Middle
                        if(offLowerEdge) {
                            if (Character.isDigit(grid[i + 1][j])) {
                                int partNum = ParsePartNumber(grid, j, i + 1);
                                if(!partNums.contains(partNum)) partNums.add(partNum);
                            }
                        }
                        if(partNums.size() >= 2){
                            System.out.print(partNums + " ");
                            gearRatio = partNums.get(0)*partNums.get(1);
                            System.out.print("Ratio of " + gearRatio + " | ");
                        }
                        result += gearRatio;
                        partNums.clear();
                    }
                }
                System.out.println("");
            }

            System.out.println("--------------------------------------------------------------------");
            System.out.println("FINAL TOTAL: " + result);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static int ParsePartNumber(char[][] grid, int initialPositionX, int initialPositionY){
        List<Character> digits = new ArrayList<Character>();
        digits.add(grid[initialPositionY][initialPositionX]);
        //System.out.print("PPN digits: " + digits + " ");
        int k = initialPositionX - 1, l = initialPositionX + 1;
        while(k >= 0){
            if(Character.isDigit(grid[initialPositionY][k])){
                digits.add(0, grid[initialPositionY][k]);
            }
            else break;
            k--;
        }
        while (l < grid[initialPositionX].length) {
            if(Character.isDigit(grid[initialPositionY][l])){
                digits.add(grid[initialPositionY][l]);
            }
            else break;
            l++;
        }
        StringBuilder sb = new StringBuilder();
        for(Character c : digits) sb.append(c);
        return Integer.parseInt(sb.toString());
    }
}