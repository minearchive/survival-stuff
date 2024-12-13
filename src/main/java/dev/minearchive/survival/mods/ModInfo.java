package dev.minearchive.survival.mods;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_UNKNOWN;

@Retention(RetentionPolicy.RUNTIME)
public @interface ModInfo {

    String name();
    String description();
    boolean enable() default false;
    int keyboard() default GLFW_KEY_UNKNOWN;

}
