package BestCharacterSheet.Rules;

import java.util.ArrayList;
import java.util.List;

/**
 * The WeaponProficiency Rule represents giving the Adventurer proficiency
 * in whatever set of weapons it is constructed with.
 */
public class WeaponProficiency extends Proficiency {
    private final List<String> values;

    /**
     * Creates a WeaponProficiency Rule
     * @param name The name of this set of weapon proficiencies, for view purposes
     * @param values All of the weapons that the Adventurer is gaining proficiency in
     */
    public WeaponProficiency(String name, List<String> values) {
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
        // add proficiencies to adventurer's weapon proficiencies TODO
        return null;
    }

    @Override
    public Rule copy() {
        return new ArmorProficiency(name, values);
    }
}

