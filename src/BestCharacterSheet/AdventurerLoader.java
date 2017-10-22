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
        res.setName(adventurerElement.getElementsByTagName("name").item(0).getTextContent());

        String className = adventurerElement.getElementsByTagName("class").item(0).getTextContent();
        AdventurerClass adventurerClass = playerHandbook.getValidClasses().get(className);
        res.setAdventurerClass(adventurerClass);

        return res;
    }
}
