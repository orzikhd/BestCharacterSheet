package BestCharacterSheet.Rules;

import BestCharacterSheet.Util;

public class GetAbilityModifier implements Rule {
    public final Util.Ability ability;

    public GetAbilityModifier(String ability) {
        try {
            this.ability = Util.Ability.valueOf(ability);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Not an ability: " + ability);
        }
    }

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
