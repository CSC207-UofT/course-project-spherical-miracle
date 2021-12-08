package Domain.User.UseCase;

import Domain.User.Boundary.UserOutputBoundary;
import Domain.User.ConvertStrategies.HeightStrategies.CmStrategy;
import Domain.User.ConvertStrategies.HeightStrategies.FtAndInStrategy;
import Domain.User.ConvertStrategies.HeightStrategies.HeightConverter;
import Domain.User.ConvertStrategies.WeightStrategies.LbsStrategy;
import Domain.User.ConvertStrategies.WeightStrategies.WeightConverter;
import Database.UserDataAccess;

import java.util.List;

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
        UserDataAccess.BodyMeasurementRecord bodyMeasurementRecord = databaseInterface.getHeightsWeightsFor(username);
        List<Double> heights = bodyMeasurementRecord.getHeights();
        List<Double> weights = bodyMeasurementRecord.getWeights();
        if (height == -1 && weight == -1 && heights.size() != 0 && weights.size() != 0){
            databaseInterface.addHeightWeight(username, heights.get(heights.size() - 1), weights.get(weights.size() - 1));
        } else if (height == -1 && heights.size() != 0){
            databaseInterface.addHeightWeight(username, heights.get(heights.size() - 1), weightConverter(weight, units[1]));
        } else if (weight == -1 && weights.size() != 0){
            databaseInterface.addHeightWeight(username, heightConverter(height,units[0]), weights.get(weights.size() - 1));
        } else {
            databaseInterface.addHeightWeight(username, heightConverter(height,units[0]), weightConverter(weight, units[1]));
        }
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
