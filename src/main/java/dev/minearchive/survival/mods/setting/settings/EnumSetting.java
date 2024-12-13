package dev.minearchive.survival.mods.setting.settings;

import dev.minearchive.survival.mods.setting.Setting;

import java.util.function.Supplier;

public class EnumSetting extends Setting<Enum<?>> {

    public EnumSetting(Enum<?> value, String display, String description, Supplier<Boolean> visible) {
        super(value, display, description, visible);
    }

    public EnumSetting(Enum<?> value, String display, Supplier<Boolean> visible) {
        super(value, display, visible);
    }

    public EnumSetting(Enum<?> value, String display, String description) {
        super(value, display, description);
    }

    public EnumSetting(Enum<?> value, String display) {
        super(value, display);
    }
}
