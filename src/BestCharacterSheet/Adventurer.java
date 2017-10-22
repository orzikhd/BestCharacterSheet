package BestCharacterSheet;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains the model data for a DnD
 * Adventurer.
 */
public class Adventurer {

    // Name of the Adventurer
    private String name;

    // Class of the Adventurer
    private AdventurerClass adventurerClass;

    // Max Health of the Adventurer
    private Integer maxHealth;

    // Current Health of the Adventurer
    private Integer currHealth;

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

    // Current Ability Scores of the Adventurer
    private List<Integer> abilityScores;

    public Adventurer() {   }

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
