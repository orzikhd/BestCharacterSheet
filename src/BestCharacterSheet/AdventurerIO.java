package BestCharacterSheet;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Provides functionality to read an adventurer object from xml file and to write an adventurer to xml for later use.
 */
public class AdventurerIO {
    /**
     * Writes an adventurer to file. Overwrites if an adventurer of the same name already exists.
     * @param adventurer the adventurer to store
     * @throws NullPointerException if something goes wrong probably
     */
    public static void writeAdventurer(Adventurer adventurer) throws NullPointerException {
        System.out.println("Writing Adventurer: " + adventurer.getName());

        DocumentBuilderFactory documentBuilderFactory =
                DocumentBuilderFactory.newInstance();


        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder =
                    documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.out.println("ERROR CREATING DOCUMENT BUILDER");
        }

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

        // add inventory
        List<Item> inventory = adventurer.getInventory();
        Element inventoryElem = doc.createElement("inventory");
        for (Item item : inventory) {
            addElementToRoot("item", item.getDescription(), inventoryElem, doc);
        }
        rootElement.appendChild(inventoryElem);

        addElementToRoot("notes", adventurer.getNotes(),
                rootElement, doc);

        try {
            DataWriter.writeData("src/adventurers/" + adventurer.getName() + ".xml", doc);
        } catch (Exception e) {
            System.out.println("ERROR WRITING TO FILE");
        }
    }

    /**
     * Loads an adventurer from file.
     * @param adventurerName name of the adventurer, uniquely identifies the adventurer as a file
     * @param playerHandbook player handbook to use to validate material read from file
     * @return adventurer as read from file
     * @throws Exception
     */
    public static Adventurer loadAdventurer(String adventurerName, PlayerHandbook playerHandbook) throws Exception{
        System.out.println("Reading Adventurer: " + adventurerName);

        Adventurer.AdventurerBuilder builder = new Adventurer.AdventurerBuilder();

        Document doc = null;
        try {
            doc = DataReader.readData("src/adventurers/" + adventurerName + ".xml");
        } catch (Exception e) {
            System.out.println("ERROR READING FROM FILE");
        }

        Element adventurerElement = (Element) doc.getElementsByTagName("adventurer").item(0);

        // Read health level and class
        String name = getTextFromElement("name", adventurerElement);
        int maxHealth = Integer.parseInt(getTextFromElement("maxhealth", adventurerElement));
        int currHealth = Integer.parseInt(getTextFromElement("currhealth", adventurerElement));
        int setLevel = Integer.parseInt(getTextFromElement("level", adventurerElement));

        String className = getTextFromElement("class", adventurerElement);
        AdventurerClass adventurerClass = playerHandbook.getValidClasses().get(className);

        // read ability scores
        String abilityScoresString = getTextFromElement("abilityscores", adventurerElement);
        String[] abilityScoreStrings = abilityScoresString.split(", ");
        List<Integer> abillityScores = new ArrayList<Integer>();
        for (String as : abilityScoreStrings) {
            abillityScores.add(Integer.parseInt(as));
        }

        // read skill skill proficiencies
        String skillProficienciesString = getTextFromElement("skillproficiencies", adventurerElement);
        String[] skillProficiencyStrings = skillProficienciesString.split(", ");
        Set<String> skillProficiencies = new HashSet<String>(Arrays.asList(skillProficiencyStrings));


        // read inventory
        List<Item> inventory = new ArrayList<Item>();
        Element inventoryElem = (Element) adventurerElement.getElementsByTagName("inventory").item(0);
        NodeList itemElems = inventoryElem.getElementsByTagName("item");
        for (int i = 0; i < itemElems.getLength(); i++) {
            String description = itemElems.item(i).getTextContent();
            inventory.add(new Item(description));
        }

        // read notes
        String notes = getTextFromElement("notes", adventurerElement);

        // build Adventurer
        return builder.withName(name).withMaxHealth(maxHealth)
                .withCurrHealth(currHealth).withLevel(setLevel)
                .withAdventurerClass(adventurerClass).withAbilityScores(abillityScores)
                .withSkillProficiencies(skillProficiencies)
                .withInventory(inventory)
                .withNotes(notes)
                .build();
    }

    private static String getTextFromElement(String tag, Element root) {
        return root.getElementsByTagName(tag).item(0).getTextContent();
    }

    private static void addElementToRoot(String tag, String content, Element root, Document doc) {
        Element newElem = doc.createElement(tag);
        newElem.setTextContent(content);
        root.appendChild(newElem);
    }

    private static void addElementFromArrayStringToRoot(String tag, String content, Element root, Document doc) {
        content = content.substring(1).substring(0, content.length() - 2);
        addElementToRoot(tag, content, root, doc);
    }
}
