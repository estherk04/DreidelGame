package Functions;

import java.util.*;

public class DreidelGame
{
    // 'Pot'
    private static int pot = 4;

    // boolean to end the game when needed
    private static boolean end = false;

    // Main method
    public static void main(String[] args)
    {
        // Scanner
        Scanner keyboard = new Scanner(System.in);

        // Ask user how many players (with input validation)
        int numOfPlayers;
        while (true) {
            try {
                System.out.println("How many players are playing?");
                numOfPlayers = Integer.parseInt(keyboard.nextLine());
                if (numOfPlayers > 0) break;
                else System.out.println("Number of players must be greater than 0.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        // Create a LinkedList of players
        LinkedList<Player> players = new LinkedList<>();

        // Ask for each player's name and assign to correct player index
        for (int i = 0; i < numOfPlayers; i++)
        {
            int count = i + 1;
            // Ask for player's name (with input validation)
            String name;
            do {
                System.out.println("What is player " + count + "'s name?");
                name = keyboard.nextLine().trim();
                if (name.isEmpty()) System.out.println("Name cannot be empty.");
            } while (name.isEmpty());

            int score = 0;
            int playerOrder = i + 1;
            Player player = new Player(name, score, playerOrder);
            player.setScore(10);
            players.add(player);
        }
        // Play the game
        play(players, keyboard);
    }

    // Play the Game Method
    public static void play(LinkedList<Player> players, Scanner keyboard)
    {
        int turns = 1;

        while(!end)
        {
            System.out.println();
            System.out.println("Round "+ turns + ":");
            // Spin the dreidel
            spinDreidel(players, keyboard);

            // Increment amount of turns + 1
            turns++;

            // If there are 10 turns, end the game
            if(turns == 11){
                end = true;
            }
        }

        // Determine the winner
        Player winner = determineWinner(players);

        Player zeroPlayer = null;
        for(Player player : players){
            if(player.getScore() == 0){
                zeroPlayer = player;
            }
        }
        // Display the winner
        System.out.println();
        if(turns == 11) {
        System.out.println("The game is now over!");
        } else {
            System.out.println(zeroPlayer.getName() + " ran out of coins. The game is over.");
        }
        System.out.println("The winner is " + winner.getName() + "! Their score is " + winner.getScore() + "!");

        // Display everyone else's scores:
        for(Player player : players){
            System.out.println(player.getName() + " has " + player.getScore() + " points.");
        }
    }

    //Spin the Driedel Method
    public static void spinDreidel(LinkedList<Player> players, Scanner keyboard)
    {
        for(Player player :players){
            if(end){
                return;
            }
        }
        // Make sure there is no draw
        if (players.isEmpty()) {
            System.out.println("No players left with coins. The game ends in a draw.");
            end = true;
            return;
        }

        // Spin for each player's turn
        for (Player player : players)
        {
            String name = player.getName();
            int score = player.getScore();
            System.out.println();
            System.out.println("It is " + name + "'s turn. Press any key to spin!");
            keyboard.nextLine();

            // Spin the dreidel
            String result = dreidel();

            // Update the pot and player scores
            updatePot(result, player, players);
        }

        // If any player has less than 0 coins, end the game
        for (Player player : players){
            int currScore = player.getScore();
            if (currScore < 0){
                player.setScore(0);
                System.out.println(player.getName() + " has no coins left. The game is over.");
                end = true;
                return;
            }
        }
    }

    // Update the pot and player scores
    public static void updatePot(String result, Player player, LinkedList<Player> players){
        switch (result)
        {
            case "Nun":
                System.out.println(player.getName() + " got a נ. Nothing happened.");
                break;
            case "Gimmel":
                pot = gimmel(players, player);
                System.out.println("Everyone added 1 coin to the pot.");
                break;
            case "Hey":
                pot = hey(player);
                System.out.println(player.getName() + " got a ה. They got half the pot.");
                break;
            case "Shin":
                pot = shin(player);
                System.out.println(player.getName() + " got a ש. They added a coin to the pot.");
                break;
        }
        System.out.println("Their score is now " + player.getScore() + ".");
        System.out.println("The pot now has " + pot + " coins left.");
    }

    // Method for the result of gimmel
    public static int gimmel(LinkedList<Player> players, Player player){
        // Player gets all coins in the pot
        player.setScore(player.getScore() + pot);
        System.out.println(player.getName() + " got a ג. They picked up " + pot + " coins.");

        // Empty the pot
        pot = 0;

        // All players add one coin back into the pot (if they have)
        for(int i = 0; i < players.size(); i++){
            Player currentPlayer = players.get(i);
            if(currentPlayer.getScore() > 0){
            currentPlayer.setScore(currentPlayer.getScore() - 1);
            pot ++;
            } else if (currentPlayer.getScore() == 0){
                System.out.println(player.getName() + " has no coins left, the game is over.");
                end = true;
            }
        }
        return pot;
    }

    // Method for the result of hey
    public static int hey(Player player){
        // the player gets half the pot
        int halfPot = pot / 2;
        player.setScore(player.getScore() + halfPot);

        // Update the pot
        pot -= halfPot;

        return pot;
    }

    // Method for the result of shin
    public static int shin(Player player){
        // Player adds a coin to the pot if they have any coins
        if(player.getScore() > 0) {
        player.setScore(player.getScore() - 1);
        } else if (player.getScore() < 0) {
            System.out.println(player.getName() + " has no more coins. The game is now over.");
          end = true;
        }
        pot++;

        return pot;
    }

    // Get a random spin
    public static String dreidel()
    {
        List<String> dreidelResult = new ArrayList<>();
        dreidelResult.add("Nun");
        dreidelResult.add("Gimmel");
        dreidelResult.add("Hey");
        dreidelResult.add("Shin");

        Random random = new Random();

        int randomIndex = random.nextInt(dreidelResult.size());

        String randomResult = dreidelResult.get(randomIndex);
        return randomResult;
    }

    // Method to determine game winner
    public static Player determineWinner(LinkedList<Player> players)
    {
        // Start with the first player in the list
        Player winner = players.getFirst();

        // Iterate through the list to find the player with the highest score
        for (Player player : players)
        {
            if (player.getScore() > winner.getScore())
            {
                // Update if we find a higher score
                winner = player;
            }
        }
        return winner;
    }
}

// Create Player object Method
class Player
{
    String name;
    int score;
    int order;

    // Player Constructor
    public Player(String name, int score, int order)
    {
        this.name = name;
        this.score = score;
        this.order = order;
    }

    // Method to get player name
    public String getName()
    {
        return name;
    }

    // Method to get player score
    public int getScore()
    {
        return score;
    }

    // Method to get player order
    public int getOrder()
    {
        return order;
    }

    // Method to set player score
    public void setScore(int score)
    {
        this.score = score;
    }
}
