package BestCharacterSheet.Rules;

/**
 * Represents Integer to be used in a caste feature
 */
public class Int implements Rule{
    public final Integer num;
    public Int(Integer num){
        this.num = num;
    }

    @Override
    public Rule execute() {
        return this;
    }

    @Override
    public Rule copy() {
        return new Int(this.num);
    }
}
