package dev.minearchive.survival.mods.setting;

import lombok.Getter;
import lombok.Setter;

import java.util.function.Supplier;

@Getter
public class Setting<T> {

    @Setter
    private T value;
    private final String display;
    private final String description;
    @Setter
    private Supplier<Boolean> visible;

    public Setting(T value, String display, String description, Supplier<Boolean> visible) {
        this.value = value;
        this.display = display;
        this.description = description;
        this.visible = visible;
    }

    public Setting(T value, String display, Supplier<Boolean> visible) {
        this.value = value;
        this.display = display;
        this.description = null;
        this.visible = visible;
    }

    public Setting(T value, String display, String description) {
        this.value = value;
        this.display = display;
        this.description = description;
        this.visible = () -> true;
    }

    public Setting(T value, String display) {
        this.value = value;
        this.display = display;
        this.description = null;
        this.visible = () -> true;
    }

}
