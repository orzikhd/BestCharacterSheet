package BestCharacterSheet.Rules;

import BestCharacterSheet.Util.Rest;

public class Pool implements Rule {
    public final String name;
    public final Integer size;
    public final Rest rest;
    public final Integer numBack;

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
