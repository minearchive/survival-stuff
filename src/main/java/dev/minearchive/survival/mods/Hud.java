package dev.minearchive.survival.mods;

import com.google.common.eventbus.Subscribe;
import dev.minearchive.survival.events.Render2DEvent;
import dev.minearchive.survival.util.NVGU;
import dev.minearchive.survival.util.NanoVGUtil;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class Hud extends Mod {
    boolean draggable;
    float x = 0, y = 0, width = 0, height = 0;
    float dragX, dragY;

    public Hud() {
        this(false);
    }

    public Hud(boolean draggable) {
        this.draggable = draggable;
    }

    @Subscribe
    private void onRenderer2d(Render2DEvent event) {
        this.onRender(event, NanoVGUtil.getNvgu());
    }

    public abstract void onRender(Render2DEvent event, NVGU nvgu);

}