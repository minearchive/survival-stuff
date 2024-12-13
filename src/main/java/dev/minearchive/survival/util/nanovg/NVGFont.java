package dev.minearchive.survival.util.nanovg;

import dev.minearchive.survival.util.NVGU;
import dev.minearchive.survival.util.NanoVGUtil;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.nio.ByteBuffer;

import static org.lwjgl.nanovg.NanoVG.*;
import static org.lwjgl.nanovg.NanoVG.nvgClosePath;

@Getter @Setter
public class NVGFont {

    private final String identifier, path;
    private ByteBuffer buffer;

    public NVGFont(String identifier, String path) {
        this.identifier = identifier;
        this.path = path;
    }

    public void drawText(String text, final float x, final float y, float size, Color color, Alignment alignment) {
        NVGU vg = NanoVGUtil.getNvgu();
        vg.text(text, x, y, color, identifier, size, alignment);
    }

    public void drawTextBox(String text, final float x, final float y, float width, float size, Color color) {
        NVGU vg = NanoVGUtil.getNvgu();
        nvgBeginPath(vg.getHandle());

        nvgFillColor(vg.getHandle(), vg.createAndStoreColour(color));
        nvgFontFace(vg.getHandle(), identifier);
        nvgFontSize(vg.getHandle(), size);
        nvgTextBox(vg.getHandle(), x, y + 1, width, text);

        nvgClosePath(vg.getHandle());
    }

    public float getHeight(float size) {
        return NanoVGUtil.getNvgu().textHeight(identifier, size);
    }

    public float getWidth(String text, float size) {
        return NanoVGUtil.getNvgu().textWidth(text, identifier, size);
    }

}
