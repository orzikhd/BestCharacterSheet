package BestCharacterSheet;

import java.util.List;

public class AdventurerClass {
    // name of the class
    private String name;

    // hit die of the class
    private int hitDie;

    // full names of ability scores this class has saving throw proficiency in
    private String[] savingThrowProficiencies;

    // ability used for spell casting (if applicable)
    private String spellAbility;

    // 20x10 2d array (20 levels, 10 spell levels) with # of that spell level at that level
    private List<List<Integer>> spellSlotsPerLevel;

    // large text describing starting proficiencies of all sorts
    private String startingProficiencies;

    // large text describing starting equipment
    private String startingEquipment;

    // feature texts by level, for all 20 levels
    private List<List<ClassFeature>> featuresPerLevel;
    public AdventurerClass() {

    }

    public int getHitDie() {
        return hitDie;
    }

    public void setHitDie(int hitDie) {
        this.hitDie = hitDie;
    }

    public String[] getSavingThrowProficiencies() {
        return savingThrowProficiencies;
    }

    public void setSavingThrowProficiencies(String[] savingThrowProficiencies) {
        this.savingThrowProficiencies = savingThrowProficiencies;
    }

    public String getSpellAbility() {
        return spellAbility;
    }

    public void setSpellAbility(String spellAbility) {
        this.spellAbility = spellAbility;
    }

    public List<List<Integer>> getSpellSlotsPerLevel() {
        return spellSlotsPerLevel;
    }

    public void setSpellSlotsPerLevel(List<List<Integer>> spellSlotsPerLevel) {
        this.spellSlotsPerLevel = spellSlotsPerLevel;
    }

    public String getStartingProficiencies() {
        return startingProficiencies;
    }

    public void setStartingProficiencies(String startingProficiencies) {
        this.startingProficiencies = startingProficiencies;
    }

    public String getStartingEquipment() {
        return startingEquipment;
    }

    public void setStartingEquipment(String startingEquipment) {
        this.startingEquipment = startingEquipment;
    }

    public List<List<ClassFeature>> getFeaturesPerLevel() {
        return featuresPerLevel;
    }

    public void setFeaturesPerLevel(List<List<ClassFeature>> featuresPerLevel) {
        this.featuresPerLevel = featuresPerLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public class ClassFeature {
        public String name;
        public String description;
        public boolean optional;

        public ClassFeature(String name, String description, boolean optional) {
            this.name = name;
            this.description = description;
            this.optional = optional;
        }

        @Override
        public String toString() {
            return "(" + this.name + ", " + this.description + ", " + this.optional + ")";
        }
    }
}
