package BestCharacterSheet;

import java.util.HashMap;
import java.util.List;

public class Race {

    // Name of the race
    private String name;

    /// Size of the race
    private String size;

    // Speed of the race
    private Integer speed;

    // Map of abilities to modifiers
    private HashMap<String, Integer> abilityScoreIncrease;

    // Map of traits to descriptions
    private List<Trait> traits;

    // Possible proficiency gained as race
    private String proficiency;

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return this.size;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getSpeed() {
        return this.speed;
    }

    public void setAbilityScoreIncrease(HashMap<String, Integer> abilityModifiers) {
        this.abilityScoreIncrease = abilityModifiers;
    }

    public HashMap<String, Integer> getAbilityScoreIncrease() {
        return this.abilityScoreIncrease;
    }

    public void setProficiency(String proficiency) {
        this.proficiency = proficiency;
    }

    public String getProficiency() {
        if(proficiency == null) {
            return "";
        }
        return this.proficiency;
    }

    public List<Trait> getTraits() {
        return this.traits;
    }

    public void setTraits(List<Trait> traits) {
        this.traits = traits;
    }

    public class Trait {
        public String name;
        public String description;

        public Trait(String name, String description) {
            this.name = name;
            this.description = description;
        }

        @Override
        public String toString() {
            return "(" + this.name + ", " + this.description + ")";
        }
    }



}
