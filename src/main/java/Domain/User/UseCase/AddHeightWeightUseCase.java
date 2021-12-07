package Domain.User.UseCase;

import Domain.User.Boundary.UserOutputBoundary;
import Domain.User.ConvertStrategies.HeightStrategies.CmStrategy;
import Domain.User.ConvertStrategies.HeightStrategies.FtAndInStrategy;
import Domain.User.ConvertStrategies.HeightStrategies.HeightConverter;
import Domain.User.ConvertStrategies.WeightStrategies.LbsStrategy;
import Domain.User.ConvertStrategies.WeightStrategies.WeightConverter;
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
