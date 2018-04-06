package BestCharacterSheet.Rules;

public class Proficiency implements Rule {
    public final String name;

    public Proficiency(String name) {
        this.name = name;
    }

    @Override
    public Rule execute() {
        // add proficiency to list of rando proficiencies TODO
        return null;
    }

    @Override
    public Rule copy() {
        return new Proficiency(name);
    }
}
