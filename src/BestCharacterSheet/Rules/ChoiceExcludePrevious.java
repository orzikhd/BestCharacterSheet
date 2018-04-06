package BestCharacterSheet.Rules;

import java.util.ArrayList;
import java.util.List;

public class ChoiceExcludePrevious extends Choice {
    public final String featureName;

    private static List<Rule> excludePrevious(String featureName, List<Rule> values) {
        // do some stuff to get previous value(s) and return a new list TODO
        return new ArrayList<>();
    }

    public ChoiceExcludePrevious(String featureName, Integer num, List<Rule> values) {
        super(num, excludePrevious(featureName, values));
        this.featureName = featureName;
    }

    @Override
    public Choice copy() {
        return new ChoiceExcludePrevious(featureName, num, this.getList());
    }
}
