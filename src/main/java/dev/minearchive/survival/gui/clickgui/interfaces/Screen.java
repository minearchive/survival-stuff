package dev.minearchive.survival.gui.clickgui.interfaces;

import dev.minearchive.survival.util.NVGU;
import dev.minearchive.survival.util.input.InputAction;
import net.minecraft.client.MinecraftClient;

public interface Screen {
    MinecraftClient mc = MinecraftClient.getInstance();
    int width = 1110;
    int height = 780;
    void render(NVGU vg, int mouseX, int mouseY, float delta);
    void mouseClicked(double mouseX, double mouseY, int button, InputAction action);
    void onKey(int keyCode, int scanCode, int modifiers, InputAction action);
    void onScroll(double mouseX, double mouseY, double amount);
}
