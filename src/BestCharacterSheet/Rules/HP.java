package BestCharacterSheet.Rules;

/**
 * The HP Rule represents altering the total/max HP of the Adventurer
 * Note: HP Rules will deal with the CONSTITUTION modifier themselves
 */
public class HP implements Rule {
    public final Integer defaultVal;
    public final Integer dieSize;

    /**
     * Creates an HP Rule when rolling for HP is an option
     * @param defaultVal The default value if the user doesn't want to roll for HP
     * @param dieSize The type of Die to use if the user wants to roll for HP
     */
    public HP(Integer defaultVal, Integer dieSize) {
        this.defaultVal = defaultVal;
        this.dieSize = dieSize;
    }

    /**
     * Creates an HP Rule when rolling for HP is not an option
     * @param defaultVal The default value for HP gained
     */
    public HP(Integer defaultVal) {
        this(defaultVal, 0);
    }

    /**
     * Triggers a user input event to use either the default value or
     * an input value, and then adjusts the Adventurer's HP accordingly.
     *
     * While this can affect the Model or View, this Rule has nothing to return
     * and no purpose in being propagated.
     * @return null
     */
    @Override
    public Rule execute() {
        // put option of defaultValue or die roll into View
        // and update the Adventurer TODO
        // - if dieSize is 0 then no need to make choice
        // - knows to add CON modifier
        return null;
    }

    @Override
    public Rule copy() {
        return new HP(defaultVal, dieSize);
    }
}
