package HoyoJava.HSR;

import HoyoJava.Clients.Client;
import com.fasterxml.jackson.databind.JsonNode;

public class HSRProfile {
    public class Avatar {
        private final String id;
        private final String name;
        private final String iconURL;

        public Avatar(JsonNode data) {
            JsonNode avatar = data.get("avatar");
            this.id = avatar.get("id").asText();
            this.name = avatar.get("name").asText();
            this.iconURL = Client.getActualURL(avatar.get("icon").asText());
        }

        public String getUrl() { return this.iconURL; }
        public String getID() { return this.id; }
        public String getName() { return this.name; }
    }

    public class SpaceInfo {
        record MemoryData(int level, int chaosID, int chaosLevel) {};

        private final MemoryData memoryData;
        private final int universeLevel;
        private final int lightConeCount;
        private final int avatarCount;
        private final int achievementCount;

        public SpaceInfo(JsonNode data) {
            JsonNode spaceInfo = data.get("space_info");
            JsonNode memoryDataNode = spaceInfo.get("memory_data");

            int memoryDataLevel = memoryDataNode.get("level").asInt();
            int memoryDataChaosID = memoryDataNode.get("chaos_id").asInt();
            int memoryDataChaosLevel = memoryDataNode.get("chaos_level").asInt();

            this.memoryData = new MemoryData(memoryDataLevel, memoryDataChaosID, memoryDataChaosLevel);

            this.universeLevel = spaceInfo.get("universe_level").asInt();
            this.lightConeCount = spaceInfo.get("light_cone_count").asInt();
            this.avatarCount = spaceInfo.get("avatar_count").asInt();
            this.achievementCount = spaceInfo.get("achievement_count").asInt();
        }

        public int getUniverseLevel() { return this.universeLevel; }
        public int getLightConeCount() { return this.lightConeCount; }
        public int getAvatarCount() { return this.avatarCount; }
        public int getAchievementCount() { return this.achievementCount; }

        public MemoryData getMemoryData() { return this.memoryData; }

    }

    private final String uid;
    private final String nickname;
    private final int level;
    private final int worldLevel;
    private final int friendCount;
    private final Avatar avatar;
    private final String signature;
    private final SpaceInfo spaceInfo;
    
    public HSRProfile(JsonNode data) {
        this.uid = data.get("uid").asText();
        this.nickname = data.get("nickname").asText();
        this.level = data.get("level").asInt();
        this.worldLevel = data.get("world_level").asInt();
        this.friendCount = data.get("friend_count").asInt();
        this.avatar = new Avatar(data);
		this.signature = data.get("signature").asText();
        this.spaceInfo = new SpaceInfo(data);
    }

    public String getUID() { return this.uid; }
    public String getNickname() { return this.nickname; }
    public int getLevel() { return this.level; }
    public int getWorldLevel() { return this.worldLevel; }
    public int getFriendCount() { return this.friendCount; }
    public String getSignature() { return this.signature; }
    public Avatar getAvatar() { return this.avatar; }
    public SpaceInfo getSpaceInfo() { return this.spaceInfo; }
}
