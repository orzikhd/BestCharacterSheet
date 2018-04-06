package BestCharacterSheet.Rules;

import BestCharacterSheet.Util.Ability;

public class Modify implements Rule {
    public final Integer num;
    public final Ability ability;

    public Modify(Integer num, String ability) {
        this.num = num;

        try {
            this.ability = Ability.valueOf(ability);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Not an ability: " + ability);
        }
    }

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
