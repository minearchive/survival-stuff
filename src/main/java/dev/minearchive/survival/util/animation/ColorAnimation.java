package dev.minearchive.survival.util.animation;

import dev.minearchive.survival.util.easing.Animation;
import dev.minearchive.survival.util.easing.EnumEasing;
import dev.minearchive.survival.util.easing.IEasing;

import java.awt.*;

public class ColorAnimation {

    private Animation red, green, blue, alpha;

    public ColorAnimation() {
        this(null, EnumEasing.CUBIC.getEasing());
    }

    public ColorAnimation(Color color, IEasing animation) {
        this.red = new Animation((color == null) ? 0.0f : color.getRed(), animation);
        this.green = new Animation((color == null) ? 0.0f : color.getGreen(), animation);
        this.blue = new Animation((color == null) ? 0.0f : color.getBlue(), animation);
        this.alpha = new Animation((color == null) ? 0.0f : color.getAlpha(), animation);
    }

    public void setAnimation(Color color, float time) {
        this.red.animateTo(color.getRed(), time);
        this.green.animateTo(color.getGreen(), time);
        this.blue.animateTo(color.getBlue(), time);
        this.alpha.animateTo(color.getAlpha(), time);
    }

    public ColorAnimation setValue(Color color) {
        this.red.setValue(color.getRed());
        this.green.setValue(color.getGreen());
        this.blue.setValue(color.getBlue());
        this.alpha.setValue(color.getAlpha());
        return this;
    }

    public Color getColor() {
        return new Color(
                Math.max(Math.min((int) red.getValue(), 255), 0),
                Math.max(Math.min((int) green.getValue(), 255), 0),
                Math.max(Math.min((int) blue.getValue(), 255), 0),
                Math.max(Math.min((int) alpha.getValue(), 255), 0)
        );
    }

}
