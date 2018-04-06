package BestCharacterSheet.Rules;
import java.util.List;
import java.util.ArrayList;

public class And implements Rule {
    private final List<Rule> values;

    public And(List<Rule> values) {
        this.values = values;
    }

    public List<Rule> getList() {
        List<Rule> newList = new ArrayList<Rule>();
        for(Rule r : values) {
            newList.add(r.copy());
        }
        return newList;
    }

    @Override
    public Rule execute() {
        List<Rule> results = new ArrayList<>();
        for (Rule rule : values) {
            Rule res = rule.execute();
            if (res != null) {
                results.add(rule.execute());
            }
        }

        return new And(results);
    }

    @Override
    public Rule copy() {
        return new And(this.getList());
    }
}

