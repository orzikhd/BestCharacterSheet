package BestCharacterSheet.Rules;

import BestCharacterSheet.Util;

/**
 * The GetAbilityModifier Rule represents requiring an
 * ability modifier value from the Adventurer for some other rule.
 */
public class GetAbilityModifier implements Rule {
    public final Util.Ability ability;

    /**
     * Creates a new GetAbilityModifier Rule
     * @param ability the ability to get the modifier of
     */
    public GetAbilityModifier(String ability) {
        try {
            this.ability = Util.Ability.valueOf(ability);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Not an ability: " + ability);
        }
    }

    /**
     * Triggers fetching the modifier from the Adventurer
     * @return The modifier as an Int
     */
    @Override
    public Rule execute() {
        // get the associated ability modifier under the adventurer TODO
        return new Int(0);
    }

    @Override
    public Rule copy() {
        return new GetAbilityModifier(this.ability.name());
    }
}
