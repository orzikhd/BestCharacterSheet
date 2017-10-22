package BestCharacterSheet;

/**
 * Contains the model data for a DnD
 * Adventurer.
 */
public class Adventurer {

    // Name of the Adventurer
    private String name;

    // Class of the Adventurer
    private AdventurerClass adventurerClass;

    public Adventurer() {
        this.name = "NO NAME YET";
    }

    public Adventurer(AdventurerClass adventurerClass) {
        this();
        this.adventurerClass = adventurerClass;
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
