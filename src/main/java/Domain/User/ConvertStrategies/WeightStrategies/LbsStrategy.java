package Domain.User.ConvertStrategies.WeightStrategies;

/**
 * A weight converting strategy for converting pounds to kilograms.
 */
public class LbsStrategy implements WeightConverter{
    /**
     * Returns the kgs equivalent of lbs input
     * @param lbs the weight (in lbs)
     * @return lbs argument in kgs
     */
    public double getKgs(double lbs){
        return lbs/2.205;
    }
}
