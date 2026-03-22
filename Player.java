/**
* Represents a player in the Animal Tracking Game,
* Each player has a name, position (on the trail) and score (in points)
* @ version 1.0
* @ author Rebecca Chalmers
*/

public class Player
{
    private String name;
    private int position;
    private int score;

    // Initialises the name, position, and score fields to default values
    public Player()
    {
        name = "Unknown";
        position = 0;
        score = 0;
    }

    // Initialises the name, position, and score fields to parameter values
    public Player(String name, int position, int score)
    {
        this.name = name;
        this.position = position;
        this.score = score;
    }

    // Returns the value of the field name
    public String getName()
    {
        return name;
    }

    // Returns the value of the field position
    public int getPosition()
    {
        return position;
    }

    // Returns the value of the field score
    public int getScore()
    {
        return score;
    }

    // Assigns a new value to the field name
    public void setName(String name)
    {
        this.name = name;
    }

    // Assigns a new value to the field position
    public void setPosition(int position)
    {
        this.position = position;
    }

    // Assigns a new value to the field score
    public void setScore(int score)
    {
        this.score = score;
    }

    // Returns state of the player object in String form
    public String toString()
    {
        return "Player Name: " + name + ", Position: " + position 
            + ", Score: " + score;
    }

}
