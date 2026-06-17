package net.claimviz.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.claimviz.ClaimViz;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class ConfigManager {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = FabricLoader.getInstance()
        .getConfigDir().resolve("claimviz/servers.json");

    private static ClaimVizConfig config = new ClaimVizConfig();

    public static void load() {
        if (!Files.exists(CONFIG_PATH)) {
            save();
            return;
        }
        try {
            String json = Files.readString(CONFIG_PATH);
            ClaimVizConfig loaded = GSON.fromJson(json, ClaimVizConfig.class);
            config = loaded != null ? loaded : new ClaimVizConfig();
            ClaimViz.LOGGER.info("ClaimViz loaded {} server entries", config.servers != null ? config.servers.size() : 0);
        } catch (IOException e) {
            ClaimViz.LOGGER.error("Failed to load config, using defaults", e);
        }
    }

    public static void save() {
        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            Files.writeString(CONFIG_PATH, GSON.toJson(config));
        } catch (IOException e) {
            ClaimViz.LOGGER.error("Failed to save config", e);
        }
    }

    /**
     * Returns the first enabled server entry whose serverAddress is a substring
     * of the given address (case-insensitive).
     */
    public static Optional<ClaimVizConfig.ServerConfig> getForServer(String address) {
        if (config.servers == null || address == null) return Optional.empty();
        String lower = address.toLowerCase();
        return config.servers.stream()
            .filter(s -> s.enabled && lower.contains(s.serverAddress.toLowerCase()))
            .findFirst();
    }

    public static ClaimVizConfig get() {
        return config;
    }
}
