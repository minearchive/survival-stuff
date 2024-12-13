package dev.minearchive.survival.mods.impl.hud;

import dev.minearchive.survival.util.NVGU;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class Hud {

    boolean draggable;
    float x = 0, y = 0, width = 0, height = 0;
    float dragX, dragY;

    public Hud() {
        this(false);
    }

    public Hud(boolean draggable) {
        this.draggable = draggable;
    }

    public abstract void onRender(NVGU nvgu);

}