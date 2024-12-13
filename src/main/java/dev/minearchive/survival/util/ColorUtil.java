package dev.minearchive.survival.util;

import dev.minearchive.survival.util.animation.ColorAnimation;
import dev.minearchive.survival.util.easing.EnumEasing;

import java.awt.*;

public class ColorUtil {

    private static ColorAnimation primary, onPrimary, primaryContainer, onPrimaryContainer, secondary, onSecondary, secondaryContainer, onSecondaryContainer, background, onBackground, surface, surfaceVariant, onSurface, onSurfaceVariant, surfaceContainer, shadow, inverseOnSurface, inversePrimary, outline;

    public static Color getPrimary() {
        if (primary == null) primary = new ColorAnimation(new Color(ThemeUtils.INSTANCE.getMaterialScheme().getPrimary()), EnumEasing.SINE.getEasing());
        else primary.setAnimation(new Color(ThemeUtils.INSTANCE.getMaterialScheme().getPrimary()), 250);
        return primary.getColor();
    }

    public static Color getOnPrimary() {
        if (onPrimary == null) onPrimary = new ColorAnimation(new Color(ThemeUtils.INSTANCE.getMaterialScheme().getOnPrimary()), EnumEasing.SINE.getEasing());
        else onPrimary.setAnimation(new Color(ThemeUtils.INSTANCE.getMaterialScheme().getOnPrimary()), 250);
        return onPrimary.getColor();
    }

    public static Color getPrimaryContainer() {
        if (primaryContainer == null) primaryContainer = new ColorAnimation(new Color(ThemeUtils.INSTANCE.getMaterialScheme().getPrimaryContainer()), EnumEasing.SINE.getEasing());
        else primaryContainer.setAnimation(new Color(ThemeUtils.INSTANCE.getMaterialScheme().getPrimaryContainer()), 250);
        return primaryContainer.getColor();
    }

    public static Color getOnPrimaryContainer() {
        if (onPrimaryContainer == null) onPrimaryContainer = new ColorAnimation(new Color(ThemeUtils.INSTANCE.getMaterialScheme().getOnPrimaryContainer()), EnumEasing.SINE.getEasing());
        else onPrimaryContainer.setAnimation(new Color(ThemeUtils.INSTANCE.getMaterialScheme().getOnPrimaryContainer()), 250);
        return onPrimaryContainer.getColor();
    }

    public static Color getSecondary() {
        if (secondary == null) secondary = new ColorAnimation(new Color(ThemeUtils.INSTANCE.getMaterialScheme().getSecondary()), EnumEasing.SINE.getEasing());
        else secondary.setAnimation(new Color(ThemeUtils.INSTANCE.getMaterialScheme().getSecondary()), 250);
        return secondary.getColor();
    }

    public static Color getOnSecondary() {
        if (onSecondary == null) onSecondary = new ColorAnimation(new Color(ThemeUtils.INSTANCE.getMaterialScheme().getOnSecondary()), EnumEasing.SINE.getEasing());
        else onSecondary.setAnimation(new Color(ThemeUtils.INSTANCE.getMaterialScheme().getOnSecondary()), 250);
        return onSecondary.getColor();
    }

    public static Color getSecondaryContainer() {
        if (secondaryContainer == null) secondaryContainer = new ColorAnimation(new Color(ThemeUtils.INSTANCE.getMaterialScheme().getSecondaryContainer()), EnumEasing.SINE.getEasing());
        else secondaryContainer.setAnimation(new Color(ThemeUtils.INSTANCE.getMaterialScheme().getSecondaryContainer()), 250);
        return secondaryContainer.getColor();
    }

    public static Color getOnSecondaryContainer() {
        if (onSecondaryContainer == null) onSecondaryContainer = new ColorAnimation(new Color(ThemeUtils.INSTANCE.getMaterialScheme().getOnSecondaryContainer()), EnumEasing.SINE.getEasing());
        else onSecondaryContainer.setAnimation(new Color(ThemeUtils.INSTANCE.getMaterialScheme().getOnSecondaryContainer()), 250);
        return onSecondaryContainer.getColor();
    }

    public static Color getBackground() {
        if (background == null) background = new ColorAnimation(new Color(ThemeUtils.INSTANCE.getMaterialScheme().getBackground()), EnumEasing.SINE.getEasing());
        else background.setAnimation(new Color(ThemeUtils.INSTANCE.getMaterialScheme().getBackground()), 250);
        return background.getColor();
    }

