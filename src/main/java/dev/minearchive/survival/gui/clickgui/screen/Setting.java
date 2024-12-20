package dev.minearchive.survival.gui.clickgui.screen;

import dev.minearchive.survival.gui.clickgui.components.Component;
import dev.minearchive.survival.gui.clickgui.components.SettingCategory;
import dev.minearchive.survival.gui.clickgui.components.impl.BooleanSettingComponent;
import dev.minearchive.survival.gui.clickgui.components.impl.ColorSettingComponent;
import dev.minearchive.survival.gui.clickgui.interfaces.Screen;
import dev.minearchive.survival.mods.setting.settings.BooleanSetting;
import dev.minearchive.survival.mods.setting.settings.ColorSetting;
import dev.minearchive.survival.util.ColorUtil;
import dev.minearchive.survival.util.DiscordRPCUtil;
import dev.minearchive.survival.util.NVGU;
import dev.minearchive.survival.util.input.InputAction;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Setting implements Screen {
    private static final List<SettingCategory> settingCategories = new ArrayList<>();

    private static final SettingCategory main = addCategory(new SettingCategory("Main"));
    public static final BooleanSetting dark = add(true, "DarkMode", main);
    public static final BooleanSetting system = add(true, "Auto Sync With System", main);

    @Override
    public void render(NVGU vg, int mouseX, int mouseY, float delta) {
        float translated = 0;
        for (SettingCategory settingCategory : settingCategories) {
            translated += settingCategory.draw(vg, mouseX, mouseY, translated);
        }
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button, InputAction action) {
        settingCategories.forEach(category -> category.onMouse(mouseX, mouseY, button, action));
    }

    @Override
    public void onKey(int keyCode, int scanCode, int modifiers, InputAction action) {

    }

    @Override
    public void onScroll(double mouseX, double mouseY, double amount) {

    }

    private static SettingCategory addCategory(SettingCategory category) {
        settingCategories.add(category);
        return category;
    }

    private static BooleanSetting add(boolean value, String title, SettingCategory category) {
        BooleanSetting booleanSetting = new BooleanSetting(value, title);
        category.add(new BooleanSettingComponent(booleanSetting));
        return booleanSetting;
    }

    private static BooleanSetting add(boolean value, String title, String description, SettingCategory category) {
        BooleanSetting booleanSetting = new BooleanSetting(value, title, description);
        category.add(new BooleanSettingComponent(booleanSetting));
        return booleanSetting;
    }

    private static ColorSetting add(Color value, String title, SettingCategory category) {
        ColorSetting colorSetting = new ColorSetting(value, title);
        category.add(new ColorSettingComponent(colorSetting));
        return colorSetting;
    }

    private static ColorSetting add(Color value, String title, String description, SettingCategory category) {
        ColorSetting colorSetting = new ColorSetting(value, title, description);
        category.add(new ColorSettingComponent(colorSetting));
        return colorSetting;
    }
}
