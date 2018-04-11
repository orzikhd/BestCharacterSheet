package BestCharacterSheet.Rules;
import java.util.List;
import java.util.ArrayList;

/**
 * The Choice Rule represents an exclusive OR choice of num
 * rules in a list of options
 */
public class Choice implements Rule {
    public final Integer num;
    private List<Rule> values;

    /**
     * Creates a new Choice Rule
     * @param num The number of choices to select from the values
     * @param values The values are the options available to choose from
     */
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

    /**
     * Triggers an event where the user can choose num options from
     * the given options
     * @return Propagates the choices the user made in the form of an And Rule
     */
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
