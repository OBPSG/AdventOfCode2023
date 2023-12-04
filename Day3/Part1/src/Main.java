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
            for(int i = 0; i < gridHeight; i++){
                for(int j = 0; j < gridWidth; j++){
                    grid[i][j] = input.get(i).charAt(j);
                }
            }

            //Traverse grid searching for digits
            System.out.println("Valid Part Numbers: ");
            for(int i = 0; i < gridHeight; i++){
                StringBuilder partNumString = new StringBuilder();
                int partNum = 0;
                Boolean isValidPartNum = false;
                System.out.print("Line " + (i + 1) + ": ");
                for(int j = 0; j < gridWidth; j++){
                    char position = grid[i][j];
                    if (!Character.isDigit(position)){
                        //Either we haven't found a digit yet, or reached the end of the number
                        //In either case, check if the part number is valid and reset sentinel values
                        if(isValidPartNum){
                            partNum = Integer.parseInt(partNumString.toString());
                            System.out.print(partNum + ", ");
                            result += partNum;
                            partNum = 0;
                        }
                        partNumString = new StringBuilder();
                        isValidPartNum = false;
                    }
                    //If a digit is found, add it to the number string and check if there are any special characters around it
                    else if (Character.isDigit(position))
                    {
                        Boolean offUpperEdge = (i > 0);
                        Boolean offLowerEdge = (i < (gridHeight - 1));
                        Boolean offLeftEdge = (j > 0);
                        Boolean offRightEdge = (j < (gridWidth - 1));
                        partNumString.append(position);
                        if(offUpperEdge) {
                            if(offLeftEdge) {
                                //Upper Left corner
                                if(specialCharacters.contains(grid[i - 1][j - 1])) isValidPartNum = true;
                            }//Upper Middle
                            if(specialCharacters.contains(grid[i - 1][j])) isValidPartNum = true;
                            //Upper Right Corner
                            if(offRightEdge){
                                if(specialCharacters.contains(grid[i - 1][j + 1])) isValidPartNum = true;
                            }
                        }
                        if(offLeftEdge){
                            //Middle Left
                            if(specialCharacters.contains(grid[i][j - 1])) isValidPartNum = true;
                            //Lower Left Corner
                            if(offLowerEdge){
                                if(specialCharacters.contains(grid[i + 1][j - 1])) isValidPartNum = true;
                            }
                        }
                        if(offRightEdge){
                            //Middle Right
                            if(specialCharacters.contains(grid[i][j + 1])) isValidPartNum = true;
                            //Lower Right
                            if(offLowerEdge){
                                if(specialCharacters.contains(grid[i + 1][j + 1])) isValidPartNum = true;
                            }
                        }
                        //Lower Middle
                        if(offLowerEdge){
                            if(specialCharacters.contains(grid[i + 1][j])) isValidPartNum = true;
                        }
                    }
                }
                //We've reached the end of the row, so add the current number (if it's valid) to the total
                if(isValidPartNum){
                    partNum = Integer.parseInt(partNumString.toString());
                    System.out.print(partNum + ", ");
                    result += partNum;
                    partNum = 0;
                }
                partNumString = new StringBuilder();
                isValidPartNum = false;
                System.out.println("");
            }

            System.out.println("--------------------------------------------------------------------");
            System.out.println("FINAL TOTAL: " + result);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}