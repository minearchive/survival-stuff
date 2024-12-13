package dev.minearchive.survival.gui.clickgui.components;

import dev.minearchive.survival.mods.setting.Setting;
import dev.minearchive.survival.util.NVGU;
import dev.minearchive.survival.util.Util;
import dev.minearchive.survival.util.input.InputAction;
import lombok.Getter;

@Getter
public class Component<T extends Setting<?>> implements Util {

    private final T setting;
    public float delta;
    public final float x0 = mc.getWindow().getWidth() / 2f - 520;
    public final float y0 = mc.getWindow().getHeight() / 2f - 390;


    public Component(T setting) {
        this.setting = setting;
    }

    public float draw(NVGU vg, float mouseX, float mouseY, float delta) {
        return 0;
    }

    public void onMouse(double mouseX, double mouseY, int button, InputAction action) {

    }

    public void onKey(int keyCode, int scanCode, int modifiers, InputAction action) {

    }
}
