package dev.minearchive.survival.mods;

import dev.minearchive.survival.events.Render2DEvent;
import dev.minearchive.survival.mods.setting.Setting;
import dev.minearchive.survival.util.input.Keybind;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.MinecraftClient;

import java.lang.annotation.IncompleteAnnotationException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static dev.minearchive.survival.Client.EVENT_BUS;

@Getter
public class Mod {

    public MinecraftClient mc = MinecraftClient.getInstance();
    @Setter
    private String name = getAnnotation().name();
    private final String description = getAnnotation().description();
    @Setter
    private Keybind keybind = new Keybind(getAnnotation().keyboard());
    private boolean enable = setToggle(getAnnotation().enable());
    private final List<Tag> tags = new CopyOnWriteArrayList<>();
    private final List<Setting<?>> settings = new ArrayList<>();

    public void onTick() { }
    public void onRender2D(Render2DEvent event) { }

    public void toggle() {
        enable = !enable;
        if (enable) EVENT_BUS.register(this);
        else EVENT_BUS.unregister(this);
    }

    public boolean setToggle(boolean toggle) {
        enable = toggle;
        if (toggle) EVENT_BUS.register(this);
        else EVENT_BUS.unregister(this);
        return toggle;
    }

    public boolean nullCheck() {
        return mc.player == null || mc.world == null;
    }

    private ModInfo getAnnotation() throws IncompleteAnnotationException {
        if (getClass().isAnnotationPresent(ModInfo.class)) {
            return getClass().getAnnotation(ModInfo.class);
        }
        throw new IllegalStateException(ModInfo.class + " is missing!");
    }

    public Setting<?> addSetting(Setting<?> setting) {
        this.settings.add(setting);
        return setting;
    }

}
