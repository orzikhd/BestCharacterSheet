package BestCharacterSheet.Rules;

public class Feature implements Rule{
    public Rule sub_rule;
    public String name;
    public Feature(Rule sub_rule, String name) {
        this.sub_rule = sub_rule;
        this.name = name;
    }

    @Override
    public Rule copy() {
        return new Feature(this.sub_rule,this.name);
    }
}
