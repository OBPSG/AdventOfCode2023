import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {

    record RaceEntry(int time, int distance) {
    }

    public static void main(String[] args) {
        List<RaceEntry> races = new ArrayList<RaceEntry>();

        File inputFile = new File("input.txt");
        try {
            int result = 1;

            Scanner scanner = new Scanner(inputFile);
            String timeLine = scanner.nextLine();
            List<Integer> times = new ArrayList<>();
            Pattern numberPattern =  Pattern.compile("\\d+");
            Matcher numberMatcher = numberPattern.matcher(timeLine);
            while(numberMatcher.find()){
                times.add(Integer.valueOf(numberMatcher.group()));
            }

            String distanceLine = scanner.nextLine();
            List<Integer> distances = new ArrayList<>();
            numberMatcher = numberPattern.matcher(distanceLine);
            while(numberMatcher.find()){
                distances.add(Integer.valueOf(numberMatcher.group()));
            }

            for(int i = 0; i < times.size(); i++){
                races.add(new RaceEntry(times.get(i), distances.get(i)));
            }

            for (RaceEntry entry : races){
                int winningCombos = 0;
                for(int j = 0; j < entry.time; j++){
                    int distanceMoved = j*(entry.time - j);
                    if(distanceMoved > entry.distance){
                        winningCombos += 1;
                    }
                }
                result *= winningCombos;
            }

        System.out.println("Final RESULT: " + result);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File Path: " + inputFile.getAbsolutePath());
        }

    }
}