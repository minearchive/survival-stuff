package dev.minearchive.survival.mods.setting.settings;

import dev.minearchive.survival.mods.setting.Setting;

import java.util.function.Supplier;

public class FloatSetting extends Setting<Float> {
    public FloatSetting(Float value, String display, String description, Supplier<Boolean> visible) {
        super(value, display, description, visible);
    }

    public FloatSetting(Float value, String display, Supplier<Boolean> visible) {
        super(value, display, visible);
    }

    public FloatSetting(Float value, String display, String description) {
        super(value, display, description);
    }

    public FloatSetting(Float value, String display) {
        super(value, display);
    }
}
