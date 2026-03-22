/**
* Holds the dice object used in the Animal Tracking game
* The dice object is used to change the position of players on the trail
* @ version 1.0
* @ author Rebecca Chalmers
*/

public class Dice
{
    private int numberOfSides;

    // Initialises the numberOfSides field to its default value
    public Dice()
    {
        numberOfSides = 0;
    }

    // Initialises the numberOfSides field to a parameter value
    public Dice(int numberOfSides)
    {
        this.numberOfSides = numberOfSides;
    }

    public int generateDiceRoll(int numberOfSides)
    {
        return (int) (Math.random() * numberOfSides + 1);
    }

    // Returns the value of the field numberOfSides
    public int getNumberOfSides()
    {
        return numberOfSides;
    }

    // Assigns a new value to the field numberOfSides
    public int setNumberOfSides(int numberOfSides)
    {
        this.numberOfSides = numberOfSides;
        return numberOfSides;
    }

    // Returns state of the dice object in String form
    public String toString()
    {
        return "The die has " + numberOfSides + " sides";
    }

}
