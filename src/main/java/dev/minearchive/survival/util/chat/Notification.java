package dev.minearchive.survival.util.chat;

import dev.minearchive.survival.util.Util;
import net.minecraft.text.Text;

public class Notification implements Util {

    public static void info(String text) {
        if (mc.world == null || mc.player == null) return;
        mc.player.sendMessage(Text.of(text));
    }

}
