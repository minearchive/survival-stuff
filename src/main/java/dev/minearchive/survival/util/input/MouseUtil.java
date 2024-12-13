package dev.minearchive.survival.util.input;

import java.util.function.Supplier;

public class MouseUtil {

    private Supplier<Float> x;
    private Supplier<Float> y;

    public MouseUtil() {
        x = () -> 0.0f;
        y = () -> 0.0f;
    }

    public MouseUtil(Supplier<Float> x, Supplier<Float> y) {
        this.x = x;
        this.y = y;
    }

    public void translate(Supplier<Float> x, Supplier<Float> y) {
        this.x = x;
        this.y = y;
    }

    public boolean isHovered(float x1, float y1, float x2, float y2, float[] mouseXY) {
        return isHovered(new float[]{x1, y1}, new float[]{x2, y2}, mouseXY);
    }

    public boolean isHovered(float x1, float y1, float x2, float y2, float mouseX, float mouseY) {
        return isHovered(new float[]{x1, y1}, new float[]{x1 + x2, y1 + y2}, new float[]{mouseX, mouseY});
    }

    public boolean isHovered(float[] po1, float[] po2, float[] mouseXY) {
        return isHovered(new float[][]{po1, po2}, mouseXY);
    }

    public boolean isHovered(float[][] pos, float[] mouseXY) {
        return pos[0][0] + x.get() < mouseXY[0] && mouseXY[0] < pos[1][0] + x.get() &&
                pos[0][1] + y.get() < mouseXY[1] && mouseXY[1] < pos[1][1] + y.get();
    }

    public boolean isHovered(float x2, float y2, float[] mouseXY) {
        return isHovered(new float[]{0 ,0}, new float[]{x2, y2}, mouseXY);
    }

    public float getX() {
        return x.get();
    }

    public float getY() {
        return y.get();
    }

}
