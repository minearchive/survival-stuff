package dev.minearchive.survival.gui.clickgui.screen;

import dev.minearchive.survival.gui.clickgui.ClickGui;
import dev.minearchive.survival.gui.clickgui.dialog.ModuleDialog;
import dev.minearchive.survival.gui.clickgui.interfaces.Screen;
import dev.minearchive.survival.mods.Mod;
import dev.minearchive.survival.mods.ModsManager;
import dev.minearchive.survival.util.ColorUtil;
import dev.minearchive.survival.util.Fonts;
import dev.minearchive.survival.util.MaterialIcon;
import dev.minearchive.survival.util.NVGU;
import dev.minearchive.survival.util.animation.ColorAnimation;
import dev.minearchive.survival.util.easing.Animation;
import dev.minearchive.survival.util.easing.EnumEasing;
import dev.minearchive.survival.util.input.InputAction;
import dev.minearchive.survival.util.nanovg.Alignment;
import dev.minearchive.survival.util.nanovg.Border;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;


public class Mods implements Screen {

    private List<ModButton> buttons = new ArrayList<>();
    private final Animation scrollAnim = new Animation(0, EnumEasing.CUBIC.getEasing());
    private double scrollValue = 0;

    public Mods() {
        int i = 0;
        for (Mod mod : ModsManager.getModList()) {
            buttons.add(new ModButton(mod, i));
            i++;
        }
    }

    @Override
    public void render(NVGU vg, int mouseX, int mouseY, float delta) {
        scrollAnim.animateTo((float) Math.min(scrollValue, 0), 300);

        //search bar
        vg.roundedRectangle(50, 40, width - 100, 50, 25, ColorUtil.getInverseOnSurface());
        vg.roundedRectangleBorder(50, 40, width - 100, 50, 25, 0.5f, ColorUtil.getOutline(), Border.INSIDE);
        Fonts.icon.drawText(MaterialIcon.SEARCH, 75, 65, 28, ColorUtil.getOnSurface(), Alignment.CENTER_MIDDLE);
        Fonts.REGULAR.drawText("何時か実装されるでしょう。", 95, 65, 24, ColorUtil.getOnSurface(), Alignment.LEFT_MIDDLE);
        vg.scissor(20, 110, width - 40, height - 130, () -> {
            for (ModButton button : buttons) {
                button.render(vg, mouseX, mouseY, scrollAnim.getValue());
            }
        });
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button, InputAction action) {
        buttons.forEach(b -> b.mouseClicked(mouseX, mouseY, button, action));
    }

    @Override
    public void onKey(int keyCode, int scanCode, int modifiers, InputAction action) {
    }

    @Override
    public void onScroll(double mouseX, double mouseY, double amount) {
        this.scrollValue = Math.min(0, Math.max(scrollValue + amount * 50, 780 - 130 - Math.abs(buttons.size() / 2f) * 80));
    }

    public static class ModButton {

        private final Animation buttonAnim;
        private final ColorAnimation colorAnimation;
        private final ColorAnimation backgroundAnimation;
        private final Mod mod;
        private final int i;
        private float scroll;
        private int x, y, width, height;

        public ModButton(Mod mod, int i) {
            this.mod = mod;
            this.i = i;
            this.buttonAnim = new Animation(mod.isEnable() ? 1 : 0, EnumEasing.SINE.getEasing());
            this.colorAnimation = new ColorAnimation(mod.isEnable() ? ColorUtil.getInversePrimary() : ColorUtil.getSurfaceVariant(), EnumEasing.SINE.getEasing());
            this.backgroundAnimation = new ColorAnimation(mod.isEnable() ? ColorUtil.getPrimary() : ColorUtil.getOnSecondary(), EnumEasing.SINE.getEasing());
        }

        public void render(NVGU vg, int mouseX, int mouseY, float deltaY) {
            x = (i % 2 == 1) ? 560 : 30;
            y = (int) (i / 2f) * 80 + 120;
            this.scroll = deltaY;
            buttonAnim.animateTo(mod.isEnable() ? 1 : 0, 150);
            colorAnimation.setAnimation(mod.isEnable() ? ColorUtil.getPrimaryContainer() : ColorUtil.getOutline(), 150);
            backgroundAnimation.setAnimation(mod.isEnable() ? ColorUtil.getPrimary() : ColorUtil.getSurfaceVariant(), 150);

            //base
            vg.roundedRectangle(x, y + scroll, 520, 70, 5, ColorUtil.getInverseOnSurface());
            vg.roundedRectangleBorder(x, y + scroll, 520, 70, 5, 0.3f, ColorUtil.getOutline(), Border.INSIDE);

            //name and description
            Fonts.REGULAR.drawText(mod.getName(), x + 20, y + 10 + scroll, 24, ColorUtil.getOnSurface(), Alignment.LEFT_TOP);
            Fonts.REGULAR.drawText(mod.getDescription(), x + 20, y + 40 + scroll, 18, ColorUtil.getOnSurfaceVariant(), Alignment.LEFT_TOP);

            //button
            vg.roundedRectangle(x + 435, y + 17.5f + scroll, 65, 35, 17.5f, backgroundAnimation.getColor());
            vg.roundedRectangleBorder(x + 435, y + 17.5f + scroll, 65, 35, 17.5f, 0.4f, ColorUtil.getOutline(), Border.INSIDE);
            vg.roundedRectangle(x + 440 + buttonAnim.getValue() * 26, y + 21.5f + scroll, 27.5f, 27.5f, 13.75f, colorAnimation.getColor());
        }

        public void mouseClicked(double mouseX, double mouseY, int button, InputAction action) {
            float x0 = mc.getWindow().getWidth() / 2f - 520, y0 = mc.getWindow().getHeight() / 2f - 390;
            if (x0 + x < mouseX && mouseX < x0 + x + 520 &&
                y0 + y + scroll < mouseY && mouseY < y0 + y + 70 + scroll && action == InputAction.PRESS) {
                if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT) mod.toggle();
                if (button == GLFW.GLFW_MOUSE_BUTTON_RIGHT) ClickGui.dialog = new ModuleDialog(this.mod);
            }
        }

        public void onKey(int keyCode, int scanCode, int modifiers, InputAction action) {

        }
    }
}
