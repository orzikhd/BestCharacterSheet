package BestCharacterSheet.Rules;

public class PoolRule implements Rule {
    public final String poolName;
    public final Integer minUse;
    public final Rule subRule;

    public PoolRule(String poolName, Integer minUse, Rule subRule) {
        this.poolName = poolName;
        this.minUse = minUse;
        this.subRule = subRule;
    }

    @Override
    public Rule execute() {
        return this;
    }

    @Override
    public Rule copy() {
        return new PoolRule(poolName, minUse, subRule);
    }
}
