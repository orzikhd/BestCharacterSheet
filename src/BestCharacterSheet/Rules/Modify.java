package BestCharacterSheet.Rules;

public class Modify implements Rule{
    private Rule x;
    private String attribute;
    public Modify(Rule x, String attribute) {
        this.x = x;
        this.attribute = attribute;
    }

    @Override
    public Rule copy() {
        return new Modify(this.x,this.attribute);
    }
}
