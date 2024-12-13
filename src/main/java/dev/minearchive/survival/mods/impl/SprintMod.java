package dev.minearchive.survival.mods.impl;

import dev.minearchive.survival.mods.Mod;
import dev.minearchive.survival.mods.ModInfo;

@ModInfo(name = "Sprint", description = "Daaaaaaaaash", enable = true)
public class SprintMod extends Mod {

    @Override
    public void onTick() {
        if (nullCheck()) return;
        mc.player.setSprinting(!mc.player.horizontalCollision && mc.player.forwardSpeed > 0);
    }
}
