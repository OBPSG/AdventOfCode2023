import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        int result = 0;
        File inputFile = new File("input.txt");
        try {
            Scanner scanner = new Scanner(inputFile);
            while(scanner.hasNextLine()){
                int numMatches = 0;
                int score = 0;
                String line = scanner.nextLine();
                System.out.print(line.substring(0, line.indexOf(":") + 1));
                System.out.println();
                String winningNumString = line.substring((line.indexOf(":") + 1), (line.indexOf("|") - 1));
                Pattern numPattern = Pattern.compile("\\d+");
                List<Integer> winningNums = new ArrayList<>();
                Matcher numMatcher = numPattern.matcher(winningNumString);
                while(numMatcher.find()) {
                    winningNums.add(Integer.valueOf(numMatcher.group()));
                }
                System.out.print(winningNums);
                String numString = line.substring((line.indexOf("|") + 1));
                List<Integer> nums = new ArrayList<>();
                numMatcher = numPattern.matcher(numString);
                while(numMatcher.find()) {
                    nums.add(Integer.valueOf(numMatcher.group()));
                }
                System.out.print(" " + nums);
                System.out.println("");
                for(Integer num : nums){
                    if(winningNums.contains(num)){
                        numMatches += 1;
                        System.out.print( " (" + num + ") ");
                        //increment the score
                        if(score == 0){
                            score = 1;
                        }
                        else {score *= 2;}
                    }
                }
                result += score;
                System.out.println("Number of matches: " + numMatches + " | " + "Score: " + score );
            }
            System.out.println("FINAL TOTAL: " + result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(inputFile.getAbsolutePath());
        }
    }
}