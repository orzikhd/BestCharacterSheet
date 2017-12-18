package BestCharacterSheet;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Contains the model data for a DnD
 * Adventurer.
 */
public class Adventurer {

    private static final String[] ABILITY_LIST = new String[] {
            "Strength"
            , "Dexterity"
            , "Constitution"
            , "Intelligence"
            , "Wisdom"
            , "Charisma"
    };
    public static final List<String> ABILITIES = new ArrayList<String>(Arrays.asList(ABILITY_LIST));

    private static final String[] SKILLS_LIST = new String[] {
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
    public static final List<String> SKILLS = new ArrayList<String>(Arrays.asList(SKILLS_LIST));

    public static final Map<Integer, List<String>> ABILITY_TO_SKILLS;
    static {
        ABILITY_TO_SKILLS = new HashMap<Integer, List<String>>();
        List<String> strSkills = new ArrayList<String>(Collections.singletonList("Athletics"));
        List<String> dexSkills = new ArrayList<String>(Arrays.asList(
                "Acrobatics", "Sleight of Hand", "Stealth"));
        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection") // constitution has no associated skills
        List<String> conSkills = new ArrayList<String>();
        List<String> intSkills = new ArrayList<String>(Arrays.asList(
                "Arcana", "History", "Investigation", "Nature", "Religion"));
        List<String> wisSkills = new ArrayList<String>(Arrays.asList(
                "Animal Handling", "Insight", "Medicine", "Perception", "Survival"));
        List<String> chaSkills = new ArrayList<String>(Arrays.asList(
                "Deception", "Intimidation", "Performance", "Persuasion"));

        ABILITY_TO_SKILLS.put(1, strSkills);
        ABILITY_TO_SKILLS.put(2, dexSkills);
        ABILITY_TO_SKILLS.put(3, conSkills);
        ABILITY_TO_SKILLS.put(4, intSkills);
        ABILITY_TO_SKILLS.put(5, wisSkills);
        ABILITY_TO_SKILLS.put(6, chaSkills);
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

    // Item Inventory
    private List<Item> inventory;

    // Notes
    private String notes;

    public Adventurer() {   }

    public Adventurer(String name, Integer level, AdventurerClass adventurerClass,
                      Integer maxHealth, Integer currHealth, List<Integer> abilityScores,
                      Set<String> skillProficiencies, List<Item> inventory, String notes) {
        this.name = name;
        this.level = level;
        this.adventurerClass = adventurerClass;
        this.maxHealth = maxHealth;
        this.currHealth = currHealth;
        this.abilityScores = abilityScores;
        this.skillProficiencies = skillProficiencies;
        this.inventory = inventory;
        this.notes = notes;
        this.setProficiencyBonus();
    }

    public String getNotes() {return notes;}

    public void setNotes(String notes) {this.notes = notes;}

    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    public void addItem(Item item) {
        this.inventory.add(item);
    }

    // Removes the first occurance of an item with the given description
    public void removeItemByDescription(String description) {
        for(int i = 0; i < this.inventory.size(); i++) {
            if (this.inventory.get(i).getDescription().equals(description)) {
                this.inventory.remove(i);
                break;
            }
        }
    }

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
            List<String> skills = ABILITY_TO_SKILLS.get(i);

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

    /**
     * Builds an adventurer using typical builder pattern. Supply all arguments for happy days.
     *
     * typical builder pattern example:
     *         Adventurer lars = new Adventurer.AdventurerBuilder()
     *              .withName("Lars Clamberlot")
     *              .withLevel(3)
     *              .withMaxHealth(8)
     *              .withAdventurerClass(playerHandbook.getValidClasses().get("Artificer"))
     *              .withAbilityScores(Arrays.asList(10,10,10,10,10,10))
     *              .withSkillProficiencies(new HashSet<String>(Arrays.asList("Athletics", "Persuasion")))
     *              .withInventory(new ArrayList<Item>())
     *              .build();
     */
    public static class AdventurerBuilder {
        private String name;
        private Integer level;
        private AdventurerClass adventurerClass;
        private Integer maxHealth;
        private Integer currHealth;
        private List<Integer> abilityScores;
        private Set<String> skillProficiencies;
        private List<Item> inventory;
        private String notes;

        public AdventurerBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public AdventurerBuilder withLevel(Integer level) {
            this.level = level;
            return this;
        }

        public AdventurerBuilder withAdventurerClass(AdventurerClass adventurerClass) {
            this.adventurerClass = adventurerClass;
            return this;
        }

        public AdventurerBuilder withMaxHealth(Integer maxHealth) {
            this.maxHealth = maxHealth;
            return this;
        }

        public AdventurerBuilder withCurrHealth(Integer currHealth) {
            this.currHealth = currHealth;
            return this;
        }

        public AdventurerBuilder withAbilityScores(List<Integer> abilityScores) {
            this.abilityScores = abilityScores;
            return this;
        }

        public AdventurerBuilder withSkillProficiencies(Set<String> skillProficiencies) {
            this.skillProficiencies = skillProficiencies;
            return this;
        }

        public AdventurerBuilder withInventory(List<Item> inventory) {
            this.inventory = inventory;
            return this;
        }

        public AdventurerBuilder withNotes(String notes) {
            this.notes = notes;
            return this;
        }

        public Adventurer build() {
            if(name == null || level == null || adventurerClass == null || maxHealth == null || currHealth == null ||
                    abilityScores == null || skillProficiencies == null || inventory == null || notes == null) {
                System.out.println("AdventurerBuilder: NOTE BUILD INCOMPLETE");
            }
            return new Adventurer(name, level, adventurerClass, maxHealth, currHealth, abilityScores, skillProficiencies, inventory, notes);
        }
    }
}
