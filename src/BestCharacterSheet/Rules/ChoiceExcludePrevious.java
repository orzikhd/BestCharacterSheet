package BestCharacterSheet.Rules;

import java.util.ArrayList;
import java.util.List;

public class ChoiceExcludePrevious extends Choice {
    public final String featureName;

    /**
     * Given the name of the feature and the options for that feature, remove the options that the adventurer
     * had already chosen
     * @param featureName feature to get the previous chosen options from
     * @param values all the options available for this feature
     * @return the options that are still available after removing the previous choices
     */
    private static List<Rule> excludePrevious(String featureName, List<Rule> values) {
        // do some stuff to get previous value(s) and return a new list TODO
        return new ArrayList<>();
    }

    /**
     *
     * @param featureName name of the feature
     * @param num number of choices to make
     * @param values all the choices possible for this feature
     */
    public ChoiceExcludePrevious(String featureName, Integer num, List<Rule> values) {
        super(num, excludePrevious(featureName, values));
        this.featureName = featureName;
    }

    @Override
    public Choice copy() {
        return new ChoiceExcludePrevious(featureName, num, this.getList());
    }
}
