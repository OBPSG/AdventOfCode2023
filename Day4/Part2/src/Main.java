import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        int result = 0;
        try {
            List<String> lines = Files.readAllLines(Path.of("input.txt"));
            List<Integer> cardCount = new ArrayList<>();
            for(int i = 0; i < lines.size(); i++) {
                cardCount.add(1);
            }
            for(int i = 0; i < lines.size(); i++){
                int numMatches = 0;
                String line = lines.get(i);
                System.out.print(line.substring(0, line.indexOf(":") + 1) + " | Count: " + cardCount.get(i));
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
                    }
                }
                System.out.println("Number of matches: " + numMatches);
                for(int j = 0; j < cardCount.get(i); j++){
                    updateCount(cardCount, i, numMatches);
                }

            }
            for(Integer element: cardCount) {
                result += element;
            }
            System.out.println("FINAL TOTAL: " + result);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void updateCount(List<Integer> counts, int startPosition, int numberOfMatches){
        for(int i = 1; i <= numberOfMatches; i++){
            int position = startPosition + i;
            if(position < counts.size()){
                counts.set(position, counts.get(position) + 1);
            }
        }
    }
}