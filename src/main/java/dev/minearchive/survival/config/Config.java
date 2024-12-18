package dev.minearchive.survival.config;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.minearchive.survival.mods.Hud;
import dev.minearchive.survival.mods.Mod;
import dev.minearchive.survival.mods.ModsManager;
import dev.minearchive.survival.mods.setting.settings.*;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Config {
    public static final File DIR = new File(MinecraftClient.getInstance().runDirectory, "config");
    private static final Logger logger = LoggerFactory.getLogger("Survival-stuff Config");

    public static JsonObject toJsonObject(Mod mod) {
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        mod.getSettings().forEach(setting -> {
            JsonObject object = new JsonObject();
            if (setting instanceof BooleanSetting) object.addProperty(setting.getDisplay(), ((BooleanSetting) setting).getValue());
//            if (setting instanceof ColorSetting) object.addProperty(setting.getDisplay(), ((ColorSetting) setting).getValue());
            if (setting instanceof EnumSetting) object.addProperty(setting.getDisplay(), ((EnumSetting) setting).getValue().name());
            if (setting instanceof FloatSetting) object.addProperty(setting.getDisplay(), ((FloatSetting) setting).getValue());
            if (setting instanceof IntegerSetting) object.addProperty(setting.getDisplay(), ((IntegerSetting) setting).getValue());
            jsonArray.add(object);
        });


        jsonObject.add("settings", jsonArray);
        jsonObject.addProperty("enabled", mod.isEnable());
        if (mod instanceof Hud) {
            jsonObject.addProperty("x", ((Hud) mod).getX());
            jsonObject.addProperty("y", ((Hud) mod).getY());
        }

        return jsonObject;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void save() {
        JsonObject object = new JsonObject();
        JsonArray array = new JsonArray();
        ModsManager.getModList().forEach(mod -> {
            JsonObject jo = new JsonObject();
            jo.add(mod.getName(), toJsonObject(mod));
            array.add(jo);
        });

        object.add("settings", array);

        File config = new File(DIR, "survival-stuff.json");

        try {
            if (config.getParentFile() != null && !config.getParentFile().exists()) {
                config.getParentFile().mkdirs();
                config.createNewFile();
            }
            if (!config.exists()) config.createNewFile();

            try (FileWriter fileWriter = new FileWriter(config)) {
                Gson gson = new Gson();
                gson.toJson(object, fileWriter);
            }

        } catch (Exception e) {
            logger.error("Error caused while creating config file.", e);
        }
    }

    public static void load() {
        File config = new File(DIR, "survival-stuff.json");

        if (!config.exists()) return;

        try (InputStream inputStream = new FileInputStream(config)) {
            try (Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
                JsonObject jsonObject = new Gson().fromJson(reader, JsonObject.class);
                JsonArray settings = jsonObject.get("settings").getAsJsonArray();
                settings.forEach(je -> je.getAsJsonObject().entrySet().forEach(entry -> {
                    Mod mod = ModsManager.getModList().stream().filter(m -> m.getName().equals(entry.getKey()))
                            .findFirst().orElse(null);

                    if (mod == null) return;

                    entry.getValue().getAsJsonObject().entrySet().forEach(c -> {
                        if (c.getKey().equals("enabled")) {
                            mod.setToggle(c.getValue().getAsBoolean());
                        }

                        if (c.getKey().equals("settings")) {
                            //読み込みまだ使ってないからいらんね
                        }

                        if (c.getKey().equals("x")) {
                            if (mod instanceof Hud) {
                                ((Hud) mod).setX(c.getValue().getAsFloat());
                            }
                        }

                        if (c.getKey().equals("y")) {
                            if (mod instanceof Hud) {
                                ((Hud) mod).setY(c.getValue().getAsFloat());
                            }
                        }
                    });
                }));
            }
        } catch (Exception e) {
            logger.error("Error caused while loading config file.", e);
        }
    }
}
