package Domain.User.ConvertStrategies.HeightStrategies;

public class CmStrategy implements HeightConverter{
    public double getM(double cm){
        return cm/100;
    }
}
