package dev.minearchive.survival.util.input;

public class MouseUtil {

    public static boolean hover(float mouseX, float mouseY, float x, float y, float w, float h) {
        return x <= mouseX && mouseX <= x + w && y <= mouseY && mouseY <= y + h;
    }
}
