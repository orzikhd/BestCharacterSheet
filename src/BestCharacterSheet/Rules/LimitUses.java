package BestCharacterSheet.Rules;

import BestCharacterSheet.Util.Rest;

/**
 * A LimitUses Rule represents placing a limitation on the amount of uses
 * an Adventurer has for a particular feature.
 */
public class LimitUses implements Rule {

    public final Integer numUses;
    public final Rest rest;
    public final Integer numBack;
    public final Text text;

    /**
     * Creates a LimitUses Rule
     * @param numUses number of uses between rests
     * @param rest the type of rest that resets the number of uses
     * @param numBack the number of uses the Adventurer gets back on the rest
     * @param text the Text body of the feature being limited
     */
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

    /**
     * LimitUses Rules do not have any execution behavior on their own,
     * and are intended to be used in its parent Feature's execution
     * @return a LimitUses Rule (itself)
     */
    @Override
    public Rule execute() {
        return this;
    }

    @Override
    public Rule copy() {
        return new LimitUses(numUses, rest.name(), numBack, text);
    }
}
