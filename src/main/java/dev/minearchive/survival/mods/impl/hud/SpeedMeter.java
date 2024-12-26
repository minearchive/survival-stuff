package dev.minearchive.survival.mods.impl.hud;

import dev.minearchive.survival.events.Render2DEvent;
import dev.minearchive.survival.mods.Hud;
import dev.minearchive.survival.mods.ModInfo;
import dev.minearchive.survival.mods.Tag;
import dev.minearchive.survival.util.Fonts;
import dev.minearchive.survival.util.NVGU;
import dev.minearchive.survival.util.nanovg.Alignment;

import java.awt.*;

@ModInfo(name = "SpeedMeter", description = "Shows your speed", tag = Tag.HUD)
public class SpeedMeter extends Hud {

    @Override
    public void onRender(Render2DEvent event, NVGU nvgu) {
        if (nullCheck()) return;
        nvgu.roundedRectangle(getX(), getY(), 200, 55, 5, new Color(0x5A000000, true));
        Fonts.REGULAR.drawText("Speed: " + String.format("%.2f", Math.sqrt(Math.pow(mc.player.getVelocity().x, 2) + Math.pow(mc.player.getVelocity().z, 2))), getX() + 15, getY() + 55 / 2f, 22, Color.WHITE, Alignment.LEFT_MIDDLE);
        setWidth(200);
        setHeight(55);
        setRound(5);
    }
}
