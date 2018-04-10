package BestCharacterSheet.Rules;

public class Feature implements Rule {
    public final Rule sub_rule;
    public final String name;

    public Feature(String name, Rule sub_rule) {
        this.sub_rule = sub_rule;
        this.name = name;
    }

    @Override
    public Rule execute() {
        Rule feature_body = sub_rule.execute();

        if (feature_body instanceof And) {
            And andRule = (And) feature_body;
            if (andRule.getList().size() == 1) {
                feature_body = andRule.getList().get(0);
            } else {
                throw new RuntimeException("Broke Feature invariant");
            }
        }

        if (feature_body instanceof Text) {
            // add (name, text) to adventurer as a new rule TODO

        } else if (feature_body instanceof Feature) {
            // do some crazy shit to combine the two TODO

        } else if (feature_body instanceof LimitUses) {
            // do some crazy adventurer/UI stuff with the number of uses
            // and the underlying feature text TODO

        } else if (feature_body instanceof PoolRule) {
            // do some crazy adventurer/UI stuff with the new pool based
            // feature and its pool and the underlying feature text TODO

        } else {
            throw new RuntimeException("Body of Feature cannot be a "
                    + feature_body.getClass().getSimpleName());
        }

        String final_feature_body = "";

        return new Feature(name, new Text(final_feature_body));
    }

    @Override
    public Rule copy() {
        return new Feature(name, sub_rule.copy());
    }
}
