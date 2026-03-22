/**
* Holds the natureFeature object which represents a nature feature that can
* appear on the trail (Creek, Bridge, Fallen Tree, Landslide),
* which involves each feature's associated letter, name, and space penalty
* @ version 1.0
* @ author Rebecca Chalmers
*/

public class NatureFeature
{
    private char featureType;
    private String featureName;
    private int spacePenalty;

    /*
    Initialises the featureType, featureName, and spacePenalty fields
    to default values
    */
    public NatureFeature()
    {
        featureType = '_';
        featureName = "";
        spacePenalty = 0;
    }

    /*
    Initialises the featureType, featureName, and spacePenalty fields
    to parameter values
    */
    public NatureFeature
        (char featureType, String featureName, int spacePenalty)
    {
        this.featureType = featureType;
        this.featureName = featureName;
        this.spacePenalty = spacePenalty;
    }

    // Returns the value of the field featureName
    public String getFeatureName()
    {
        return featureName;
    }

    // Returns the value of the field featureType
    public char getFeatureType()
    {
        return featureType;
    }

    // Returns the value of the field spacePenalty
    public int getSpacePenalty()
    {
        return spacePenalty;
    }

    // Assigns a new value to the field featureName
    public void setFeatureName(String featureName)
    {
        this.featureName = featureName;
    }

    // Assigns a new value to the field featureType
    public void setFeatureType(char featureType)
    {
        this.featureType = featureType;
    }

    // Assigns a new value to the field spacePenalty
    public void setSpacePenalty(int spacePenalty)
    {
        this.spacePenalty = spacePenalty;
    }

    // Returns state of the natureFeature object in String form
    public String toString()
    {
        return "Feature Type: " + featureType 
        + " Feature Name: " + featureName 
        + " Space Penalty: " + spacePenalty;
    }

}
