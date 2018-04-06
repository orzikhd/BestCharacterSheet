package BestCharacterSheet.Rules;

import java.util.ArrayList;
import java.util.List;

public class WeaponProficiency extends Proficiency {
    private final List<String> values;

    public WeaponProficiency(String name, List<String> values) {
        super(name);
        this.values = values;
    }

    public List<String> getList() {
        List<String> newList = new ArrayList<>();
        newList.addAll(values);
        return newList;
    }

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

