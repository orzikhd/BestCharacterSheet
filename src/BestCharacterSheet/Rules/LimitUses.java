package BestCharacterSheet.Rules;

import BestCharacterSheet.Util.Rest;

public class LimitUses implements Rule {

    public final Integer numUses;
    public final Rest rest;
    public final Integer numBack;
    public final Text text;

    public LimitUses(Integer numUses, String rest, Integer numBack, Text text) {
        this.numUses = numUses;
        try {
            this.rest = Rest.valueOf(rest);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Not a rest type: " + rest);
        }
        this.numBack = numBack;
        this.text = text;
    }

    @Override
    public Rule execute() {
        return this;
    }

    @Override
    public Rule copy() {
        return new LimitUses(numUses, rest.name(), numBack, text);
    }
}
