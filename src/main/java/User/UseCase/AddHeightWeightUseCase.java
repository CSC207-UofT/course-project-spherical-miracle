package User.UseCase;

import User.Boundary.UserOutputBoundary;
import User.ConvertStrategies.HeightStrategies.CmStrategy;
import User.ConvertStrategies.HeightStrategies.FtAndInStrategy;
import User.ConvertStrategies.HeightStrategies.HeightConverter;
import User.ConvertStrategies.WeightStrategies.LbsStrategy;
import User.ConvertStrategies.WeightStrategies.WeightConverter;
import Database.UserDataAccess;

public class AddHeightWeightUseCase {

    private final UserDataAccess databaseInterface;
    private final UserOutputBoundary outputBoundary;

    public AddHeightWeightUseCase(UserDataAccess databaseInterface, UserOutputBoundary outputBoundary){
        this.databaseInterface = databaseInterface;
        this.outputBoundary = outputBoundary;
    }

    public void addHeightWeight(String username){
        String[] units  = outputBoundary.askUnitType();
        double height = outputBoundary.askMeasurements("height");
        double weight = outputBoundary.askMeasurements("weight");
        databaseInterface.addHeightWeight(username, heightConverter(height,units[0]), weightConverter(weight, units[1]));
    }

    private double heightConverter(double height, String unit){
        HeightConverter heightConverter = null;
        if (unit.equalsIgnoreCase("cm")){
            heightConverter = new CmStrategy();
        } else if (unit.equalsIgnoreCase("f")) {
            heightConverter = new FtAndInStrategy();
        }
        assert heightConverter != null;
        return heightConverter.getM(height);
    }

    private double weightConverter(double weight, String unit){
        WeightConverter weightConverter = null;
        if (unit.equalsIgnoreCase("lbs")){
            weightConverter = new LbsStrategy();
        }
        if (weightConverter != null) {
            return weightConverter.getKgs(weight);
        }else {
            return weight;
        }
    }
 }
