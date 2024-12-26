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
    private boolean draggable = true;
    private boolean dragging = false;
    private float x = 0, y = 0, width = 0, height = 0, round = 0;
    private float lastX = 0, lastY = 0;

    public Hud() {
        this(true);
    }

    public Hud(boolean draggable) {
        this.draggable = draggable;
    }

    @Subscribe
    private void onRenderer2d(Render2DEvent event) {
        this.onRender(event, NanoVGUtil.getNvgu());
    }

    public abstract void onRender(Render2DEvent event, NVGU nvgu);

    public void addX(float x) {
        this.x += x;
    }

    public void addY(float y) {
        this.y += y;
    }
}