package dev.minearchive.survival.gui.clickgui.dialog;

import dev.minearchive.survival.gui.clickgui.interfaces.Dialog;
import dev.minearchive.survival.mods.Mod;
import dev.minearchive.survival.util.NVGU;

public class ModuleDialog extends Dialog {

    private final Mod module;

    public ModuleDialog(Mod module) {
        this.module = module;
    }

    @Override
    public void render(NVGU vg, int mouseX, int mouseY, float delta) {
        super.render(vg, mouseX, mouseY, delta);
    }
}
