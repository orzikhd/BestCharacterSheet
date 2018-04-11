package BestCharacterSheet.Rules;

import BestCharacterSheet.Util.Rest;

/**
 * A Pool represents a pool of points available for the Adventurer
 * to draw from for any PoolRules linked to this Pool
 */
public class Pool implements Rule {
    public final String name;
    public final Integer size;
    public final Rest rest;
    public final Integer numBack;

    /**
     * Creates a Pool
     * @param name name of this pool
     * @param size size of this pool, or the number of points available
     * @param rest the type of rest that resets the number of uses
     * @param numBack the number of uses the Adventurer gets back on the rest
     */
    public Pool(String name, Integer size, String rest, Integer numBack) {
        this.name = name;
        this.size = size;

        try {
            this.rest = Rest.valueOf(rest);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Not a rest type: " + rest);
        }

        this.numBack = numBack;
    }

    /**
     * Gives the Pool to the Adventurer.
     *
     * While this can affect the Model or View, this Rule has nothing to return
     * and no purpose in being propagated.
     * @return
     */
    @Override
    public Rule execute() {
        // give this pool to the adventurer TODO
        return null;
    }

    @Override
    public Rule copy() {
        return new Pool(name, size, rest.name(), numBack);
    }
}
