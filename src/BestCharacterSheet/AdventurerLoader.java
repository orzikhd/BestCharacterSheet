package BestCharacterSheet;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.*;

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
        res.setLevel(Integer.parseInt(getTextFromElement("level", adventurerElement)));

        String className = getTextFromElement("class", adventurerElement);
        AdventurerClass adventurerClass = playerHandbook.getValidClasses().get(className);
        res.setAdventurerClass(adventurerClass);

        String abilityScoresString = getTextFromElement("abilityscores", adventurerElement);
        String[] abilityScoreStrings = abilityScoresString.split(", ");
        List<Integer> abillityScores = new ArrayList<Integer>();
        for (int i = 0; i < abilityScoreStrings.length; i++) {
            //abillityScores.add(Integer.parseInt(abilityScoreStrings[i].replaceAll("\\s","")));
            abillityScores.add(Integer.parseInt(abilityScoreStrings[i]));
        }
        res.setAbilityScores(abillityScores);

        String skillProficienciesString = getTextFromElement("skillproficiencies", adventurerElement);
        String[] skillProficiencyStrings = skillProficienciesString.split(", ");
        Set<String> skillProficiencies = new HashSet<String>(Arrays.asList(skillProficiencyStrings));
        res.setSkillProficiencies(skillProficiencies);

        return res;
    }

    private String getTextFromElement(String tag, Element root) {
        return root.getElementsByTagName(tag).item(0).getTextContent();
    }
}
