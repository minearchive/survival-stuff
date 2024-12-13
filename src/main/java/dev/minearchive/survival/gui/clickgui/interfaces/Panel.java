package dev.minearchive.survival.gui.clickgui.interfaces;

import dev.minearchive.survival.util.NVGU;
import dev.minearchive.survival.util.input.InputAction;

public interface Panel {
    void render(NVGU vg, int mouseX, int mouseY, float delta);
    void mouseClicked(double mouseX, double mouseY, int button, InputAction action);
    void onKey(int keyCode, int scanCode, int modifiers, InputAction action);
}
