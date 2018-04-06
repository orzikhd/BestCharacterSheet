package BestCharacterSheet;

public class Util {

    private static final boolean dev_mode = false;

    /**
     * Print function that prints when dev_mode is true.
     * @param toPrint string to print
     */
    public static void print(String toPrint) {
        if (dev_mode) {
            System.out.println(toPrint);
        }
    }

    /**
     * Enum representing Abilities
     */
    public enum Ability {
        STRENGTH ("Strength"),
        DEXTERITY ("Dexterity"),
        CONSTITUTION ("Constitution"),
        INTELLIGENCE ("Intelligence"),
        WISDOM ("Wisdom"),
        CHARISMA ("Charisma");

        public final String ability;

        Ability(String ability) {
            this.ability = ability;
        }
    }

    /**
     * Enum representing Skills
     */
    public enum Skill {
        ACROBATICS ("Acrobatics", Ability.DEXTERITY)
        , ANIMAL_HANDLING ("Animal Handling", Ability.WISDOM)
        , ARCANA ("Arcana", Ability.INTELLIGENCE)
        , ATHLETICS ("Athletics", Ability.STRENGTH)
        , DECEPTION ("Deception", Ability.CHARISMA)
        , HISTORY ("History", Ability.INTELLIGENCE)
        , INSIGHT ("Insight", Ability.WISDOM)
        , INTIMIDATION ("Intimidation", Ability.CHARISMA)
        , INVESTIGATION ("Investigation", Ability.INTELLIGENCE)
        , MEDICINE ("Medicine", Ability.WISDOM)
        , NATURE ("Nature", Ability.INTELLIGENCE)
        , PERCEPTION ("Perception", Ability.WISDOM)
        , PERFORMANCE ("Performance", Ability.CHARISMA)
        , PERSUASION ("Persuasion", Ability.CHARISMA)
        , RELIGION ("Religion", Ability.INTELLIGENCE)
        , SLEIGHT_OF_HAND ("Sleight of Hand", Ability.DEXTERITY)
        , STEALTH ("Stealth", Ability.DEXTERITY)
        , SURVIVAL ("Survival", Ability.WISDOM);

        public final String skill;
        public final Ability ability;

        Skill(String skill, Ability ability) {
            this.skill = skill;
            this.ability = ability;
        }
    }

    public enum Rest {
        SHORT("Short"),
        LONG("Long");

        public final String desc;

        Rest(String desc) {
            this.desc = desc;
        }
    }
}
