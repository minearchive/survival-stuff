package dev.minearchive.survival;

import com.google.common.eventbus.EventBus;
import dev.minearchive.survival.events.TickEvent;
import dev.minearchive.survival.mods.ModsManager;
import dev.minearchive.survival.util.ThemeUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class Client implements ModInitializer {

    public static EventBus EVENT_BUS = new EventBus();
    public static ModsManager modsManager = new ModsManager();
    public static ThemeUtils themeUtils = new ThemeUtils();
    public static long startMS = 0;
    public static String VERSION = "v0.0-SNAPSHOT";

    @Override
    public void onInitialize() {
        startMS = System.currentTimeMillis();
        EVENT_BUS.register(modsManager);
        ClientTickEvents.START_CLIENT_TICK.register((tick) -> {
            EVENT_BUS.post(new TickEvent());
        });
    }

    private static long modifyLastThreeDigits(long value) {
        long truncatedValue = value / 1000 * 1000;
        return truncatedValue + 222;
    }

    private static long getTime() {
        long a = System.currentTimeMillis();
        long b = modifyLastThreeDigits(a);
        return b > a ? b - 1000 : b;
    }
}
