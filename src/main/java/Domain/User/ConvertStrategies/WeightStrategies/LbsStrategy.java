package Domain.User.ConvertStrategies.WeightStrategies;

public class LbsStrategy implements WeightConverter{
    public double getKgs(double lbs){
        return lbs/2.205;
    }
}
