/**
* The Game class manages the playing of the Animal Tracking Game
* @ version 1.0
* @ author Rebecca Chalmers
*/

import java.util.Scanner;

public class Game
{
    private Dice dice;
    private Player player1; // Human Player
    private Player player2; // Computer Player
    private Trail natureTrail;

    /*
    Initialises the natureTrail, player1, player2, and dice fields 
    to default values
    */
    public Game()
    {
        natureTrail = new Trail();
        player1 = new Player("", 0, 0);
        player2 = new Player("Computer", 0, 0);
        dice = new Dice(4);
    }

    /*
    Initialises the natureTrail, player1, player2, and dice fields 
    to parameter values
    */
    public Game(Trail natureTrail, Player player1, Player player2, Dice dice)
    {
        this.natureTrail = natureTrail;
        this.player1 = player1;
        this.player2 = player2;
        this.dice = dice;
    }

    /* 
    Checks if there is an animal sighting (50% chance)
    If there is, determines which animal it is (1/5 for each animal),
    as well as the point gain/penalty to the player's score
    */
    public void checkAnimalSighting(Player player)
    {
        if (Math.random() < 0.5)
        {
            int animalIndex = (int) (Math.random() * 5);
            String animal = "";
            int points = 0;

            switch (animalIndex)
            {
                case 0:
                    animal = "Koala";
                    points = 10;
                    break;
                case 1:
                    animal = "Emu";
                    points = 7;
                    break;
                case 2:
                    animal = "Wombat";
                    points = 5;
                    break;
                case 3:
                    animal = "Kangaroo";
                    points = 2;
                    break;
                case 4:
                    animal = "Rabbit";
                    points = -5;
                    break;
            }

            System.out.print("    ");
            System.out.println("Animal sighted: " + animal
                + ", points: " + points);
            requestUpdateScore(player, points);
        }
        else
        {
            System.out.print("    ");
            System.out.println("No animal found.");
        }
    }

    /* 
    Checks if there is a nature feature on the current space,
    as well as the change to the players position
    (how many spaces they need to move forwards/backwards)
    */
    public int checkNatureFeature(Player player)
    {
        NatureFeature feature = 
            natureTrail.getNatureTrail()[player.getPosition()];
        
        if (feature != null)
        {
            int penalty = feature.getSpacePenalty();
            System.out.print("    ");
            System.out.println("Reached a nature feature: " + 
                feature.getFeatureName());
            System.out.print("    ");
            System.out.println("Position change: " + penalty);
            System.out.print("    ");
            return penalty;
        }

        return 0;
    }

    // Creates nature features (on the spaces) along with their space penalties
    public NatureFeature createNatureFeature(char symbol)
    {
        switch (symbol)
        {
            case 'c':
                return new NatureFeature('c', "Creek", -2);
            case 'b':
                return new NatureFeature('b', "Bridge", 4);
            case 'f':
                return new NatureFeature('f', "Fallen Tree", -3);
            case 'l':
                return new NatureFeature('l', "Landslide", -5);
            default:
                return null;
        }
    }

