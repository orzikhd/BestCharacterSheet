package BestCharacterSheet;

import java.util.*;

/**
 * Contains the model data for a DnD
 * Adventurer.
 */
public class Adventurer {

    private static final String[] ABILITYLIST = new String[] {
            "Strength"
            , "Dexterity"
            , "Constitution"
            , "Intelligence"
            , "Wisdom"
            , "Charisma"
    };
    public static final List<String> ABILITIES = new ArrayList<String>(Arrays.asList(ABILITYLIST));

    private static final String[] SKILLSLIST = new String[] {
            "Acrobatics"
            , "Animal Handling"
            , "Arcana"
            , "Athletics"
            , "Deception"
            , "History"
            , "Insight"
            , "Intimidation"
            , "Investigation"
            , "Medicine"
            , "Nature"
            , "Perception"
            , "Performance"
            , "Persuasion"
            , "Religion"
            , "Sleight of Hand"
            , "Stealth"
            , "Survival"
    };

    public static final List<String> SKILLS = new ArrayList<String>(Arrays.asList(SKILLSLIST));

    public static final Map<Integer, List<String>> ABILITYTOSKILLS;
    static {
        ABILITYTOSKILLS = new HashMap<Integer, List<String>>();
        List<String> strengthSkills = new ArrayList<String>(Arrays.asList(
                new String[] {"Athletics"}));
        List<String> dexSkills = new ArrayList<String>(Arrays.asList(
                new String[] {"Acrobatics", "Sleight of Hand", "Stealth"}));
        List<String> constSkills = new ArrayList<String>(Arrays.asList(
                new String[] {}));
        List<String> intSkills = new ArrayList<String>(Arrays.asList(
                new String[] {"Arcana", "History", "Investigation", "Nature", "Religion"}));
        List<String> wisSkills = new ArrayList<String>(Arrays.asList(
                new String[] {"Animal Handling", "Insight", "Medicine", "Perception", "Survival"}));
        List<String> chaSkills = new ArrayList<String>(Arrays.asList(
                new String[] {"Deception", "Intimidation", "Performance", "Persuasion"}));

        ABILITYTOSKILLS.put(1, strengthSkills);
        ABILITYTOSKILLS.put(2, dexSkills);
        ABILITYTOSKILLS.put(3, constSkills);
        ABILITYTOSKILLS.put(4, intSkills);
        ABILITYTOSKILLS.put(5, wisSkills);
        ABILITYTOSKILLS.put(6, chaSkills);
    }

    // Name of the Adventurer
    private String name;

    // Level of the Adventurer
    private Integer level;

    // Proficiency bonus of the Adventurer
    private Integer proficiencyBonus;

    // Class of the Adventurer
    private AdventurerClass adventurerClass;

    // Max Health of the Adventurer
    private Integer maxHealth;

    // Current Health of the Adventurer
    private Integer currHealth;

    // Current Ability Scores of the Adventurer
    private List<Integer> abilityScores;

    // Skill Proficiencies
    private Set<String> skillProficiencies;

    public Adventurer() {   }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
        setProficiencyBonus();
    }

    private void setProficiencyBonus() {
        this.proficiencyBonus = (int) Math.ceil((double) level / 4.0) + 1;
    }

    public Integer getProficiencyBonus() {
        return proficiencyBonus;
    }

    public Set<String> getSkillProficiencies() {
        return skillProficiencies;
    }

    public Map<String, Integer> getSkillModifiers() {
        Map<String, Integer> res = new HashMap<String, Integer>();

        List<Integer> abilityModifiers = this.getAbilityModifiers();

        for (int i = 1; i < 7; i++) {
            List<String> skills = ABILITYTOSKILLS.get(i);
            int abilityMod = abilityModifiers.get(i - 1);
            for (String skill : skills) {
                int profMod = abilityMod;

                if (this.getSkillProficiencies().contains(skill)) {
                    profMod += this.getProficiencyBonus();
                }

                res.put(skill, profMod);
            }
        }

        return res;
    }

    public void setSkillProficiencies(Set<String> skillProficiencies) {
        this.skillProficiencies = skillProficiencies;
    }

    public List<Integer> getAbilityScores() {
        return abilityScores;
    }

    public List<Integer> getAbilityModifiers() {
        List<Integer> res = new ArrayList<Integer>();

        for (Integer i : abilityScores) {
            res.add((int)Math.floor(((double)(i - 10))/2.0));
        }

        return res;
    }

    public void setAbilityScores(List<Integer> abilityScores) {
        this.abilityScores = abilityScores;
    }

    public String getName() {
        return name;
    }

    public Integer getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(Integer maxHealth) {
        this.maxHealth = maxHealth;
    }

    public Integer getCurrHealth() {
        return currHealth;
    }

    public void setCurrHealth(Integer currHealth) {
        this.currHealth = currHealth;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AdventurerClass getAdventurerClass() {
        return adventurerClass;
    }

    public void setAdventurerClass(AdventurerClass adventurerClass) {
        this.adventurerClass = adventurerClass;
    }

}
