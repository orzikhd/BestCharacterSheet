package BestCharacterSheet.Rules;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents an exclusive OR choice of num
 * rules in a list of options
 */
public class Choice implements Rule {
    public final Integer num;
    private List<Rule> values;

    public Choice(Integer num, List<Rule> values) {
        this.num = num;
        this.values = values;
    }

    public List<Rule> getList() {
        List<Rule> newList = new ArrayList<>();
        for(Rule r : values) {
            newList.add(r.copy());
        }
        return newList;
    }

    @Override
    public Rule execute() {
        // trigger View input event with num choices out of the values
        // TODO
        List<Rule> choices = new ArrayList<>();
        return new And(choices);
    }

    @Override
    public Choice copy() {
        return new Choice(this.num,this.getList());
    }
}
