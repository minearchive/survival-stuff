package dev.minearchive.survival.mods.impl.hud;

import dev.minearchive.survival.events.Render2DEvent;
import dev.minearchive.survival.mods.Hud;
import dev.minearchive.survival.mods.ModInfo;
import dev.minearchive.survival.mods.Tag;
import dev.minearchive.survival.util.Fonts;
import dev.minearchive.survival.util.NVGU;
import dev.minearchive.survival.util.NanoVGUtil;
import dev.minearchive.survival.util.nanovg.Alignment;

import java.awt.*;

@ModInfo(name = "FPS", description = "Show you Frame Per Second", tag = Tag.HUD)
public class FPS extends Hud {

    @Override
    public void onRender(Render2DEvent event, NVGU nvgu) {
        nvgu.roundedRectangle(getX(), getY(), 200, 55, 5, new Color(0x5A000000, true));
        Fonts.REGULAR.drawText("FPS: " + mc.getCurrentFps(), getX() + 15, getY() + 55 / 2f, 22, Color.WHITE, Alignment.LEFT_MIDDLE);
    }
}
