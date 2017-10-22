package BestCharacterSheet;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class AdventurerLoader {
    public AdventurerLoader() {

    }

    public Adventurer loadAdventurer(String adventurerName, PlayerHandbook playerHandbook) throws Exception {
        Adventurer res = new Adventurer();

        Document doc = DataReader.readData("src/adventurers/" + adventurerName + ".xml");

        Element adventurerElement = (Element) doc.getElementsByTagName("adventurer").item(0);

        res.setName(getTextFromElement("name", adventurerElement));
        res.setMaxHealth(Integer.parseInt(getTextFromElement("maxhealth", adventurerElement)));
        res.setCurrHealth(Integer.parseInt(getTextFromElement("currhealth", adventurerElement)));

        String className = getTextFromElement("class", adventurerElement);
        AdventurerClass adventurerClass = playerHandbook.getValidClasses().get(className);
        res.setAdventurerClass(adventurerClass);

        /*
        String abilityScoresString = getTextFromElement("abilityscores")
        String[] savingThrowProficiencies = rawProficiencies.split(",");
        for (int x = 0; x < savingThrowProficiencies.length; x++) {
            savingThrowProficiencies[x] = savingThrowProficiencies[x].replaceAll("\\s","");
        }
        */

        return res;
    }

    private String getTextFromElement(String tag, Element root) {
        return root.getElementsByTagName(tag).item(0).getTextContent();
    }
}
