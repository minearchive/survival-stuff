package dev.minearchive.survival.gui.clickgui.components.impl;

import dev.minearchive.survival.gui.clickgui.components.Component;
import dev.minearchive.survival.mods.setting.settings.BooleanSetting;
import dev.minearchive.survival.util.ColorUtil;
import dev.minearchive.survival.util.Fonts;
import dev.minearchive.survival.util.NVGU;
import dev.minearchive.survival.util.Util;
import dev.minearchive.survival.util.animation.ColorAnimation;
import dev.minearchive.survival.util.easing.Animation;
import dev.minearchive.survival.util.easing.EnumEasing;
import dev.minearchive.survival.util.input.InputAction;
import dev.minearchive.survival.util.nanovg.Alignment;
import dev.minearchive.survival.util.nanovg.Border;
import org.lwjgl.glfw.GLFW;

public class BooleanSettingComponent extends Component<BooleanSetting> implements Util {

    private final Animation buttonAnim;
    private final ColorAnimation colorAnimation;
    private final ColorAnimation backgroundAnimation;
    private long startMS = 0;

    public BooleanSettingComponent(BooleanSetting setting) {
        super(setting);
        this.buttonAnim = new Animation(setting.getValue() ? 1 : 0, EnumEasing.SINE.getEasing());
        this.colorAnimation = new ColorAnimation(setting.getValue() ? ColorUtil.getInversePrimary() : ColorUtil.getSurfaceVariant(), EnumEasing.SINE.getEasing());
        this.backgroundAnimation = new ColorAnimation(setting.getValue() ? ColorUtil.getPrimary() : ColorUtil.getOnSecondary(), EnumEasing.SINE.getEasing());
    }

    @Override
    public float draw(NVGU vg, float mouseX, float mouseY, float delta) {
        float x0 = mc.getWindow().getWidth() / 2f - 520, y0 = mc.getWindow().getHeight() / 2f - 390;
        if (x0 + 20 < mouseX * mc.getWindow().getScaleFactor() && mouseX * mc.getWindow().getScaleFactor() < x0 + 1070 &&
                y0 + delta + 10 < mouseY * mc.getWindow().getScaleFactor() && mouseY * mc.getWindow().getScaleFactor() < y0 + delta + 60) {
            if (startMS == 0) startMS = System.currentTimeMillis();
            if (System.currentTimeMillis() - startMS > 600 && getSetting().getDescription() != null) {
                vg.roundedRectangle(mouseX , mouseY - 40, Fonts.REGULAR.getWidth(getSetting().getDescription(), 24), 30, 5, ColorUtil.getPrimary());
            }
        } else startMS = 0;
        this.delta = delta;
        buttonAnim.animateTo(getSetting().getValue() ? 1 : 0, 150);
        colorAnimation.setAnimation(getSetting().getValue() ? ColorUtil.getPrimaryContainer() : ColorUtil.getOutline(), 150);
        backgroundAnimation.setAnimation(getSetting().getValue() ? ColorUtil.getPrimary() : ColorUtil.getBackground(), 150);

        vg.roundedRectangleBorder(20, 10 + delta, 1110 - 50, 50, 5, 0.5f, ColorUtil.getOutline(), Border.INSIDE);
        Fonts.REGULAR.drawText(getSetting().getDisplay(), 30, 35 + delta, 26, ColorUtil.getOnSurface(), Alignment.LEFT_MIDDLE);

        vg.roundedRectangle(435 + 570, 17.5f + delta, 65, 35, 17.5f, backgroundAnimation.getColor());
        vg.roundedRectangleBorder(435 + 570, 17.5f + delta, 65, 35, 17.5f, 0.4f, ColorUtil.getOutline(), Border.INSIDE);
        vg.roundedRectangle(440 + 570 + buttonAnim.getValue() * 26, 21.5f + delta, 27.5f, 27.5f, 13.75f, colorAnimation.getColor());
        return 60;
    }

    @Override
    public void onMouse(double mouseX, double mouseY, int button, InputAction action) {
        float x0 = mc.getWindow().getWidth() / 2f - 520, y0 = mc.getWindow().getHeight() / 2f - 390;
        if (x0 + 20 < mouseX && mouseX < x0 + 1070 &&
            y0 + delta + 10 < mouseY && mouseY < y0 + delta + 60 && action == InputAction.PRESS && button == GLFW.GLFW_MOUSE_BUTTON_1) {
            getSetting().toggle();
        }
    }
}
