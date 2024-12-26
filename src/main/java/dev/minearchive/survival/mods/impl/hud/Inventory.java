package dev.minearchive.survival.mods.impl.hud;

import dev.minearchive.survival.events.Render2DEvent;
import dev.minearchive.survival.mods.Hud;
import dev.minearchive.survival.mods.ModInfo;
import dev.minearchive.survival.mods.Tag;
import dev.minearchive.survival.util.Fonts;
import dev.minearchive.survival.util.NVGU;
import dev.minearchive.survival.util.nanovg.Alignment;
import net.minecraft.item.ItemStack;

import java.awt.*;

@ModInfo(name = "Inventory", description = "Shows your inventory", tag = Tag.HUD)
public class Inventory extends Hud {
    @Override
    public void onRender(Render2DEvent event, NVGU nvgu) {
        if (nullCheck()) return;
        nvgu.roundedRectangle(getX(), getY(), 10 + 40 * 9, 10 + 40 * 3, 5, new Color(0x5A000000, true));
        nvgu.rectangle(getX() + 10, getY() + 40 + 5, 40 * 9 - 10, 2, new Color(0x99FFFFFF, true));
        nvgu.rectangle(getX() + 10, getY() + 40 * 2 + 5, 40 * 9 - 10, 2, new Color(0x99FFFFFF, true));

        nvgu.rectangle(getX() + 5 + 40, getY() + 10, 2, 40 * 3 - 10, new Color(0x99FFFFFF, true));
        nvgu.rectangle(getX() + 5 + 40 * 2, getY() + 10, 2, 40 * 3 - 10, new Color(0x99FFFFFF, true));
        nvgu.rectangle(getX() + 5 + 40 * 3, getY() + 10, 2, 40 * 3 - 10, new Color(0x99FFFFFF, true));
        nvgu.rectangle(getX() + 5 + 40 * 4, getY() + 10, 2, 40 * 3 - 10, new Color(0x99FFFFFF, true));
        nvgu.rectangle(getX() + 5 + 40 * 5, getY() + 10, 2, 40 * 3 - 10, new Color(0x99FFFFFF, true));
        nvgu.rectangle(getX() + 5 + 40 * 6, getY() + 10, 2, 40 * 3 - 10, new Color(0x99FFFFFF, true));
        nvgu.rectangle(getX() + 5 + 40 * 7, getY() + 10, 2, 40 * 3 - 10, new Color(0x99FFFFFF, true));
        nvgu.rectangle(getX() + 5 + 40 * 8, getY() + 10, 2, 40 * 3 - 10, new Color(0x99FFFFFF, true));

        nvgu.endFrame().beginFrame(mc.getWindow().getWidth(), mc.getWindow().getHeight());
        event.context().getMatrices().translate(5, 5, 0);
        for (int i = 1; i < 4; i++) {
            for (int j = 0; j < 9; j++) {
                ItemStack stack = mc.player.getInventory().getStack(i * 9 + j);
                event.context().drawItem(stack, (int) ((int) (getX() + 40 * j) / mc.getWindow().getScaleFactor()), (int) ((int) (getY() + 40 * (i - 1)) / mc.getWindow().getScaleFactor()));
                if (stack.isDamageable() && stack.getDamage() != stack.getMaxDamage()) {
                    Color damagedColor;
                    float damage = (float) stack.getDamage() / (float) stack.getMaxDamage();

                }
                if (stack.getCount() != 1) Fonts.REGULAR.drawText(String.valueOf(stack.getCount()), getX() + 45 + 40 * (j), getY() + 45 + 40 * (i - 1), 18, stack.getCount() < 0 ? Color.RED : Color.WHITE, Alignment.RIGHT_BOTTOM);
            }
        }
        event.context().getMatrices().translate(-5, -5, 0);
        setWidth(200);
        setHeight(55);
        setRound(5);
    }
}