    // Displays the game results and ends the game
    public void displayGameResult()
    {
        System.out.println
            ("****************************************************");

        System.out.println(player1.getName() + "'s score: "
             + player1.getScore());
        System.out.println(player2.getName() + "'s score: "
             + player2.getScore());
        System.out.println();

        if (player1.getScore() > player2.getScore())
        {
            System.out.println(player1.getName() 
                + " has won the game with the highest score: " 
                + player1.getScore());
        }
        else if (player2.getScore() > player1.getScore())
        {
            System.out.println(player2.getName() 
                + " has won the game with the highest score: " 
                + player2.getScore());
        }
        else
        {
            System.out.println("The game is a draw.");
        }

        System.out.println
            ("****************************************************");
        System.out.println();

        displayNatureTrail();

        System.out.println();
        System.out.print("Press Enter to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        
        System.out.println("Goodbye");
    }

    /*
    Displays the nature trail with start (S) and finish (F) positions, 
    as well as the player tokens at their respective positions on the trail
    (player1 = H, player2 = C)
    */
    public void displayNatureTrail()
    {
        NatureFeature[] trailDisplay = natureTrail.getNatureTrail();
        
        for (int i = 0; i < trailDisplay.length; i++)
        {
            boolean isPlayerOneSpace = i == player1.getPosition();
            boolean isPlayerTwoSpace = i == player2.getPosition();
            boolean isStartSpace = i == 0;

            if (isStartSpace)
            {
                System.out.print("S");
            }
            
            if (isPlayerOneSpace && isPlayerTwoSpace)
            {
                System.out.print("HC");
            }
            else if (isPlayerOneSpace)
            {
                System.out.print("H");
            }
            else if (isPlayerTwoSpace)
            {
                System.out.print("C");
            }
            else if (!isStartSpace && !isPlayerOneSpace && !isPlayerTwoSpace)
            {
                System.out.print("_");
            }
        }
        System.out.println("F");
    }

    // Displays a welcome message on the screen
    public void displayWelcomeMessage()
    {
        System.out.println("Welcome to the Animal Tracking Game!");
    }

    // Returns the value of the field dice
    public Dice getDice()
    {
        return dice;
    }

    // Returns the value of the field player1
    public Player getPlayer1()
    {
        return player1;
    }

    // Returns the value of the field player2
    public Player getPlayer2()
    {
        return player2;
    }

    // Returns the value of the field natureTrail
    public Trail getNatureTrail()
    {
        return natureTrail;
    }

    // Main method - Initialises the game object and calls other methods
    public static void main(String[] args)
    {
        Game game = new Game();
        game.displayWelcomeMessage();
        game.requestPlayerName();
        game.selectTrail();
        game.runGame();
    }

    // Moves the player a number of places and displays their new position
    public void movePlayerSpaces(Player player, int roll)
    {
        int currentPosition = player.getPosition();
        int newPosition = currentPosition + roll;

        if (newPosition < 0)
        {
            newPosition = 0;
        }

        else if (newPosition >= natureTrail.getNatureTrail().length)
        {
            newPosition = natureTrail.getNatureTrail().length - 1;
        }
        
        player.setPosition(newPosition);
        System.out.println("New Position: " + (newPosition + 1));
    }

    // Requests a complex trail from the Landscape class
    public void requestComplexTrail()
    {
        char[] currentTrail = Landscape.getComplexTrail();
        NatureFeature[] features = new NatureFeature[currentTrail.length];

        for (int i = 0; i < currentTrail.length; i++)
        {
            char space = currentTrail[i];
            features[i] = createNatureFeature(space);
        }

        natureTrail = new Trail(features);
    }

    // Requests an easy trail from the Landscape class
    public void requestEasyTrail()
    {
        char[] currentTrail = Landscape.getEasyTrail();
        NatureFeature[] features = new NatureFeature[currentTrail.length];

        for (int i = 0; i < currentTrail.length; i++)
        {
            char space = currentTrail[i];
            features[i] = createNatureFeature(space);
        }
        natureTrail = new Trail(features);
    }

    // Requests player to enter their name and saves it
    public void requestPlayerName()
    {
        Scanner scanner = new Scanner(System.in);
        String name;
        boolean isValid = false;

        while (isValid == false)
        {
            System.out.print("Enter player name: ");
            name = scanner.nextLine();

            // must be 1-10 chars and not begin/end with a space character
            name = name.trim();
            if (name.length() >= 1 && name.length() <= 10)
            {
                isValid = true;
                player1.setName(name);
            }
            else
            {
                System.out.println
                    ("Error: number of characters is not in range 1-10");
            }
        }
    }

    // Requests the player object to adjust the score of a player
    public void requestUpdateScore(Player player, int points)
    {
        int currentScore = player.getScore();
        player.setScore(currentScore + points);
    }

    // Rolls the dice
    public int rollDice(Player player)
    {
        int roll = dice.generateDiceRoll(4);
        System.out.println("Throwing the dice... " + roll);
        return roll;
    }

    /*
    Runs the game
    (controls gameplay; players take turns until one reaches finish (F))
    */
    public void runGame()
    {
        Scanner scanner = new Scanner(System.in);
        boolean isGameOver = false;
        boolean playerOneFinished = false;
        boolean playerTwoFinished = false;
        int round = 1;

        while (!isGameOver)
        {
            System.out.println("*** Round " + round + " ***");
            System.out.println();
            displayNatureTrail();
            System.out.println();

            // Runs players' turns (until one reaches the finish space)
            if (!playerOneFinished)
            {
                playerOneFinished = runPlayerTurn(player1);
                if (playerOneFinished == true)
                {
                    isGameOver = true;
                }
            }

            if (!isGameOver && !playerTwoFinished)
            {
                playerTwoFinished = runPlayerTurn(player2);
                if (playerTwoFinished == true)
                {
                    isGameOver = true;
                }
            }

            // Displays the nature trail again at the end of the round
            System.out.println();
            displayNatureTrail();
            System.out.println();

            if (!isGameOver)
            {
                System.out.println("Press Enter to continue...");
                scanner.nextLine();
                round++;
            }
        }
        displayGameResult();
    }

    public boolean runPlayerTurn(Player player)
    {
        System.out.println(player.getName() + " is playing...");
        System.out.print("    ");
        System.out.println("Current position: " + 
            (player.getPosition() + 1));
        System.out.print("    ");
        int roll = rollDice(player);
        System.out.print("    ");
        movePlayerSpaces(player, roll);

        int trailFinish = natureTrail.getNatureTrail().length - 1;

        if (player.getPosition() >= trailFinish)
        {
            if (player.getPosition() == trailFinish)
            {
                System.out.print("    ");
                System.out.println("Current score: " + player.getScore());
                requestUpdateScore(player, 10);
                System.out.println();
                System.out.println(player.getName() 
                + " has reached the end of the trail " 
                + "and is awarded 10 points.");
            }
            return true;
        }

        checkAnimalSighting(player);
        int penalty = checkNatureFeature(player);

        if (penalty != 0)
        {
            movePlayerSpaces(player, penalty);
        }
                    
        System.out.print("    ");
        System.out.println("Current score: " + player.getScore());
        return false;
    }

    // Prompts user to select a trail type (easy or complex)
    public void selectTrail()
    {
        System.out.println("Select trail");
        System.out.println("============");
        System.out.println("1. Easy");
        System.out.println("2. Complex");

        Scanner scanner = new Scanner(System.in);
        String trailType;
        boolean isValid = false;

        while (isValid == false)
        {
            System.out.print("Choice: ");
            trailType = scanner.nextLine();

            if (trailType.trim().equals("1"))
            {
                isValid = true;
                requestEasyTrail();
            }
            else if (trailType.trim().equals("2"))
            {
                isValid = true;
                requestComplexTrail();
            }
            else
            {
                System.out.println("Error: Please choose option 1 or 2");
            }
        }

        System.out.println();
    }

    // Assigns a new value to the field dice
    public void setDice(Dice dice)
    {
        this.dice = dice;
    }

    // Assigns a new value to the field player1
    public void setPlayer1(Player player1)
    {
        this.player1 = player1;
    }

    // Assigns a new value to the field player2
    public void setPlayer2(Player player2)
    {
        this.player2 = player2;
    }

    // Assigns a new value to the field natureTrail
    public void setNatureTrail(Trail natureTrail)
    {
        this.natureTrail = natureTrail;
    }

    // Returns state of the game object in String form
    public String toString()
    {
        return "Player 1: " + player1 + " Player 2: " + player2 + " Dice: " 
            + dice + " Trail: " + natureTrail;
    }

}
