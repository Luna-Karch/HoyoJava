package HoyoJava.HSR;

import java.util.HashMap;
import java.util.ArrayList;
import HoyoJava.Clients.Client;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.annotation.JsonAppend.Attr;

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

        public TreeSkill(JsonNode skillTreeNode) {
            this.ID = skillTreeNode.get("id").asText();
            this.level = skillTreeNode.get("level").asInt();
            this.anchor = skillTreeNode.get("anchor").asText();
            this.maxLevel = skillTreeNode.get("max_level").asInt();
            this.iconUrl = Client.getActualURL(skillTreeNode.get("icon").asText());
            this.parent = (skillTreeNode.get("parent") instanceof NullNode) ? (null) : (skillTreeNode.get("parent").asText());
        }

        public String getID() { return this.ID; }
        public int getLevel() { return this.level; }
        public String getAnchor() { return this.anchor; }
        public int getMaxLevel() { return this.maxLevel; }
        public String getIconUrl() { return this.iconUrl; }
        public String getParent() { return this.parent; }
    }

    public class SkillTree {
        private final HashMap<String, TreeSkill> skillTree = new HashMap<>();

        public SkillTree(JsonNode skillTreeData) {
            for (final JsonNode skillTreeNode: skillTreeData) {
                String ID = skillTreeNode.get("id").asText();
                skillTree.put(ID, new TreeSkill(skillTreeNode));
            }
        }

        public TreeSkill getSkill (String ID) {
            return skillTree.get(ID);
        }

        public TreeSkill getSkillParent(String ID) {
            TreeSkill currentNode = this.getSkill(ID);

            if (currentNode == null) { return null; }

            return this.getSkill(currentNode.getParent());
        }
    }

    public class LightCone {
        private final String ID;
        private final String name;
        private final int rank;
        private final int level;
        private final int promotion;
        private final String iconUrl;
        private final String previewUrl;
        private final String portraitUrl;
        private final Path path;
        private final ArrayList<Attribute> attributes = new ArrayList<>();
        private final ArrayList<Property> properties = new ArrayList<>();
        private final boolean nullStatus;

        public LightCone(JsonNode characterNode) {
            JsonNode lightConeNode = characterNode.get("light_cone");

            if (lightConeNode instanceof NullNode) {
                /** If no lightcone equipped */
                this.ID = null;
                this.name = null;
                this.rank = 0;
                this.level = 0;
                this.promotion = 0;
                this.iconUrl = null;
                this.previewUrl = null;
                this.portraitUrl = null;
                this.path = null;
                this.nullStatus = true;
                return;
            }

            this.nullStatus = false;
            this.ID = lightConeNode.get("id").asText();
            this.name = lightConeNode.get("name").asText();
            this.rank = lightConeNode.get("rank").asInt();
            this.level = lightConeNode.get("level").asInt();
            this.promotion = lightConeNode.get("promotion").asInt();
            this.iconUrl = Client.getActualURL(lightConeNode.get("icon").asText());
            this.previewUrl = Client.getActualURL(lightConeNode.get("preview").asText());
            this.portraitUrl = Client.getActualURL(lightConeNode.get("portrait").asText());
            this.path = new Path(lightConeNode);

            for (JsonNode attributeNode: characterNode.get("attributes")) {
                this.attributes.add(new Attribute(attributeNode));
            }

            //TODO: Initialize Properties
        }

        public String getID() { return this.ID; }
        public String getName() { return this.name; }
        public int getRank() { return this.rank; }
        public int getLevel() { return this.level; }
        public int getPromotion() { return this.promotion; }
        public String getIconUrl() { return this.iconUrl; }
        public String getPreviewUrl() { return this.previewUrl; }
        public String getPortraitUrl() { return this.portraitUrl; }
        public Path getPath() { return this.path; }
        public ArrayList<Attribute> getAttributes() { return this.attributes; }
        public ArrayList<Property> getProperties() { return this.properties; }
        public boolean isNull() { return this.nullStatus; }
    }

    public class Relic {
        // TODO:
    }

    public class RelicSet {
        // TODO:
    }

    public class Attribute {
        public final String field;
        public final String name;
        public final String iconUrl;
        public final double value;
        public final String display;
        public final boolean percent;

        public Attribute(JsonNode attributeNode) {
            this.field = attributeNode.get("field").asText();
            this.name = attributeNode.get("name").asText();
            this.iconUrl = Client.getActualURL(attributeNode.get("icon").asText());
            this.value = attributeNode.get("value").asDouble();
            this.display = attributeNode.get("display").asText();
            this.percent = attributeNode.get("percent").asBoolean();
        }

        public String getField() { return this.field; }
        public String getName() { return this.name; }
        public String getIconUrl() { return this.iconUrl; }
        public double getValue() { return this.value; }
        public String getDisplayValue() { return this.display; }
        public boolean isPercentage() { return this.percent; }
    }

    public class Addition {
        // TODO:
    }

    public class Property {
        // TODO:
    }

    public class Pos {
        // TODO:
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
    private final SkillTree skillTree;

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

        this.skillTree = new SkillTree(characterNode.get("skill_trees"));

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
    public SkillTree getSkillTree() { return this.skillTree; }
}
