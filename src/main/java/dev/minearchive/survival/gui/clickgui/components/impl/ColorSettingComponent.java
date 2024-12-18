package dev.minearchive.survival.gui.clickgui.components.impl;

import dev.minearchive.survival.gui.clickgui.components.Component;
import dev.minearchive.survival.mods.setting.settings.ColorSetting;
import dev.minearchive.survival.util.ColorUtil;
import dev.minearchive.survival.util.Fonts;
import dev.minearchive.survival.util.NVGU;
import dev.minearchive.survival.util.Util;
import dev.minearchive.survival.util.easing.Animation;
import dev.minearchive.survival.util.easing.EnumEasing;
import dev.minearchive.survival.util.input.InputAction;
import dev.minearchive.survival.util.nanovg.Alignment;
import dev.minearchive.survival.util.nanovg.Border;
import org.lwjgl.glfw.GLFW;

public class ColorSettingComponent extends Component<ColorSetting> implements Util {

    private boolean isOpen = false;
    private final Animation height;

    public ColorSettingComponent(ColorSetting setting) {
        super(setting);
        height = new Animation(60, EnumEasing.CUBIC.getEasing());
    }

    @Override
    public float draw(NVGU vg, float mouseX, float mouseY, float delta) {
        height.animateTo(isOpen ? 150 : 50, 120);
        this.delta = delta;
        vg.roundedRectangleBorder(20, 10 + delta, 1110 - 50, height.getValue(), 5, 0.5f, ColorUtil.getOutline(), Border.INSIDE);
        Fonts.REGULAR.drawText(getSetting().getDisplay(), 30, 35 + delta, 26, ColorUtil.getOnSurface(), Alignment.LEFT_MIDDLE);
        vg.roundedRectangle(1005, 20 + delta, 60, 30, 10, ColorUtil.getPrimary());
        vg.roundedRectangleBorder(1005, 20 + delta, 60, 30, 10, 2, ColorUtil.getOutline(), Border.MIDDLE);
        return height.getValue() + 10;
    }

    @Override
    public void onMouse(double mouseX, double mouseY, int button, InputAction action) {
        float x0 = mc.getWindow().getWidth() / 2f - 520;
        float y0 = mc.getWindow().getHeight() / 2f - 390;
        if (x0 + 20 < mouseX && mouseX < x0 + 1080 &&
            y0 + 10 + delta < mouseY && mouseY < y0 + 60 + delta && action == InputAction.PRESS && button == GLFW.GLFW_MOUSE_BUTTON_RIGHT) {
            isOpen = !isOpen;
        }
    }
}
