package dev.minearchive.survival.mods.setting.settings;

import dev.minearchive.survival.mods.setting.Setting;

import java.util.function.Supplier;

public class IntegerSetting extends Setting<Integer> {
    public IntegerSetting(Integer value, String display, String description, Supplier<Boolean> visible) {
        super(value, display, description, visible);
    }

    public IntegerSetting(Integer value, String display, Supplier<Boolean> visible) {
        super(value, display, visible);
    }

    public IntegerSetting(Integer value, String display, String description) {
        super(value, display, description);
    }

    public IntegerSetting(Integer value, String display) {
        super(value, display);
    }
}
