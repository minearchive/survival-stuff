package dev.minearchive.survival.util.input;

import lombok.Getter;
import lombok.Setter;
import org.lwjgl.glfw.GLFW;

@Setter
@Getter
public class Keybind {
    private int key;

    public Keybind() {
        this(GLFW.GLFW_KEY_UNKNOWN);
    }

    public Keybind(int key) {
        this.key = key;
    }

}
