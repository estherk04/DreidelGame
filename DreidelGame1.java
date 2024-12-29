package Functions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class DreidelGame1
{
    private static LinkedList<DreidelValues> TheValues = new LinkedList<>();
    private static LinkedList<DreidelPlayers> ThePlayers = new LinkedList<>();

    public void main(String[] args)
    {
        // integer to keep track of the max spin
        int maxSpinValue = 0;

        // ArrayList to keep track of the winners
        ArrayList<String> winners = new ArrayList<>();

        // Load Dreidel Values and Players
        LoadDreidelValues();
        LoadPlayers();

        // Assign a spin to each player
        for (DreidelPlayers player : ThePlayers)
        {
            Collections.shuffle(TheValues);
            player.SpinValue = TheValues.get(0).SpinValue;
            player.DisplayValue = TheValues.get(0).DisplayValue;
            player.PlayerScore = TheValues.get(0).ScoreValue;
        }

        // Find the highest spin
        int maxValue = 0;
        String maxDisplay = "";
        for (DreidelPlayers player : ThePlayers)
        {
            if (player.SpinValue > maxValue) {
                maxValue = player.SpinValue;
                maxDisplay = player.DisplayValue;
            }
        }

        // Determine the highest score*
        for (DreidelPlayers player : ThePlayers)
        {
            if (player.SpinValue == maxValue)
            {
                winners.add(player.PlayerName);
            }
        }

        // Display the highest score
        System.out.println("The highest spin is " + maxDisplay);

        // Display the player(s) with the highest score
        if (winners.size() == 1)
        {
            System.out.println("The player with the highest spin is: ");
            for (String player : winners)
            {
                System.out.println(player);
            }
        }
        else if (winners.size() > 1)
        {
            StringBuilder builder = new StringBuilder();
            System.out.println("The players with the highest spin are: ");
            for (String player : winners)
            {
                if (builder.length() != 0)
                {
                    builder.append(", ");
                }
                builder.append(player);
            }
            System.out.println(builder.toString());
        }

        // Display individual scores
        for (DreidelPlayers player : ThePlayers)
        {
            System.out.println(player.PlayerName + "'s score is " + player.PlayerScore + " (" + player.DisplayValue + ")");
        }
    }

    // Method to load players
    public static void LoadPlayers()
    {
        // Ask how many players are playing
        Scanner keyboard = new Scanner(System.in);
        System.out.println("How many players are playing?");
        int numOfPlayers = keyboard.nextInt();
        keyboard.nextLine();

        // Add each player to ThePlayers
        for(int i = 1; i<=numOfPlayers; i++)
        {
            ThePlayers.add(new DreidelPlayers("Player " + i, 0,"", 0));
        }

        // Ask each player their name and assign it to the correct Player
        for(DreidelPlayers player : ThePlayers)
        {
            System.out.println("What is your name " + player.PlayerName + "?");
            String playerName = keyboard.nextLine();player.PlayerName = playerName;
        }
    }

    // Method to load dreidel values
    public static void LoadDreidelValues()
    {
        TheValues.add(new DreidelValues(1, "נ", 0));
        TheValues.add(new DreidelValues(2, "ה", 1));
        TheValues.add(new DreidelValues(3, "ג", 3));
        TheValues.add(new DreidelValues(0, "ש", -3));
    }

    // Dreidel Players class
    public static class DreidelPlayers
    {
        public int SpinValue;
        public String PlayerName;
        public String DisplayValue;
        public int PlayerScore;

        // Dreidel Players constructor
        public DreidelPlayers(String playerName, int spinValue, String displayValue, int playerScore)
        {
            SpinValue = spinValue;
            PlayerName = playerName;
            DisplayValue = displayValue;
            PlayerScore = playerScore;
        }
    }

    // Dreidel Values class
    public static class DreidelValues
    {
        public int SpinValue;
        public String DisplayValue;
        public int ScoreValue;

        // Dreidel Values Constructor
        public DreidelValues(int spinValue, String displayValue, int scoreValue)
        {
            SpinValue = spinValue;
            DisplayValue = displayValue;
            ScoreValue = scoreValue;
        }
    }
}
