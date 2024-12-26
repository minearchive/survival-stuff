package dev.minearchive.survival.gui.clickgui;

import com.google.common.eventbus.Subscribe;
import dev.minearchive.survival.Client;
import dev.minearchive.survival.events.WindowResizeEvent;
import dev.minearchive.survival.gui.GuiScreen;
import dev.minearchive.survival.gui.clickgui.interfaces.Children;
import dev.minearchive.survival.gui.clickgui.interfaces.Panel;
import dev.minearchive.survival.gui.clickgui.interfaces.Screen;
import dev.minearchive.survival.gui.clickgui.interfaces.Dialog;
import dev.minearchive.survival.gui.clickgui.screen.Home;
import dev.minearchive.survival.gui.clickgui.screen.Mods;
import dev.minearchive.survival.gui.clickgui.screen.Setting;
import dev.minearchive.survival.util.*;
import dev.minearchive.survival.util.easing.Animation;
import dev.minearchive.survival.util.easing.EnumEasing;
import dev.minearchive.survival.util.input.InputAction;
import dev.minearchive.survival.util.nanovg.Alignment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.nanovg.NanoVG;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static dev.minearchive.survival.util.MaterialIcon.*;

public class ClickGui extends GuiScreen {
    public ClickGui() {
        super("ClickGui");
        Client.register(this);
        INSTANCE = this;
    }

    public static ClickGui INSTANCE;

