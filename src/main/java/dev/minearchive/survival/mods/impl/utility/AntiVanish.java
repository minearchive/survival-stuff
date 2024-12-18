package dev.minearchive.survival.mods.impl.utility;

import com.google.common.eventbus.Subscribe;
import dev.minearchive.survival.events.PacketEvent;
import dev.minearchive.survival.mods.Mod;
import dev.minearchive.survival.mods.ModInfo;
import dev.minearchive.survival.mods.Tag;

@ModInfo(name = "AntiVanish", description = "Detect vanished player...", enable = true, tag = Tag.UTILITY)
public class AntiVanish extends Mod {

    @Override
    public void onTick() {
        if (nullCheck()) return;
    }

    @Subscribe
    public void onPacket(PacketEvent.Receive event) {
        if (nullCheck()) return;
    }
}
