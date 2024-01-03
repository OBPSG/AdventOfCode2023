import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {

    record RaceEntry(long time, long distance) {
    }

    public static void main(String[] args) {
        File inputFile = new File("input.txt");
        try {
            int result = 1;

            Scanner scanner = new Scanner(inputFile);
            String timeLine = scanner.nextLine();
            StringBuilder timeDigits = new StringBuilder();
            Pattern digitPattern =  Pattern.compile("\\d+");
            Matcher numberMatcher = digitPattern.matcher(timeLine);
            while(numberMatcher.find()){
                timeDigits.append(numberMatcher.group());
            }

            String distanceLine = scanner.nextLine();
            StringBuilder distanceDigits = new StringBuilder();
            numberMatcher = digitPattern.matcher(distanceLine);
            while(numberMatcher.find()){
                distanceDigits.append(numberMatcher.group());
            }

            RaceEntry entry = new RaceEntry(Long.valueOf(timeDigits.toString()), Long.valueOf(distanceDigits.toString()));
            System.out.println(entry);

            int winningCombos = 0;
                for(int j = 0; j < entry.time; j++){
                    long distanceMoved = j*(entry.time - j);
                    if(distanceMoved > entry.distance){
                        winningCombos += 1;
                    }
                }


            System.out.println("Final RESULT: " + winningCombos);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File Path: " + inputFile.getAbsolutePath());
        }

    }
}