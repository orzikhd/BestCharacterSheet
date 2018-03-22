package BestCharacterSheet.Rules;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents an exclusive OR choice of x
 * rules in a list of options
 */
public class Choice implements Rule {
    private int x;
    private List<Rule> rList;

    public Choice(){
        rList = new ArrayList<Rule>();
    };

    public Choice(int x, List<Rule> rList) {
        this();
        this.x = x;
        this.rList = rList;
    }

    public void addRule(Rule r) {
        rList.add(r);
    }

    public List<Rule> getList() {
        List<Rule> newList = new ArrayList<Rule>();
        for(Rule r : rList) {
            newList.add(r.copy());
        }
        return newList;
    }

    @Override
    public Choice copy() {
        return new Choice(this.x,this.getList());
    }
}
