package BestCharacterSheet.Rules;

import java.util.ArrayList;
import java.util.List;

/**
 * The ArmorProficiency Rule represents giving the Adventurer proficiency
 * in whatever set of armors it is constructed with.
 */
public class ArmorProficiency extends Proficiency {
    private final List<String> values;

    /**
     * Creates a ArmorProficiency Rule
     * @param name The name of this set of armor proficiencies, for view purposes
     * @param values All of the armors that the Adventurer is gaining proficiency in
     */
    public ArmorProficiency(String name, List<String> values) {
        super(name);
        this.values = values;
    }

    public List<String> getList() {
        List<String> newList = new ArrayList<>();
        newList.addAll(values);
        return newList;
    }

    /**
     * Triggers an event where the proficiency is granted to the Adventurer
     *
     * While this can affect the Model or View, this Rule has nothing to return
     * and no purpose in being propagated.
     * @return null
     */
    @Override
    public Rule execute() {
        // add proficiencies to adventurer's armor proficiencies TODO
        return null;
    }

    @Override
    public Rule copy() {
        return new ArmorProficiency(name, values);
    }
}
