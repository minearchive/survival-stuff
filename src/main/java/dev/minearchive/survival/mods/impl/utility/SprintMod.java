package dev.minearchive.survival.mods.impl.utility;

import dev.minearchive.survival.mods.Mod;
import dev.minearchive.survival.mods.ModInfo;
import dev.minearchive.survival.mods.Tag;

@ModInfo(name = "Sprint", description = "Daaaaaaaaash", enable = true, tag = Tag.UTILITY)
public class SprintMod extends Mod {

    @Override
    public void onTick() {
        if (nullCheck()) return;
        mc.player.setSprinting(!mc.player.horizontalCollision && mc.player.forwardSpeed > 0);
    }
}
