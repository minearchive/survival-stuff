package dev.minearchive.survival.mixin;

import dev.minearchive.survival.mods.Hud;
import dev.minearchive.survival.mods.ModsManager;
import dev.minearchive.survival.util.NanoVGUtil;
import dev.minearchive.survival.util.Util;
import dev.minearchive.survival.util.input.MouseUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ChatScreen;
import org.joml.Math;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@Mixin(ChatScreen.class)
public class MixinChatScreen implements Util {

    @Inject(method = "render", at = @At("TAIL"))
    public void onRender(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        final double x = mc.mouse.getX();
        final double y = mc.mouse.getY();
        NanoVGUtil.setupAndDraw(false, vg -> ModsManager.getModList().stream().filter(mod -> mod instanceof Hud && mod.isEnable()).map(mod -> (Hud) mod).forEach(mod -> {
            if (MouseUtil.hover((float) x, (float) y, mod.getX(), mod.getY(), mod.getWidth(), mod.getHeight())) {
                vg.roundedRectangle(mod.getX(), mod.getY(), mod.getWidth(), mod.getHeight(), mod.getRound(), new Color(0x49FFFFFF, true));

                if (GLFW.glfwGetMouseButton(mc.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_PRESS) {
                    mod.setDragging(true);
                }
            }

            if (mod.isDragging() && mod.isDraggable()) {
                mod.addX((float) (x - mod.getLastX()));
                mod.addY((float) (y - mod.getLastY()));
                mod.setLastX((float) x);
                mod.setLastY((float) y);
            }

            if (GLFW.glfwGetMouseButton(mc.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_RELEASE) {
                mod.setDragging(false);
            }

            mod.setX(org.joml.Math.clamp(0, MinecraftClient.getInstance().getWindow().getWidth() - mod.getWidth(), mod.getX()));
            mod.setY(Math.clamp(0, MinecraftClient.getInstance().getWindow().getHeight() - mod.getHeight(), mod.getY()));
        }));
    }
}
