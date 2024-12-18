package dev.minearchive.survival.util;

import dev.minearchive.survival.util.chat.Notification;
import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;

public class DiscordRPCUtil implements Util{
    private static String currentPresents = "";
    private static final DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler(user -> {
        if (mc.player != null && mc.world != null) {
            Notification.info("Welcome " + user.username + "!");
            Notification.info("You just joined discord RPC!");
        }
    }).build();


    public static void startRPC() {
        DiscordRPC.discordInitialize("1223212618932555828", handlers, false);
    }

    public static void stopRPC() {
        DiscordRPC.discordShutdown();
    }

    public static void updatePresents(String info) {
        currentPresents = info;
        DiscordRPC.discordUpdatePresence(new DiscordRichPresence.Builder(info).build());
    }

    public static void updateDetails(String info) {
        DiscordRPC.discordUpdatePresence(new DiscordRichPresence.Builder(currentPresents).setDetails(info).build());
    }
}
