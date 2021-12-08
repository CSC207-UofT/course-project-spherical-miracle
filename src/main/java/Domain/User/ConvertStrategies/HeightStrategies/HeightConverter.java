package Domain.User.ConvertStrategies.HeightStrategies;

/**
 * Interface for all height conversion to meters strategies
 */

public interface HeightConverter {
    /**
     * Returns the meters equivalent of height input
     */
    double getM(double height);
}
