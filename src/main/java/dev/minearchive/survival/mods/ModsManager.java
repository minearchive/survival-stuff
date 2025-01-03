package dev.minearchive.survival.mods;

import com.google.common.eventbus.Subscribe;
import dev.minearchive.survival.Client;
import dev.minearchive.survival.events.KeyboardEvent;
import dev.minearchive.survival.events.Render2DEvent;
import dev.minearchive.survival.events.TickEvent;
import dev.minearchive.survival.gui.clickgui.ClickGui;
import dev.minearchive.survival.mods.impl.hud.*;
import dev.minearchive.survival.mods.impl.utility.SprintMod;
import lombok.Getter;
import org.lwjgl.glfw.GLFW;

import java.net.CookieManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static dev.minearchive.survival.util.Util.mc;

public class ModsManager {

    public static ModsManager INSTANCE;

    private static final Map<Class<? extends Mod>, Mod> mods = new HashMap<>();
    @Getter
    private static final List<Mod> modList = new ArrayList<>();

    {
        register(new SprintMod());

        register(new TPS());
        register(new FPS());
        register(new Coordinates());
        register(new SpeedMeter());
        register(new Inventory());

        INSTANCE = this;
    }

    public void register(Mod mod) {
        mods.put(mod.getClass(), mod);
        modList.add(mod);
    }

    public static Mod getModByClass(Class<? extends Mod> clazz) {
        return mods.get(clazz);
    }

    @Subscribe
    public void onTick(TickEvent event) {
        modList.forEach(mod -> {
            if (mod.isEnable()) mod.onTick();
        });
    }

    @Subscribe
    public void onRender2D(Render2DEvent event) {
        if (Client.openGui.isPressed() && mc.currentScreen == null) mc.setScreen(ClickGui.INSTANCE == null ? new ClickGui() : ClickGui.INSTANCE.reset());
        modList.forEach(mod -> {
            if(mod.isEnable()) mod.onRender2D(event);
        });
    }

    @Subscribe
    public void onKey(KeyboardEvent event) {
        modList.forEach(mod -> {
            if (mod.getKeybind().getKey() == event.key()) mod.toggle();
        });
    }

}