    public static Color getOnBackground() {
        if (onBackground == null) onBackground = new ColorAnimation(new Color(ThemeUtils.INSTANCE.getMaterialScheme().getOnBackground()), EnumEasing.SINE.getEasing());
        else onBackground.setAnimation(new Color(ThemeUtils.INSTANCE.getMaterialScheme().getOnBackground()), 250);
        return onBackground.getColor();
    }

    public static Color getSurface() {
        if (surface == null) surface = new ColorAnimation(new Color(ThemeUtils.INSTANCE.getMaterialScheme().getSurface()), EnumEasing.SINE.getEasing());
        else surface.setAnimation(new Color(ThemeUtils.INSTANCE.getMaterialScheme().getSurface()), 250);
        return surface.getColor();
    }

    public static Color getSurfaceVariant() {
        if (surfaceVariant == null) surfaceVariant = new ColorAnimation(new Color(ThemeUtils.INSTANCE.getMaterialScheme().getSurfaceVariant()), EnumEasing.SINE.getEasing());
        else surfaceVariant.setAnimation(new Color(ThemeUtils.INSTANCE.getMaterialScheme().getSurfaceVariant()), 250);
        return surfaceVariant.getColor();
    }

    public static Color getOnSurface() {
        if (onSurface == null) onSurface = new ColorAnimation(new Color(ThemeUtils.INSTANCE.getMaterialScheme().getOnSurface()), EnumEasing.SINE.getEasing());
        else onSurface.setAnimation(new Color(ThemeUtils.INSTANCE.getMaterialScheme().getOnSurface()), 250);
        return onSurface.getColor();
    }

    public static Color getOnSurfaceVariant() {
        if (onSurfaceVariant == null) onSurfaceVariant = new ColorAnimation(new Color(ThemeUtils.INSTANCE.getMaterialScheme().getOnSurfaceVariant()), EnumEasing.SINE.getEasing());
        else onSurfaceVariant.setAnimation(new Color(ThemeUtils.INSTANCE.getMaterialScheme().getOnSurfaceVariant()), 250);
        return onSurfaceVariant.getColor();
    }

//    public static Color getSurfaceContainer() {
//        if (surfaceContainer == null) surfaceContainer = new ColorAnimation(new Color(ThemeUtils.INSTANCE.getMaterialScheme().getSurface()), EnumEasing.SINE.getEasing());
//        else surfaceContainer.setAnimation(new Color(ThemeUtils.INSTANCE.getMaterialScheme().getShadow()), 250);
//        return surfaceContainer.getColor();
//    }

    public static Color getShadow() {
        if (shadow == null) shadow = new ColorAnimation(new Color(ThemeUtils.INSTANCE.getMaterialScheme().getShadow()), EnumEasing.SINE.getEasing());
        else shadow.setAnimation(new Color(ThemeUtils.INSTANCE.getMaterialScheme().getShadow()), 250);
        return shadow.getColor();
    }

    public static Color getInverseOnSurface() {
        if (inverseOnSurface == null) inverseOnSurface = new ColorAnimation(new Color(ThemeUtils.INSTANCE.getMaterialScheme().getInverseOnSurface()), EnumEasing.SINE.getEasing());
        else inverseOnSurface.setAnimation(new Color(ThemeUtils.INSTANCE.getMaterialScheme().getInverseOnSurface()), 250);
        return inverseOnSurface.getColor();
    }

    public static Color getInversePrimary() {
        if (inversePrimary == null) inversePrimary = new ColorAnimation(new Color(ThemeUtils.INSTANCE.getMaterialScheme().getInversePrimary()), EnumEasing.SINE.getEasing());
        else inversePrimary.setAnimation(new Color(ThemeUtils.INSTANCE.getMaterialScheme().getInversePrimary()), 250);
        return inversePrimary.getColor();
    }

    public static Color getOutline() {
        if (outline == null) outline = new ColorAnimation(new Color(ThemeUtils.INSTANCE.getMaterialScheme().getOutline()), EnumEasing.SINE.getEasing());
        else outline.setAnimation(new Color(ThemeUtils.INSTANCE.getMaterialScheme().getOutline()), 250);
        return outline.getColor();
    }

    public static Color addAlpha(int color, float value) {
        Color color1 = new Color(color);
        return new Color(color1.getRed(), color1.getGreen(), color1.getBlue(), (int) Math.max(Math.min(value, 255), 0));
    }

}
