package BestCharacterSheet;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class PlayerHandbook {
    public static final String CLASS_LOCATION = "src/data/Classes.xml";

    public static final String RACE_LOCATION = "src/data/Races.xml";

    public static final String BACKGROUND_LOCATION = "src/data/Backgrounds.xml";

    public static final String FEAT_LOCATION = "src/data/Feats.xml";

    public static final String MUNDANE_ITEMS_LOCATION = "src/data/Mundane Items.xml";

    private boolean dev_mode = false;

    public PlayerHandbook() throws Exception {
        // build class dictionary
        Document classDoc = DataReader.readData(CLASS_LOCATION);

        NodeList classes = classDoc.getElementsByTagName("class");

        for (int i = 0; i < classes.getLength(); i++) {
            AdventurerClass artificer = new AdventurerClass();
            Element adventurerClass = (Element) classes.item(i);

            String name = adventurerClass.getElementsByTagName("name").item(0).getTextContent();
            artificer.setName(name);
            print(name);

            Integer hitDie = Integer.parseInt(
                    adventurerClass.getElementsByTagName("hd").item(0).getTextContent());
            artificer.setHitDie(hitDie);
            print(hitDie.toString());

            String rawProficiencies = adventurerClass.getElementsByTagName("proficiency").item(0).getTextContent();
            print(rawProficiencies);
            String[] savingThrowProficiencies = rawProficiencies.split(",");
            for (int x = 0; x < savingThrowProficiencies.length; x++) {
                savingThrowProficiencies[x] = savingThrowProficiencies[x].replaceAll("\\s","");
            }
            artificer.setSavingThrowProficiencies(savingThrowProficiencies);
            print(savingThrowProficiencies[0].toString());
            print(savingThrowProficiencies[1].toString());

            String spellAbility = adventurerClass.getElementsByTagName("spellAbility").item(0).getTextContent();
            artificer.setSpellAbility(spellAbility);
            print(spellAbility);

            NodeList autoLevels = adventurerClass.getElementsByTagName("autolevel");

            // grab the first 20 autoLevels to make spell slot table
            List<List<Integer>> spellSlotsPerLevel = new ArrayList<List<Integer>>();
            int autoLevelCounter;
            for (autoLevelCounter = 0; autoLevelCounter < 20; autoLevelCounter++) {
                Element autoLevel = (Element) autoLevels.item(autoLevelCounter);
                String rawSlots = autoLevel.getElementsByTagName("slots").item(0).getTextContent();
                List<Integer> slots = new ArrayList<Integer>();
                for (String slot : rawSlots.split(",")) {
                    slots.add(Integer.parseInt(slot.replaceAll("\\s","")));
                }
                spellSlotsPerLevel.add(slots);
            }
            artificer.setSpellSlotsPerLevel(spellSlotsPerLevel);
            print(spellSlotsPerLevel.toString());

            // next autolevel should be starting proficiencies and equipment
            Element autoLevel = (Element) autoLevels.item(autoLevelCounter);
            Element profs = (Element) autoLevel.getElementsByTagName("feature").item(0);
            artificer.setStartingProficiencies(combineTextElements(profs));
            print(combineTextElements(profs));

            Element equips = (Element) autoLevel.getElementsByTagName("feature").item(1);
            artificer.setStartingEquipment(combineTextElements(equips));
            print(combineTextElements(equips));

            autoLevelCounter++;

            List<List<AdventurerClass.ClassFeature>> featuresByLevel =
                    new ArrayList<List<AdventurerClass.ClassFeature>>();
            for (; autoLevelCounter <autoLevels.getLength(); autoLevelCounter++) {
                autoLevel = (Element) autoLevels.item(autoLevelCounter);
                NodeList features = autoLevel.getElementsByTagName("feature");

                List<AdventurerClass.ClassFeature> classFeatures =
                        new ArrayList<AdventurerClass.ClassFeature>();
                for (int x = 0; x < features.getLength(); x++) {
                    Element featureElem = (Element) features.item(x);
                    boolean optional = featureElem.hasAttribute("optional");
                    String featureName = featureElem.getElementsByTagName("name").item(0).getTextContent();
                    String description = combineTextElements(featureElem);
                    AdventurerClass.ClassFeature feature =
                            artificer.new ClassFeature(featureName, description, optional);
                    classFeatures.add(feature);
                }
                print(classFeatures.toString());
            }



            break; // only doing Artificer right now
        }

        // build race dictionary

        // build background dictionary

        // build feat dictionary

        // build item dictionary
    }

    private String combineTextElements(Element root) {
        NodeList texts = root.getElementsByTagName("text");
        String res = "";
        for (int i = 0; i < texts.getLength(); i++) {
            res += (texts.item(i)).getTextContent() + "\n";
        }

        return res;
    }

    private void print(String toPrint) {
        if (dev_mode) {
            System.out.println(toPrint);
        }
    }
}
