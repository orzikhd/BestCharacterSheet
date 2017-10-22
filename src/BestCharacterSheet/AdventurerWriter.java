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

        // remove brackets
        String abilityScoresString = adventurer.getAbilityScores().toString().substring(1);
        abilityScoresString = abilityScoresString.substring(0, abilityScoresString.length() - 1);
        addElementToRoot("abilityscores", abilityScoresString,
                rootElement, doc);

        DataWriter.writeData("src/adventurers/" + adventurer.getName() + ".xml", doc);
    }

    private void addElementToRoot(String tag, String content, Element root, Document doc) {
        Element newElem = doc.createElement(tag);
        newElem.setTextContent(content);
        root.appendChild(newElem);
    }
}
