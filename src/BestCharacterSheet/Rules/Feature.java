package BestCharacterSheet.Rules;

public class Feature implements Rule{
    public final Rule sub_rule;
    public final String name;

    public Feature(Rule sub_rule, String name) {
        this.sub_rule = sub_rule;
        this.name = name;
    }

    @Override
    public Rule execute() {
        if (sub_rule instanceof Text) {
            // add (name, text) to adventurer as a new rule TODO

        } else if (sub_rule instanceof And) {
            And andRule = (And) sub_rule;
            if (andRule.getList().size() != 1) {
                throw new RuntimeException("Broke Feature invariant");
            }

            Rule child = andRule.getList().get(0);
            if (child instanceof Text) {
                // add (name, text) to adventurer as a new rule TODO
            } else if (child instanceof Feature) {
                // do some crazy shit to combine the two TODO
            } else {
                throw new RuntimeException("Broke Feature invariant");
            }

        } else if (sub_rule instanceof LimitUses) {
            // do some crazy adventurer/UI stuff with the number of uses
            // and the underlying feature text TODO

        } else if (sub_rule instanceof PoolRule) {
            // do some crazy adventurer/UI stuff with the new pool based
            // feature and its pool and the underlying feature text TODO

        } else {
            throw new RuntimeException("sub_rule of Feature cannot be a "
                    + sub_rule.getClass().getSimpleName());
        }

        return null;
    }

    @Override
    public Rule copy() {
        return new Feature(this.sub_rule,this.name);
    }
}
