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
        nvgu.beginFrame(mcScale ? mc.getWindow().getScaledWidth() : mc.getWindow().getWidth(),
                mcScale ? mc.getWindow().getScaledHeight() : mc.getWindow().getHeight()
        );

        nvg.accept(nvgu);

        nvgu.endFrame();
    }

}