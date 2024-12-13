package dev.minearchive.survival.util.input;

import org.lwjgl.glfw.GLFW;

public class Keybind {

    private int key;

    public Keybind() {
        this(GLFW.GLFW_KEY_UNKNOWN);
    }

    public Keybind(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
