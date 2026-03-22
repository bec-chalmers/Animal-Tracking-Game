/**
* Represents possible nature trails for the Animal Tracking Game
* @ version 1.0
* @ author Rebecca Chalmers
*/

public class Trail
{
    private NatureFeature[] natureTrail;

    // Initialises the natureTrail field to its default value
    public Trail()
    {
        natureTrail = new NatureFeature[0];
    }

    // Initialises the natureTrail field to a parameter value
    public Trail(NatureFeature[] natureTrail)
    {
        this.natureTrail = natureTrail;
    }

    // Returns the value of the field natureTrail
    public NatureFeature[] getNatureTrail()
    {
        return natureTrail;
    }

    // Assigns a new value to the field natureTrail
    public void setNatureTrail(NatureFeature[] natureTrail)
    {
        this.natureTrail = natureTrail;
    }

    // Returns state of the trail object in String form
    public String toString()
    {
        return "Trail Length: " + natureTrail.length;
    }

}
