package dev.minearchive.survival.mods.impl.hud.impl;

import dev.minearchive.survival.events.Render2DEvent;
import dev.minearchive.survival.mods.impl.hud.Hud;
import dev.minearchive.survival.util.NVGU;

import java.awt.*;

public class Line extends Hud {

    @Override
    public void onRender(NVGU nvgu) {
        nvgu.roundedRectangle(0, 0, 100, 40, 3, Color.CYAN);
    }
}
