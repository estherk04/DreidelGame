package Functions;
import Structures.BasicStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDate;
import java.util.Scanner;

public class Functions
{
    // Method to return a color based on the day of the week
    public static String returnColour()
    {
        try
        {
            String[] colors = "Red|Orange|Yellow|Green|Blue|Purple|Pink".split("\\|");
            return colors[LocalDate.now().getDayOfWeek().getValue() - 1];
        }
        catch (Exception e)
        {
            return "There was an error getting the color: " + e.getMessage();
        }
    }

    // Method to convert a string to Tile Case
    public static String ConvertToTitleCase(String input)
    {
        try
        {
            StringBuilder builder = new StringBuilder();
            for (String word : input.split("\\s"))
            {
                if (word.length() < 4)
                {
                    builder.append(word.toLowerCase());
                }
                else
                {
                    builder.append(word.substring(0, 1).toUpperCase());
                    builder.append(word.substring(1).toLowerCase());
                }
                builder.append(" ");
            }
            return builder.toString();
        }
        catch (Exception e)
        {
            return "There was an error getting the title case: " + e.getMessage();
        }
    }

    // Print input unless 'Exit' is the input
    public static void EchoInput()
    {
        String inputString;

        do
        {
            // Enter your input
            System.out.print("Enter any string: ");
            Scanner in = new Scanner(System.in);
            inputString = in.nextLine();

            // If the input is not 'Exit', print the input
            if (!inputString.toUpperCase().equals("EXIT"))
            {
                System.out.println("Your input is: " + inputString);
            }
            // If the input is 'Exit', exit the program
            else
            {
                System.out.println("Exit successful");
            }
        }
        while (!inputString.toUpperCase().equals("EXIT"));
    }

    // Method to determine if given string is a palindrome
    public static void isStringAPalindrome()
    {
        String inputString;

        do
        {
            // Enter your word
            System.out.print("Enter a word or 'Exit' to exit: ");
            Scanner in = new Scanner(System.in);
            inputString = in.nextLine();
            StringBuilder builder = new StringBuilder();

            // Build a stack
            BasicStack<String> stack = new BasicStack<String>();
            for (int i = 0; i < inputString.length(); i++) {
                stack.push(String.valueOf(inputString.charAt(i)));
            }

            // Build a string to compare to the stack
            for (int i = 0; i < inputString.length(); i++)
            {
                builder.append(stack.pop());
            }

            String newWord = builder.toString();

            // If input is 'Exit', exit
            if (inputString.toUpperCase().equals("EXIT"))
            {
                System.out.println("Exit successful");
            }
            // If the input equals the new string, the word is a palindrome
            else if (inputString.equals(newWord))
            {
                System.out.print("Your word is a palindrome.\n");
            }
            // If the input does not equal the new string, the word is not a palindrome
            else
            {
                System.out.print("Your word is not a palindrome.\n");
            }
        }
        while (!inputString.toUpperCase().equals("EXIT"));
    }

    public static void main(String[] args) {
//        returnColour();
//        ConvertToTitleCase("hello");
//        EchoInput();
//        isStringAPalindrome();
    }
}




