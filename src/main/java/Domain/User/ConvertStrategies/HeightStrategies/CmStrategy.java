package Domain.User.ConvertStrategies.HeightStrategies;

/**
 * A height converting strategy for converting centimeters to meters.
 */

public class CmStrategy implements HeightConverter{
    /**
     * Returns the meters equivalent of centimeters input
     * @param cm the height (in cm)
     * @return cm argument in meters
     */
    public double getM(double cm){
        return cm/100;
    }
}
