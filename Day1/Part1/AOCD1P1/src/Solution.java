import java.io.File;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        File file = new File("input.txt");
        try{
            int result = 0;
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                System.out.print("Line read: " + line);
                String digits = line.replaceAll("\\D", "");
                System.out.print(" --> Digits Extracted: " + digits );
                String digitOne = String.valueOf(digits.charAt(0));
                String digitTwo = String.valueOf(digits.charAt(digits.length() - 1));
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