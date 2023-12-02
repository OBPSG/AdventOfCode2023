import java.io.File;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        File file = new File("input.txt");
        try{
            String[] numerals = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
            Map<String, String> numbers = new HashMap<>();
            numbers.put("one", "1");
            numbers.put("two", "2");
            numbers.put("three", "3");
            numbers.put("four", "4");
            numbers.put("five", "5");
            numbers.put("six", "6");
            numbers.put("seven", "7");
            numbers.put("eight", "8");
            numbers.put("nine", "9");
            int result = 0;
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                Map<Integer, String> digitPositions = new HashMap<>();
                System.out.println("Line read: " + line);
                Integer digitOnePosition, digitTwoPosition;

                for(String number: numerals){
                    int position = line.indexOf(number);
                    if(position >= 0) {
                        digitPositions.put(position, String.valueOf(line.charAt(position)));
                }
                    position = line.lastIndexOf(number);
                    if(position >= 0) {
                        digitPositions.put(position, String.valueOf(line.charAt(position)));
                    }
                }

                for (Map.Entry<String, String> entry : numbers.entrySet()) {
                    int position = line.indexOf(entry.getKey());
                    if (position >= 0) {
                        digitPositions.put(position, entry.getValue());
                    }
                    position = line.lastIndexOf(entry.getKey());
                    if (position >= 0) {
                        digitPositions.put(position, entry.getValue());
                    }
                }

                System.out.println("Digit Positions and values: ");
                for(Map.Entry<Integer, String> entry: digitPositions.entrySet()) {
                    System.out.print(" (" + entry.getKey() + ", " + entry.getValue() + "), ");
                }

                digitOnePosition = digitPositions.keySet().stream().min((o1, o2) -> o1 - o2).get();
                System.out.print(" First Digit:" + digitOnePosition);
                digitTwoPosition = digitPositions.keySet().stream().max((o1, o2) -> o1 - o2).get();
                System.out.print(" Last Digit:" + digitTwoPosition);

                String digitOne = digitPositions.get(digitOnePosition);
                String digitTwo = digitPositions.get(digitTwoPosition);
                int finalValue = Integer.parseInt( digitOne + digitTwo);
                System.out.println(" --> Final Number: " + finalValue);
                result += finalValue;
            }
            System.out.println("----------------------------------------------");
            System.out.println("FINAL TOTAL: " + result);

        } catch (Exception ex)
        {
            ex.printStackTrace();
            System.err.println("File Path: " + file.getAbsolutePath());
        }
    }
}