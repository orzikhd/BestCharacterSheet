package BestCharacterSheet;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

public class AdventurerLoader {
    public AdventurerLoader() {

    }

    public Adventurer loadAdventurer(String adventurerName, PlayerHandbook playerHandbook) throws Exception {

        try {
            Adventurer res = new Adventurer();

            Document doc = DataReader.readData("src/adventurers/" + adventurerName + ".xml");

            Element adventurerElement = (Element) doc.getElementsByTagName("adventurer").item(0);

            res.setName(getTextFromElement("name", adventurerElement));
            res.setMaxHealth(Integer.parseInt(getTextFromElement("maxhealth", adventurerElement)));
            res.setCurrHealth(Integer.parseInt(getTextFromElement("currhealth", adventurerElement)));

            String className = getTextFromElement("class", adventurerElement);
            AdventurerClass adventurerClass = playerHandbook.getValidClasses().get(className);
            res.setAdventurerClass(adventurerClass);

            String abilityScoresString = getTextFromElement("abilityscores", adventurerElement);
            String[] abilityScoreStrings = abilityScoresString.split(",");
            List<Integer> abillityScores = new ArrayList<Integer>();
            for (int i = 0; i < abilityScoreStrings.length; i++) {
                abillityScores.add(Integer.parseInt(abilityScoreStrings[i].replaceAll("\\s", "")));
            }
            res.setAbilityScores(abillityScores);

            return res;
        } catch (Exception e) {
            System.out.println("ERROR WHEN LOADING FILE");
        }
        return null;
    }

    private String getTextFromElement(String tag, Element root) {
        return root.getElementsByTagName(tag).item(0).getTextContent();
    }
}
