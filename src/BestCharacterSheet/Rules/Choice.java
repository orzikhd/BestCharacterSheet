package BestCharacterSheet.Rules;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents an exclusive OR choice of num
 * rules in a list of options
 */
public class Choice implements Rule {
    public int num;
    private List<Rule> values;

    public Choice(){
        values = new ArrayList<Rule>();
    }

    public Choice(int num, List<Rule> values) {
        this();
        this.num = num;
        this.values = values;
    }

    public void addRule(Rule r) {
        values.add(r);
    }

    public List<Rule> getList() {
        List<Rule> newList = new ArrayList<Rule>();
        for(Rule r : values) {
            newList.add(r.copy());
        }
        return newList;
    }

    @Override
    public Choice copy() {
        return new Choice(this.num,this.getList());
    }
}
