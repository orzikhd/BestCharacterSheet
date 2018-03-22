package BestCharacterSheet.Rules;
import java.util.List;
import java.util.ArrayList;

public class And implements Rule {
    private List<Rule> rList;
    public And() {
         rList = new ArrayList<Rule>();
    }

    public And(List<Rule> rList) {
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
    public Rule copy() {
        return new And(this.getList());
    }
}

