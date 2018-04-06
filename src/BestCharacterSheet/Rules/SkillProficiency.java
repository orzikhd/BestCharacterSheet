package BestCharacterSheet.Rules;
import BestCharacterSheet.Util.Skill;

public class SkillProficiency extends Proficiency {
    public final boolean expertise;

    public SkillProficiency(String name, boolean expertise) {
        super(name);
        this.expertise = expertise;

        try {
            Skill.valueOf(name); // check if this is a valid skill
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Not a skill: " + name);
        }
    }

    @Override
    public Rule execute() {
        // adds proficiency in this skill to the adventurer TODO
        return null;
    }

    @Override
    public Rule copy() {
        return new SkillProficiency(name, expertise);
    }
}
