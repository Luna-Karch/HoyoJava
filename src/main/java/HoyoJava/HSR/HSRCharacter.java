package HoyoJava.HSR;

import HoyoJava.Clients.Client;
import com.fasterxml.jackson.databind.JsonNode;

public class HSRCharacter {
    public class Path {
        private final String ID;
        private final String name;
        private final String iconUrl;
        
        public Path(String ID, String name, String iconUrl) {
            this.ID = ID;
            this.name = name;
            this.iconUrl = Client.getActualURL(iconUrl);
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

        public Element(String ID, String name, String color, String iconUrl) {
            this.ID = ID;
            this.name = name;
            this.color = color;
            this.iconUrl = Client.getActualURL(iconUrl);
        }

        public Element() {
            this.ID = null;
            this.name = null;
            this.color = null;
            this.iconUrl = null;
        }

        public String getID() { return this.ID; }
        public String getName() { return this.name; }
        public String getColor() { return this.color; }
        public String getIconUrl() { return this.iconUrl; }
    }

    public class Skill {
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

        public Skill(String ID, String name, int level, int maxLevel,
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

    public HSRCharacter(JsonNode characterNode) {
        throw new UnsupportedOperationException(); // TODO
    }
}
