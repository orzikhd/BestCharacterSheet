package BestCharacterSheet.Rules;

/**
 * The Proficiency Rule represents giving an Adventurer a proficiency
 * Ex: Proficiency(“The Doodle Gab instrument”)
 */
public class Proficiency implements Rule {
    public final String name;

    /**
     * Creates a Proficiency Rule
     * @param name The name of whatever the Adventurer is getting proficiency in
     */
    public Proficiency(String name) {
        this.name = name;
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
        // add proficiency to list of rando proficiencies TODO
        return null;
    }

    @Override
    public Rule copy() {
        return new Proficiency(name);
    }
}
