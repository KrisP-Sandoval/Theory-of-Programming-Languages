package Midterm;
import java.util.Scanner;

public class Lab001 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter word/phrase: ");
        String wordOrPhrase = scanner.nextLine();

        String cleanedString = wordOrPhrase.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        boolean isPalindrome = isPalindrome(cleanedString);

        if (isPalindrome) {
            System.out.println(wordOrPhrase + " is a palindrome");
        } else {
            System.out.println(wordOrPhrase + " is not a palindrome");
        }

        scanner.close();
    }

    public static boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
