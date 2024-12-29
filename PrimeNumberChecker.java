package Functions;

import java.util.Scanner;

public class PrimeNumberChecker
{
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);
        int number = 0;

        // Loop until 'Exit'
        while(number != -1)
        {
            // Ask the user for their number
            System.out.println("Please enter your number or press -1 to exit: ");
            number = keyboard.nextInt();

            // if number is -1, exit
            if (number == -1)
            {
                System.exit(0);
            }

            // Check if number is a prime number
            String answer = checkForPrime(number);

            // Print the answer
            System.out.println(answer);
        }
    }

    // Method to check if number is a Prime Number
    public static String checkForPrime(int number)
    {
        String string = "";
        for(int i = 2; i < number; i++)
        {
            if(number % i == 0)
            {
                string = "This is not a prime number.";
                return string;
            }
        }
        return "This is a prime number";
    }
}



