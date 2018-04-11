package BestCharacterSheet.Rules;
import java.util.List;
import java.util.ArrayList;

/**
 * The And Rule represents all of its child rules being executed together.
 */
public class And implements Rule {
    private final List<Rule> values;

    /**
     * Creates an And Rule
     * @param values child rules that are all to be executed together
     */
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

    /**
     * Returns a new And Rule containing the non-null results of executing
     * all of its child rules
     * @return list of post-execution rules
     */
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

