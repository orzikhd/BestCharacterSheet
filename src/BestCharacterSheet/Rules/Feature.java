package BestCharacterSheet.Rules;

/**
 * The Feature Rule represents any feature that is granted to an Adventurer.
 */
public class Feature implements Rule {
    public final Rule sub_rule;
    public final String name;

    /**
     * Creates a new Feature Rule
     * @param name Name of the feature
     * @param subRule Body of the feature
     */
    public Feature(String name, Rule subRule) {
        this.sub_rule = subRule;
        this.name = name;
    }

    /**
     * Depending on the type of the sub_rule, trigger events or
     * combine information until the feature's body is distilled into text form.
     * @return The final, Text sub_rule form of the feature
     */
    @Override
    public Rule execute() {
        Rule featureBody = sub_rule.execute();

        if (featureBody instanceof And) {
            And andRule = (And) featureBody;
            if (andRule.getList().size() == 1) {
                featureBody = andRule.getList().get(0);
            } else {
                throw new RuntimeException("Broke Feature invariant");
            }
        }

        if (featureBody instanceof Text) {
            // add (name, text) to adventurer as a new rule TODO

        } else if (featureBody instanceof Feature) {
            // do some crazy shit to combine the two TODO

        } else if (featureBody instanceof LimitUses) {
            // do some crazy adventurer/UI stuff with the number of uses
            // and the underlying feature text TODO

        } else if (featureBody instanceof PoolRule) {
            // do some crazy adventurer/UI stuff with the new pool based
            // feature and its pool and the underlying feature text TODO

        } else {
            throw new RuntimeException("Body of Feature cannot be a "
                    + featureBody.getClass().getSimpleName());
        }

        String final_feature_body = "";

        return new Feature(name, new Text(final_feature_body));
    }

    @Override
    public Rule copy() {
        return new Feature(name, sub_rule.copy());
    }
}
