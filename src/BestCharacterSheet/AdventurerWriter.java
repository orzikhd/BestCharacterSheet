package BestCharacterSheet;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class AdventurerWriter {
    public AdventurerWriter() {

    }

    public void writeAdventurer(Adventurer adventurer) throws Exception{
        DocumentBuilderFactory documentBuilderFactory =
                DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder =
                documentBuilderFactory.newDocumentBuilder();

        Document doc = documentBuilder.newDocument();
        Element rootElement = doc.createElement("adventurer");
        doc.appendChild(rootElement);

        addElementToRoot("name", adventurer.getName(),
                rootElement, doc);
        addElementToRoot("class", adventurer.getAdventurerClass().getName(),
                rootElement, doc);
        addElementToRoot("maxhealth", adventurer.getMaxHealth().toString(),
                rootElement, doc);
        addElementToRoot("currhealth", adventurer.getCurrHealth().toString(),
                rootElement, doc);
        addElementToRoot("level", adventurer.getLevel().toString(),
                rootElement, doc);

        String abilityScoresString = adventurer.getAbilityScores().toString();
        addElementFromArrayStringToRoot("abilityscores", abilityScoresString, rootElement, doc);

        // add skill proficiencies set
        String skillProficienciesString = adventurer.getSkillProficiencies().toString();
        addElementFromArrayStringToRoot("skillproficiencies", skillProficienciesString, rootElement, doc);

        DataWriter.writeData("src/adventurers/" + adventurer.getName() + ".xml", doc);
    }

    private void addElementToRoot(String tag, String content, Element root, Document doc) {
        Element newElem = doc.createElement(tag);
        newElem.setTextContent(content);
        root.appendChild(newElem);
    }

    private void addElementFromArrayStringToRoot(String tag, String content, Element root, Document doc) {
        content = content.substring(1).substring(0, content.length() - 2);
        addElementToRoot(tag, content, root, doc);
    }
}
