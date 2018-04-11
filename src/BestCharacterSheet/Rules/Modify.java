package BestCharacterSheet.Rules;

import BestCharacterSheet.Util.Ability;

/**
 * A Modify Rule represents modifying an ability score of an Adventurer
 */
public class Modify implements Rule {
    public final Integer num;
    public final Ability ability;

    /**
     * Creates a Modify Rule
     * @param num the number to add to the existing ability score
     * @param ability the ability to get the modifier of
     */
    public Modify(Integer num, String ability) {
        this.num = num;

        try {
            this.ability = Ability.valueOf(ability);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Not an ability: " + ability);
        }
    }

    /**
     * Triggers an event to modify the ability score of the Adventurer
     *
     * While this can affect the Model or View, this Rule has nothing to return
     * and no purpose in being propagated.
     * @return null
     */
    @Override
    public Rule execute() {
        // modify the associated ability under the adventurer TODO
        return null;
    }

    @Override
    public Rule copy() {
        return new Modify(this.num,this.ability.name());
    }
}
