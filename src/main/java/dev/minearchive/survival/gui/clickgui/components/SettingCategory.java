package dev.minearchive.survival.gui.clickgui.components;

import dev.minearchive.survival.util.ColorUtil;
import dev.minearchive.survival.util.Fonts;
import dev.minearchive.survival.util.NVGU;
import dev.minearchive.survival.util.Util;
import dev.minearchive.survival.util.easing.Animation;
import dev.minearchive.survival.util.easing.EnumEasing;
import dev.minearchive.survival.util.input.InputAction;
import dev.minearchive.survival.util.nanovg.Alignment;
import lombok.Getter;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SettingCategory implements Util {
    private final String title;
    private final List<Component<?>> components = new ArrayList<>();
    private Animation openAnim = new Animation(0.0f, EnumEasing.LINEAR.getEasing());
    private boolean isOpen = true;
    private float translated = 0;
    private float delta = 0;

    public SettingCategory(String title) {
        this.title = title;
    }

    public void add(Component<?> component) {
        components.add(component);
    }

    public float draw(NVGU vg, float mouseX, float mouseY, float delta) {
        int width = 1110;
        translated = 0;
        this.delta = delta;
        vg.scissor(0, delta, width, openAnim.getValue(), () -> {
            Fonts.REGULAR.drawText(title, 20, 30 + delta, 24, ColorUtil.getOnSurfaceVariant(), Alignment.LEFT_MIDDLE);
            vg.rectangle(30 + Fonts.REGULAR.getWidth(title, 28), 30 - 1 + delta, width - (60 + Fonts.REGULAR.getWidth(title, 28)), 2, ColorUtil.getOnSurfaceVariant());
            for (Component<?> component : components) {
                add(component.draw(vg, mouseX, mouseY, delta + 35 + (Fonts.REGULAR.getHeight(24) / 2) + get()));
            }
        });
        openAnim.animateTo(isOpen ? 35 + (Fonts.REGULAR.getHeight(24) / 2) + get() : 35 + (Fonts.REGULAR.getHeight(24) / 2), 100);
        return openAnim.getValue();
    }

    public void onMouse(double mouseX, double mouseY, int button, InputAction action) {
        float x0 = mc.getWindow().getWidth() / 2f - 520, y0 = mc.getWindow().getHeight() / 2f - 390;
        if (x0 < mouseX && mouseX < x0 + 1110 &&
            y0 + delta < mouseY && mouseY < y0 + delta + (Fonts.REGULAR.getHeight(24) / 2) + 35 &&
                button == GLFW.GLFW_MOUSE_BUTTON_RIGHT && action == InputAction.PRESS) {
            isOpen = !isOpen;
            return;
        }
        if (!isOpen) return;
        components.forEach(component -> component.onMouse(mouseX, mouseY, button, action));
    }

    private void add(float var) {
        translated += var;
    }

    private float get() {
        return translated;
    }

}
