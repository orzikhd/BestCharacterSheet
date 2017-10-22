package BestCharacterSheet;

/**
 * Contains the model data for a DnD
 * Adventurer.
 */
public class Adventurer {

    // Name of the Adventurer
    private String name;
    private AdventurerClass adventurerClass;

    public Adventurer() {
    }

    public String getName() {
        return name;
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
