package Domain.User.ConvertStrategies.HeightStrategies;

/**
 * A height converting strategy for converting feet and inches to meters.
 */
public class FtAndInStrategy implements HeightConverter{
    /**
     * Returns the meters equivalent of feet and inches input
     * @param ftAndIn the height (in feet and inches)
     * @return ftAndIn argument in meters
     */
    public double getM(double ftAndIn){
        return ftAndIn/3.281;
    }
}
