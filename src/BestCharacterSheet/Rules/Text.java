package BestCharacterSheet.Rules;

/**
 * Represents Text to be displayed as a caste feature
 */
public class Text implements Rule{
    private String text;
    public Text(String text){
        this.text = text;
    }

    @Override
    public Rule copy() {
        return new Text(this.text);
    }
}
