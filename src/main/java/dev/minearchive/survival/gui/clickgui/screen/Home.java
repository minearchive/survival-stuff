package dev.minearchive.survival.gui.clickgui.screen;

import dev.minearchive.survival.gui.clickgui.interfaces.Screen;
import dev.minearchive.survival.util.ColorUtil;
import dev.minearchive.survival.util.Fonts;
import dev.minearchive.survival.util.NVGU;
import dev.minearchive.survival.util.input.InputAction;
import dev.minearchive.survival.util.nanovg.Alignment;
import dev.minearchive.survival.util.nanovg.Border;
import net.minecraft.client.util.Session;

import java.awt.*;

public class Home implements Screen {

    @Override
    public void render(NVGU vg, int mouseX, int mouseY, float delta) {
        //top logo
        vg.createTexture("home-logo", Home.class.getResourceAsStream("/assets/survival-mod/images/home.jpg"));
        vg.centeredTexturedRoundedRectangle(30, 30, width - 60, 300, 10, "home-logo", 1980, 1080,  width - 60, 572.4f)
                        .roundedRectangle(30, 30, width - 60, 300, 10, new Color(0x59000000, true));
        Fonts.SANS_MEDIUM.drawText("Improve Your Minecraft QoL", width / 2f, height / 6f, 64, new Color(0xfbfcfe), Alignment.CENTER_MIDDLE);
        Fonts.REGULAR.drawText("Survival Mod is the Minecraft mod for the latest version that improves QoL.", width / 2f, height / 3.6f, 26, new Color(0xfbfcfe), Alignment.CENTER_MIDDLE);
        Fonts.REGULAR.drawText("Designed based on Material 3 invented by Google.", width / 2f, height / 3.6f + (Fonts.REGULAR.getHeight(26) * 1.2f), 26, new Color(0xfbfcfe), Alignment.CENTER_MIDDLE);

        //Some info
        vg.roundedRectangleBorder(30, 360, width - 60, 300, 10, 0.5f, ColorUtil.getOutline(), Border.MIDDLE);
        Session session = mc.getSession();
        Fonts.REGULAR.drawText("Hi " + session.getUsername() + "!", 50, 380, 32, ColorUtil.getOnSurface(), Alignment.LEFT_TOP);

        Fonts.REGULAR.drawText("Todo:Add Something here", width / 2f, 520, 24, ColorUtil.getOnSurface(), Alignment.CENTER_MIDDLE);

        /*
         ここにワンちゃん再生バーいれるのもありかもしれないわかんないけど
         */
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button, InputAction action) {

    }

    @Override
    public void onKey(int keyCode, int scanCode, int modifiers, InputAction action) {

    }

    @Override
    public void onScroll(double mouseX, double mouseY, double amount) {

    }
}