    private static final MinecraftClient mc = MinecraftClient.getInstance();
    public static final SidePanel sidePanel = new SidePanel();
    public static final MainPanel mainPanel = new MainPanel();
    public static Dialog dialog = null;
    private final List<Panel> panels = List.of(mainPanel, sidePanel);
    private final Animation scale = new Animation(0.0f, EnumEasing.CUBIC.getEasing());
    private final Animation dialogBackground = new Animation(0, EnumEasing.CUBIC.getEasing());
    public static boolean isOpen;

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        scale.animateTo(1, 480);
        dialogBackground.animateTo(dialog == null ? 0 : 100, 480);
        float centerX = mc.getWindow().getWidth() / 2f;
        float centerY = mc.getWindow().getHeight() / 2f;
        NanoVGUtil.setupAndDraw(false, vg -> vg.scope(() -> {
            vg.scale(centerX, centerY, scale.getValue());
            NanoVG.nvgGlobalAlpha(vg.getHandle(), scale.getValue());
            vg.roundedRectangle(centerX - 605, centerY - 405, 1210, 810, 15, ColorUtil.getInverseOnSurface());
            panels.forEach(panel -> panel.render(vg, mouseX, mouseY, delta));
            vg.rectangle(0, 0, mc.getWindow().getWidth(), mc.getWindow().getHeight(), ColorUtil.addAlpha(Color.BLACK.getRGB(), dialogBackground.getValue()));
            if (dialog != null) dialog.render(vg, mouseX, mouseY, delta);
        }));
    }

    @Override
    public void onMouseClicked(double mouseX, double mouseY, int button, InputAction action) {
        if (dialog != null) return;
        panels.forEach(panel -> panel.mouseClicked(mouseX, mouseY, button, action));
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (dialog != null && keyCode == GLFW.GLFW_KEY_ESCAPE) {
            dialog = null;
            return false;
        }
        panels.forEach(panel -> panel.onKey(keyCode, scanCode, modifiers, InputAction.PRESS));
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        panels.forEach(panel -> panel.onKey(keyCode, scanCode, modifiers, InputAction.RELEASE));
        return super.keyReleased(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        mainPanel.onScroll(mouseX, mouseY, amount);
        return super.mouseScrolled(mouseX, mouseY, amount);
    }

    @Subscribe
    public void resize(WindowResizeEvent event) {
        sidePanel.onResize();
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    public ClickGui reset() {
        scale.setValue(0);
        return this;
    }

    public static class SidePanel implements Panel {
        private final MinecraftClient mc = MinecraftClient.getInstance();
        private final List<Button> buttons = new ArrayList<>();
        private final Animation widthAnimation = new Animation(0.3f, EnumEasing.CUBIC.getEasing());
        private final Animation cornerAnimation = new Animation(10, EnumEasing.CUBIC.getEasing());

        public SidePanel() {
            buttons.addAll(
                    List.of(
                            new Button(() -> HOME, () -> "Home",
                                    () -> mc.getWindow().getWidth() / 2f - 590,
                                    () -> mc.getWindow().getHeight() / 2f - 370, () -> mainPanel.setScreen(new Home())),
                            new Button(() -> READER, () -> "Mods",
                                    () -> mc.getWindow().getWidth() / 2f - 590,
                                    () -> mc.getWindow().getHeight() / 2f - 310, () -> mainPanel.setScreen(new Mods())),
                            new Button(() -> SETTING, () -> "Settings",
                                    () -> mc.getWindow().getWidth() / 2f - 590,
                                    () -> mc.getWindow().getHeight() / 2f - 250, () -> mainPanel.setScreen(new Setting())),
                            new RotateButton(() -> AUTO_RENEW, () -> ThemeUtils.INSTANCE.isDark() ? "To Light" : "To Dark",
                                    () -> mc.getWindow().getWidth() / 2f - 590,
                                    () -> mc.getWindow().getHeight() / 2f + 280, () -> ThemeUtils.INSTANCE.changeMode(!ThemeUtils.INSTANCE.isDark())),
                            new Button(() -> isOpen ? BACK_IOS : FORWARD_IOS, () -> "Close menu",
                                    () -> mc.getWindow().getWidth() / 2f - 590,
                                    () -> mc.getWindow().getHeight() / 2f + 340, () -> isOpen = !isOpen)
                    )
            );
        }

        @Override
        public void render(NVGU vg, int mouseX, int mouseY, float delta) {
            float centerX = mc.getWindow().getWidth() / 2f;
            float centerY = mc.getWindow().getHeight() / 2f;
            widthAnimation.animateTo(isOpen ? 1 : 0.325f, 320);
            cornerAnimation.animateTo(isOpen ? 30 : 15, 320);
            vg.scissor(centerX - 605, centerY - 405, 230 * widthAnimation.getValue(), 810, () -> {
                vg.roundedRectangle(centerX - 605, centerY - 405, 230 * widthAnimation.getValue(), 810, 15, cornerAnimation.getValue(), cornerAnimation.getValue(), 15, ColorUtil.getBackground());
                buttons.forEach(button -> button.render(vg, mouseX, mouseY, delta));
            });
        }

        @Override
        public void mouseClicked(double mouseX, double mouseY, int button, InputAction action) {
            buttons.forEach(button1 -> button1.mouseClicked(mouseX, mouseY, button, action));
        }

        @Override
        public void onKey(int keyCode, int scanCode, int modifiers, InputAction action) {

        }

        public void onResize() {
            buttons.forEach(Button::onResize);
        }

        public static class Button implements Children {
            public final Supplier<String> icon, text;
            public float x, y;
            public final Runnable runnable;
            public final Animation alpha = new Animation(0, EnumEasing.LINEAR.getEasing());
            public final Supplier<Float> xs, ys;

            public Button(Supplier<String> icon, Supplier<String> text, Supplier<Float> x, Supplier<Float> y, Runnable runnable) {
                this.icon = icon;
                this.text = text;
                this.x = x.get();
                this.y = y.get();
                this.runnable = runnable;
                this.xs = x;
                this.ys = y;
            }

            @Override
            public void render(NVGU vg, int mouseX, int mouseY, float delta) {
                if (isOpen) alpha.animateTo(255, 100);
                else alpha.animateTo(0, 100);
                Fonts.REGULAR.drawText(text.get(), x + Fonts.icon.getWidth(icon.get(), 36) + 20, y + Fonts.icon.getHeight(36) / 2f + 2.5f, 24, ColorUtil.addAlpha(ColorUtil.getOnSurface().getRGB(), alpha.getValue()), Alignment.LEFT_MIDDLE);
                Fonts.icon.drawText(icon.get(), x + 2, y, 36, ColorUtil.getOnSurface(), Alignment.LEFT_TOP);
            }

            @Override
            public void mouseClicked(double mouseX, double mouseY, int button, InputAction action) {
                if (x - 15 < mouseX && mouseX < x + 25 + Fonts.icon.getWidth(icon.get(), 36) + (isOpen ? Fonts.REGULAR.getWidth(text.get(), 36) : 0) &&
                        y < mouseY && mouseY < y + Fonts.icon.getHeight(36) &&
                        action == InputAction.PRESS) {
                    runnable.run();
                }
            }

            @Override
            public void onKey(int keyCode, int scanCode, int modifiers, InputAction action) {

            }

            public void onResize() {
                this.x = xs.get();
                this.y = ys.get();
            }
        }

        public static class RotateButton extends SidePanel.Button {
            private final Animation rotateAnim = new Animation(0, EnumEasing.CUBIC.getEasing());
            private float animateTo = 0;

            public RotateButton(Supplier<String> icon, Supplier<String> text, Supplier<Float> x, Supplier<Float> y, Runnable runnable) {
                super(icon, text, x, y, runnable);
            }

            @Override
            public void render(NVGU vg, int mouseX, int mouseY, float delta) {
                if (isOpen) alpha.animateTo(255, 100);
                else alpha.animateTo(0, 100);
                rotateAnim.animateTo(animateTo, 480);
                Fonts.REGULAR.drawText(text.get(), x + Fonts.icon.getWidth(icon.get(), 36) + 20, y + Fonts.icon.getHeight(36) / 2f + 2.5f, 24, ColorUtil.addAlpha(ColorUtil.getOnSurface().getRGB(), alpha.getValue()), Alignment.LEFT_MIDDLE);
                vg.rotateDegrees(x + Fonts.icon.getWidth(icon.get(), 36) / 2 + 4, y + Fonts.icon.getHeight(36) / 2, rotateAnim.getValue(), () ->
                        Fonts.icon.drawText(icon.get(), x + 2, y, 36, ColorUtil.getOnSurface(), Alignment.LEFT_TOP)
                );
            }

            @Override
            public void mouseClicked(double mouseX, double mouseY, int button, InputAction action) {
                if (x - 15 < mouseX && mouseX < x + 25 + Fonts.icon.getWidth(icon.get(), 36) + (isOpen ? Fonts.REGULAR.getWidth(text.get(), 36) : 0) &&
                        y < mouseY && mouseY < y + Fonts.icon.getHeight(36) &&
                        action == InputAction.PRESS) {
                    animateTo += 180f;
                    runnable.run();
                }
            }
        }
    }

    public static class MainPanel implements Panel {
        private final MinecraftClient mc = MinecraftClient.getInstance();
        private final Animation overlay = new Animation(1, EnumEasing.CUBIC.getEasing());
        private final Animation up = new Animation(0, EnumEasing.CIRC.getEasing());
        private final Animation alpha = new Animation(0, EnumEasing.CIRC.getEasing());
        public Screen currentScreen = new Home(), oldScreen = null;

        @Override
        public void render(NVGU vg, int mouseX, int mouseY, float delta) {
            up.animateTo(0, 640);
            alpha.animateTo(255, 320);
            overlay.animateTo(isOpen ? 100 : 0, 640);
            float centerX = mc.getWindow().getWidth() / 2f;
            float centerY = mc.getWindow().getHeight() / 2f;
            vg.roundedRectangle(centerX - 520, centerY - 390, 1110, 780, 5, ColorUtil.getInverseOnSurface());
            vg.translate(centerX - 520, centerY - 390);
            vg.scissor(0, 0, 1110, 780, () -> {
                if (oldScreen != null) oldScreen.render(vg, mouseX, mouseY, delta);
                vg.roundedRectangle(0, 0, 1110, 780, 5, ColorUtil.addAlpha(ColorUtil.getInverseOnSurface().getRGB(), alpha.getValue()));
                vg.translate(0, up.getValue());
                if(currentScreen != null) currentScreen.render(vg, mouseX, mouseY, delta);
                vg.roundedRectangle(0, 0, 1110, 780, 5, ColorUtil.addAlpha(ColorUtil.getInverseOnSurface().getRGB(), 255 - alpha.getValue()));
                vg.translate(0, -up.getValue());
            });
            vg.translate(-(centerX - 520), -(centerY - 390));
            vg.roundedRectangle(centerX - 520, centerY - 390, 1110, 780, 5, ColorUtil.addAlpha(ColorUtil.getShadow().getRGB(), overlay.getValue()));
        }

        @Override
        public void mouseClicked(double mouseX, double mouseY, int button, InputAction action) {
            float centerX = mc.getWindow().getWidth() / 2f;
            float centerY = mc.getWindow().getHeight() / 2f;
            if (isOpen) {
                if (centerX - 520 < mouseX && mouseX < centerX - 390 && centerY - 390 < mouseY && mouseY < centerY + 390) return;
                if (centerX - 520 < mouseX && mouseX < centerX + 520 &&
                        centerY - 390 < mouseY && mouseY < centerY + 390 &&
                        action == InputAction.PRESS) {
                    isOpen = false;
                }
            } else {
                if (currentScreen != null) currentScreen.mouseClicked(mouseX, mouseY, button, action);
            }
        }

        @Override
        public void onKey(int keyCode, int scanCode, int modifiers, InputAction action) {
            if (currentScreen != null) currentScreen.onKey(keyCode, scanCode, modifiers, action);
        }

        public void onScroll(double mouseX, double mouseY, double amount) {
            if (currentScreen != null) currentScreen.onScroll(mouseX, mouseY, amount);
        }

        public void setScreen(Screen screen) {
            if (currentScreen.getClass() == screen.getClass()) return;
            this.oldScreen = this.currentScreen;
            this.currentScreen = screen;
            this.up.setValue(50);
            this.alpha.setValue(0);
            isOpen = false;
        }
    }
}
