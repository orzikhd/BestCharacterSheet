package BestCharacterSheet.Rules;

public class OnUse implements Rule {
    private final Rule inner_rule;

    public OnUse(Rule inner_rule) {
        this.inner_rule = inner_rule;
    }

    public Rule use() {
        // use the inner rule when triggered during play
        // TODO based on inner rule

        return null;
    }

    @Override
    public Rule execute() {
        return this;
    }

    @Override
    public Rule copy() {
        return new OnUse(inner_rule);
    }
}
