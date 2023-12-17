package Midterm;

import java.util.Scanner;
public class Lab002 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter sentence: ");
        String sentence = scanner.nextLine();
        System.out.print("Enter letter: ");
        char letter = scanner.nextLine().charAt(0);

        letter = Character.toLowerCase(letter);

        int count = 0;

        for (int i = 0; i < sentence.length(); i++) {
            char currentChar = Character.toLowerCase(sentence.charAt(i));
            if (currentChar == letter) {
                count++;
            }
        }

        System.out.printf("The letter '%c' occurred %d time/s.\n", letter, count);

        scanner.close();
    }
}