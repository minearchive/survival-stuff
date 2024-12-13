package dev.minearchive.survival.util;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import lombok.Getter;

import java.util.function.Consumer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;

public class NanoVGUtil implements Util {

    @Getter
    private static NVGU nvgu;

    public static void init() {
        nvgu = new NVGU().create();
    }

    public static void setupAndDraw(boolean mcScale, Consumer<NVGU> nvg) {
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GL_SRC_ALPHA, GL_ONE);
        RenderSystem.enableDepthTest();
        RenderSystem.depthFunc(GL_LESS);
        RenderSystem.clear(GL_DEPTH_BUFFER_BIT, false);
        nvgu.beginFrame(mcScale ? mc.getWindow().getScaledWidth() : mc.getWindow().getWidth(),
                mcScale ? mc.getWindow().getScaledHeight() : mc.getWindow().getHeight()
        );

        nvg.accept(nvgu);

        nvgu.endFrame();
        GlStateManager._disableCull();
        GlStateManager._disableDepthTest();
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SrcFactor.ZERO, GlStateManager.DstFactor.ONE);
    }

}