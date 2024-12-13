package dev.minearchive.survival.gui;

import dev.minearchive.survival.util.input.InputAction;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class GuiScreen extends Screen {

    private final MinecraftClient mc = MinecraftClient.getInstance();

    public GuiScreen(String title) {
        super(Text.of(title));
    }

    public void onMouseClicked(double mouseX, double mouseY, int button, InputAction action) {
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        onMouseClicked(mouseX * mc.getWindow().getScaleFactor(), mouseY * mc.getWindow().getScaleFactor(), button, InputAction.PRESS);
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        onMouseClicked(mouseX * mc.getWindow().getScaleFactor(), mouseY * mc.getWindow().getScaleFactor(), button, InputAction.RELEASE);
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        onMouseClicked(mouseX * mc.getWindow().getScaleFactor(), mouseY * mc.getWindow().getScaleFactor(), button, InputAction.REPEAT);
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }
}
