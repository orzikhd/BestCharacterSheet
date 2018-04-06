package BestCharacterSheet.Rules;

public class HP implements Rule {
    public final Integer defaultVal;
    public final Integer dieSize;

    public HP(Integer defaultVal, Integer dieSize) {
        this.defaultVal = defaultVal;
        this.dieSize = dieSize;
    }

    public HP(Integer defaultVal) {
        this(defaultVal, 0);
    }

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
