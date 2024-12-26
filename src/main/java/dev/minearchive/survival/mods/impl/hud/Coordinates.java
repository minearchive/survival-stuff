package dev.minearchive.survival.mods.impl.hud;

import dev.minearchive.survival.events.Render2DEvent;
import dev.minearchive.survival.mods.Hud;
import dev.minearchive.survival.mods.ModInfo;
import dev.minearchive.survival.mods.Tag;
import dev.minearchive.survival.util.Fonts;
import dev.minearchive.survival.util.NVGU;
import dev.minearchive.survival.util.nanovg.Alignment;
import net.minecraft.util.math.Direction;

import java.awt.*;

@ModInfo(name = "Coordinates", tag = Tag.HUD, description = "Show you coordinate, rotation, biome")
public class Coordinates extends Hud {
    @Override
    public void onRender(Render2DEvent event, NVGU nvgu) {
        if (nullCheck()) return;
        nvgu.roundedRectangle(getX(), getY(), 300, 40 + Fonts.REGULAR.getHeight(22) * 5, 5, new Color(0x5A000000, true));
        Fonts.REGULAR.drawText("X: " + String.format("%.2f", mc.player.getPos().getX()), getX() + 15, getY() + 55 / 2f, 22, Color.WHITE, Alignment.LEFT_MIDDLE);
        Fonts.REGULAR.drawText("Y: " + String.format("%.2f", mc.player.getPos().getY()), getX() + 15, getY() + 55 / 2f + Fonts.REGULAR.getHeight(22), 22, Color.WHITE, Alignment.LEFT_MIDDLE);
        Fonts.REGULAR.drawText("Z: " + String.format("%.2f", mc.player.getPos().getZ()), getX() + 15, getY() + 55 / 2f + Fonts.REGULAR.getHeight(22) * 2, 22, Color.WHITE, Alignment.LEFT_MIDDLE);
        Fonts.REGULAR.drawText("Direction: " + Direction.fromRotation(mc.player.getYaw()), getX() + 15, getY() + 55 / 2f + Fonts.REGULAR.getHeight(22) * 3, 22, Color.WHITE, Alignment.LEFT_MIDDLE);
        Fonts.REGULAR.drawText("Biomes: " + mc.world.getBiome(mc.player.getBlockPos()).getKey().get().getValue().toTranslationKey(), getX() + 15, getY() + 55 / 2f + Fonts.REGULAR.getHeight(22) * 4, 22, Color.WHITE, Alignment.LEFT_MIDDLE);
        setWidth(300);
        setHeight(40 + Fonts.REGULAR.getHeight(22) * 5);
        setRound(5);
    }
}
