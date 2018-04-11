package BestCharacterSheet.Rules;

/**
 * A PoolRule represents a feature which is linked
 * to a Pool owned by the Adventurer
 */
public class PoolRule implements Rule {
    public final String poolName;
    public final Integer minUse;
    public final Rule subRule;

    /**
     * Creates a PoolRule
     * @param poolName name of the pool to link
     * @param minUse the minimum number of points from the pool that must be used for this feature
     * @param subRule the body of the feature which is triggered by using these points
     */
    public PoolRule(String poolName, Integer minUse, Rule subRule) {
        this.poolName = poolName;
        this.minUse = minUse;
        this.subRule = subRule;
    }

    /**
     * PoolRules do not have any execution behavior on their own,
     * and are intended to be used in its parent Feature's execution
     * @return a PoolRule (itself)
     */
    @Override
    public Rule execute() {
        return this;
    }

    @Override
    public Rule copy() {
        return new PoolRule(poolName, minUse, subRule);
    }
}
