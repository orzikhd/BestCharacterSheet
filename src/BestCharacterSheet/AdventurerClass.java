package BestCharacterSheet;

import java.util.List;

public class AdventurerClass {
    private String name;
    private int hitDie;
    private String[] savingThrowProficiencies;
    private String spellAbility;
    private List<List<Integer>> spellSlotsPerLevel;
    private String startingProficiencies;
    private String startingEquipment;
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
