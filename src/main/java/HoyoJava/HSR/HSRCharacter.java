package HoyoJava.HSR;

import java.util.ArrayList;
import HoyoJava.Clients.Client;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.NullNode;

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

        public BaseSkill(JsonNode skillData) {
            this.ID = skillData.get("id").asText();
            this.name = skillData.get("name").asText();
            this.level = skillData.get("level").asInt();
            this.maxLevel = skillData.get("max_level").asInt();
            this.element = (skillData.get("element") instanceof NullNode) ? (new Element()) : (new Element(skillData));
            this.type = skillData.get("type").asText();
            this.typeText = skillData.get("type_text").asText();
            this.effect = skillData.get("effect").asText();
            this.effectText = skillData.get("effect_text").asText();
            this.simpleDescription = skillData.get("simple_desc").asText();
            this.description = skillData.get("desc").asText();
            this.iconUrl = Client.getActualURL(skillData.get("icon").asText());
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

    public class SkillTree {
        // TODO: Implement a skill tree system
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
    private ArrayList<String> rankIcons = new ArrayList<>();
    private final Path path;
    private final Element element;
    private final ArrayList<BaseSkill> skills = new ArrayList<>();

    public HSRCharacter(JsonNode characterNode) {
        this.ID = characterNode.get("id").asText();
        this.name = characterNode.get("name").asText();
        this.rarity = characterNode.get("rarity").asInt();
        this.rank = characterNode.get("rank").asInt();
        this.promotion = characterNode.get("level").asInt();
        this.iconUrl = Client.getActualURL(characterNode.get("icon").asText());
        this.path = new Path(characterNode);
        
        for (final JsonNode rankIcon: characterNode.get("rank_icons")) {
            this.rankIcons.add(rankIcon.asText());
        }
        this.element = new Element(characterNode);

        for (final JsonNode skillData: characterNode.get("skills")) {
            this.skills.add(new BaseSkill(skillData));
        }

        throw new UnsupportedOperationException(); // TODO
    }

    public String getID() { return this.ID; }
    public String getName() { return this.name; }
    public int getRarity() { return this.rarity; }
    public int getLevel() { return this.level; }
    public int getRank() { return this.rank; }
    public int getPromotion() { return this.promotion; }
    public String getIconUrl() { return this.iconUrl; }
    public String getPreviewUrl() { return this.previewUrl; }
    public String getPortraitUrl() { return this.portraitUrl; }
    public ArrayList<String> getRankIcons() { return this.rankIcons; }
    public Path getPath() { return this.path; }
    public Element getElement() { return this.element; }
    public ArrayList<BaseSkill> getSkills() { return this.skills; }
}
