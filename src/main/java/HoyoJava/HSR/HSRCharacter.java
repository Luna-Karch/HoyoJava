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

        public String getID() { return this.ID; }
        public String getName() { return this.name; }
        public String getColor() { return this.color; }
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
