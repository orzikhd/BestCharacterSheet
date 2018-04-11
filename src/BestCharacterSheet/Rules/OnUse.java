package BestCharacterSheet.Rules;

/**
 * The OnUse Rule represents delaying the execution of a subrule
 * until a later time.
 */
public class OnUse implements Rule {
    private final Rule subRule;

    /**
     * Creates an OnUse Rule
     * @param subRule The rule whose execution to delay
     */
    public OnUse(Rule subRule) {
        this.subRule = subRule;
    }

    /**
     * Triggers execution of the subrule
     * @return TODO
     */
    public Rule use() {
        // use the inner rule when triggered during play
        // TODO based on inner rule

        return null;
    }

    /**
     * OnUse Rules do not have any sort of execution behavior
     * so they return themselves
     * @return an OnUse Rule (itself)
     */
    @Override
    public Rule execute() {
        return this;
    }

    @Override
    public Rule copy() {
        return new OnUse(subRule);
    }
}
