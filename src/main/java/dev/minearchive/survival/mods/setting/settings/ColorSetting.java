package dev.minearchive.survival.mods.setting.settings;

import dev.minearchive.survival.mods.setting.Setting;

import java.awt.*;
import java.util.function.Supplier;

public class ColorSetting extends Setting<Color> {

    public ColorSetting(Color value, String display, String description, Supplier<Boolean> visible) {
        super(value, display, description, visible);
    }

    public ColorSetting(Color value, String display, Supplier<Boolean> visible) {
        super(value, display, visible);
    }

    public ColorSetting(Color value, String display, String description) {
        super(value, display, description);
    }

    public ColorSetting(Color value, String display) {
        super(value, display);
    }
}
