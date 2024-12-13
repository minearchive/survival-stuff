package dev.minearchive.survival.mods.setting.settings;

import dev.minearchive.survival.mods.setting.Setting;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class BooleanSetting extends Setting<Boolean> {

    private Consumer<BooleanSetting> runnable = setting -> {};

    public BooleanSetting(Boolean value, String display, String description, Supplier<Boolean> visible) {
        super(value, display, description, visible);
    }

    public BooleanSetting(Boolean value, String display, Supplier<Boolean> visible) {
        super(value, display, visible);
    }

    public BooleanSetting(Boolean value, String display, String description) {
        super(value, display, description);
    }

    public BooleanSetting(Boolean value, String display) {
        super(value, display);
    }

    public BooleanSetting setRunnable(Consumer<BooleanSetting> runnable) {
        this.runnable = runnable;
        return this;
    }

    public void toggle() {
        setValue(!getValue());
        runnable.accept(this);
    }
}
