package BestCharacterSheet.Rules;
import BestCharacterSheet.Util.Skill;

/**
 * The SkillProficiency Rule represents giving the Adventurer proficiency
 * in whatever skill it is constructed with.
 */
public class SkillProficiency extends Proficiency {
    public final boolean expertise;

    /**
     * Creates a SkillProficiency Rule
     * @param name The name of the skill
     * @param expertise True if the Adventurer has expertise in this skill
     */
    public SkillProficiency(String name, boolean expertise) {
        super(name);
        this.expertise = expertise;

        try {
            Skill.valueOf(name); // check if this is a valid skill
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Not a skill: " + name);
        }
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
        // adds proficiency in this skill to the adventurer TODO
        return null;
    }

    @Override
    public Rule copy() {
        return new SkillProficiency(name, expertise);
    }
}
