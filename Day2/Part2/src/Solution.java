import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) {
        File file = new File("input.txt");
        try{
            int result = 0;
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()) {
                int redCubesMin = 0, greenCubesMin = 0, blueCubesMin = 0;
                String line = scanner.nextLine();
                int gameId = Integer.parseInt(line.substring(5, line.indexOf(":")));
                System.out.print("Game ID: " + gameId + " | ");
                String gameLine = line.substring(line.indexOf(":") + 2);
                System.out.println(gameLine);
                String[] rounds = gameLine.split(";");
                for(String round : rounds){
                    int redCubesDrawn = 0, greenCubesDrawn = 0, blueCubesDrawn = 0;
                    Pattern numPattern = Pattern.compile("\\d*");

                    Pattern redPattern = Pattern.compile("\\d* red");
                    Matcher mred = redPattern.matcher(round);
                    if(mred.find()){
                        Matcher mnum = numPattern.matcher(mred.group(0));
                        mnum.find();
                        redCubesDrawn = Integer.parseInt(mnum.group(0));
                    }

                    Pattern greenPattern = Pattern.compile("\\d* green");
                    Matcher mgreen = greenPattern.matcher(round);
                    if(mgreen.find()){
                        Matcher mnum = numPattern.matcher(mgreen.group(0));
                        mnum.find();
                        greenCubesDrawn = Integer.parseInt(mnum.group(0));
                    }

                    Pattern bluePattern = Pattern.compile("\\d* blue");
                    Matcher mblue = bluePattern.matcher(round);
                    if(mblue.find()){
                        Matcher mnum = numPattern.matcher(mblue.group(0));
                        mnum.find();
                        blueCubesDrawn = Integer.parseInt(mnum.group(0));
                    }

                    System.out.print("rgb [" + redCubesDrawn + ", " + greenCubesDrawn + ", " + blueCubesDrawn + "] | ");

                    if(redCubesDrawn > redCubesMin) {redCubesMin = redCubesDrawn;}
                    if(greenCubesDrawn > greenCubesMin ) {greenCubesMin = greenCubesDrawn;}
                    if(blueCubesDrawn > blueCubesMin ) {blueCubesMin = blueCubesDrawn;}

                }

                int power = redCubesMin * greenCubesMin * blueCubesMin;
                System.out.println("Min RGB: " + redCubesMin + ", " + greenCubesMin + ", " + blueCubesMin + " | " + "Power: " + power);
                result += power;

            }
            System.out.println("------------------------------------------------------------------------------------");
            System.out.println("FINAL POWER TOTAL: " + result);
        } catch (Exception ex)
        {
            ex.printStackTrace();
            System.err.println("File Path: " + file.getAbsolutePath());
        }
    }
}