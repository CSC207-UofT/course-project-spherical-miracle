package Domain.User.ConvertStrategies.HeightStrategies;

public class FtAndInStrategy implements HeightConverter{
    public double getM(double ftAndIn){
        return ftAndIn/3.281;
    }
}
