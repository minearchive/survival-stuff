package dev.minearchive.survival.mods.impl;

import dev.minearchive.survival.events.Render2DEvent;
import dev.minearchive.survival.mods.Mod;
import dev.minearchive.survival.mods.impl.hud.Hud;
import dev.minearchive.survival.util.NanoVGUtil;

import java.util.ArrayList;
import java.util.List;

public class HudMod extends Mod {

    public List<Hud> hudList = new ArrayList<>();

    @Override
    public void onRender2D(Render2DEvent event) {
        hudList.forEach(hud -> NanoVGUtil.setupAndDraw(true, vg -> {
            vg.translate(hud.getX(), hud.getY());
            hud.onRender(vg);
            vg.translate(-hud.getX(), -hud.getY());
        }));
    }
}
