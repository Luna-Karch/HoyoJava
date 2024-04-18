package HoyoJava.HSR;

import java.util.ArrayList;
import HoyoJava.Clients.Client;
import com.fasterxml.jackson.databind.JsonNode;

public class HSRCharacter {
    public class Path {
        private final String ID;
        private final String name;
        private final String iconUrl;
        
        public Path(JsonNode characterNode) {
            JsonNode pathNode = characterNode.get("path");

            this.ID = pathNode.get("id").asText();
            this.name = pathNode.get("name").asText();
            this.iconUrl = Client.getActualURL(pathNode.get("icon").asText());
        }

        public String getID() { return this.ID; }
        public String getName() { return this.name; }
        public String getIconUrl() { return this.iconUrl; }
    }

    public class Element {
        private final String ID;
        private final String name;
        private final String color;
        private final String iconUrl;

        public Element(JsonNode characterNode) {
            JsonNode elementNode = characterNode.get("element");

            this.ID = elementNode.get("id").asText();
            this.name = elementNode.get("name").asText();
            this.color = elementNode.get("color").asText();
            this.iconUrl = Client.getActualURL(elementNode.get("icon").asText());
        }

        public Element() {
            this.ID = null;
            this.name = null;
            this.color = null;
            this.iconUrl = null;
        } // Set everything to null when nothing is provided

        public String getID() { return this.ID; }
        public String getName() { return this.name; }
        public String getColor() { return this.color; }
        public String getIconUrl() { return this.iconUrl; }
    }

    public class BaseSkill {
        private final String ID;
        private final String name;
        private final int level;
        private final int maxLevel;
        private final Element element;
        private final String type;
        private final String typeText;
        private final String effect;
        private final String effectText;
        private final String simpleDescription;
        private final String description;
        private final String iconUrl;

        public BaseSkill(String ID, String name, int level, int maxLevel,
        Element element, String type, String typeText, String effect,
        String effectText, String simpleDescription, String description,
        String iconUrl) {
            this.ID = ID;
            this.name = name;
            this.level = level;
            this.maxLevel = maxLevel;
            this.element = element;
            this.type = type;
            this.typeText = typeText;
            this.effect = effect;
            this.effectText = effectText;
            this.simpleDescription = simpleDescription;
            this.description = description;
            this.iconUrl = Client.getActualURL(iconUrl);
        }

        public String getID() { return this.ID; }
        public String getName() { return this.name; }
        public int getLevel() { return this.level; }
        public int getMaxLevel() { return this.maxLevel; }
        public Element getElement() { return this.element; }
        public String getType() { return this.type; }
        public String getTypeText() { return this.typeText; }
        public String getEffect() { return this.effect; }
        public String getEffectText() { return this.effectText; }
        public String getSimpleDescription() { return this.simpleDescription; }
        public String getDescription() { return this.description; }
        public String getIconUrl() { return this.iconUrl; }
    }

    public class TreeSkill {
        private final String ID;
        private final int level;
        private final String anchor;
        private final int maxLevel;
        private final String iconUrl;
        private final String parent;

        public TreeSkill(String ID, int level, String anchor, 
        int maxLevel, String iconUrl) {
            this.ID = ID;
            this.level = level;
            this.anchor = anchor;
            this.maxLevel = maxLevel;
            this.iconUrl = Client.getActualURL(iconUrl);
            this.parent = null;
        }

        public TreeSkill(String ID, int level, String anchor, 
        int maxLevel, String iconUrl, String parent) {
            this.ID = ID;
            this.level = level;
            this.anchor = anchor;
            this.maxLevel = maxLevel;
            this.iconUrl = Client.getActualURL(iconUrl);
            this.parent = parent;
        } // Alternate constructor for when a parent is present

        public String getID() { return this.ID; }
        public int getLevel() { return this.level; }
        public String getAnchor() { return this.anchor; }
        public int getMaxLevel() { return this.maxLevel; }
        public String getIconUrl() { return this.iconUrl; }
        public String getParent() { return this.parent; }
    }

    private final String ID;
    private final String name;
    private final int rarity;
    private final int rank;
    private final int level;
    private final int promotion;
    private String iconUrl;
    private String previewUrl;
    private String portraitUrl;
    private String[] rankIcons;
    private final Path path;
    private final Element element;
    private final ArrayList<BaseSkill> skills;

    public HSRCharacter(JsonNode characterNode) {
        this.ID = characterNode.get("id").asText();
        this.name = characterNode.get("name").asText();
        this.rarity = characterNode.get("rarity").asInt();
        this.rank = characterNode.get("rank").asInt();
        this.promotion = characterNode.get("level").asInt();
        this.iconUrl = Client.getActualURL(characterNode.get("icon").asText());
        this.path = new Path(characterNode);
        // TODO: Implement Rank Icons
        this.element = new Element(characterNode);

        throw new UnsupportedOperationException(); // TODO
    }
}
