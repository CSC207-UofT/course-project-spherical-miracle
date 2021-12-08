package Domain.User.ConvertStrategies.WeightStrategies;

/**
 * Interface for all weight conversion to kilograms strategies
 */

public interface WeightConverter {
    /**
     * Returns the kilograms equivalent of weight input
     */
    double getKgs(double weight);
}
