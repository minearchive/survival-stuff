package dev.minearchive.survival.events;

import net.minecraft.client.gui.DrawContext;

public record Render2DEvent(DrawContext context, float tickDelta) {
}

