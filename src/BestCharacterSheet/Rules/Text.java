package BestCharacterSheet.Rules;

/**
 * Represents Text to be displayed as the body of a feature
 */
public class Text implements Rule{
    public final String text;

    /**
     * Creates a Text Rule
     * @param text the String to wrap with this Text Rule
     */
    public Text(String text){
        this.text = text;
    }

    /**
     * Text Rules do not have any sort of execution behavior
     * so they return themselves
     * @return a Text Rule (itself)
     */
    @Override
    public Rule execute() {
        return this;
    }

    @Override
    public Rule copy() {
        return new Text(this.text);
    }
}
