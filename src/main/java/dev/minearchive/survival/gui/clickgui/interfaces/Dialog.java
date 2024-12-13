package dev.minearchive.survival.gui.clickgui.interfaces;

import dev.minearchive.survival.util.NVGU;
import dev.minearchive.survival.util.input.InputAction;
import net.minecraft.client.MinecraftClient;

public class Dialog {

    public MinecraftClient mc = MinecraftClient.getInstance();

    public void render(NVGU vg, int mouseX, int mouseY, float delta) {

    }

    public void mouseClicked(double mouseX, double mouseY, int button, InputAction action) {

    }

    public void onKey(int keyCode, int scanCode, int modifiers, InputAction action) {

    }

    public void onScroll(double mouseX, double mouseY, double amount) {

    }

    public void destroy() {

    }
}
