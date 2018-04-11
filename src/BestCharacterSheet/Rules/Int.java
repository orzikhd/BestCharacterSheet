package BestCharacterSheet.Rules;

/**
 * An Int Rule represents an Integer being used in some other Rule.
 */
public class Int implements Rule{
    public final Integer num;

    /**
     * Creates an Int Rule
     * @param num the Integer to wrap with this Int Rule
     */
    public Int(Integer num){
        this.num = num;
    }

    /**
     * Int Rules do not have any sort of execution behavior
     * so they return themselves
     * @return an Int Rule (itself)
     */
    @Override
    public Rule execute() {
        return this;
    }

    @Override
    public Rule copy() {
        return new Int(this.num);
    }
}
