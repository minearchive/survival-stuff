package dev.minearchive.survival.mods;

import lombok.Getter;

@Getter
public enum Tag {
    HUD("Hud"), RENDER("Render"), COMBAT("Combat"), UTILITY("Utility");

    private final String name;
    Tag(String name) {
        this.name =name;
    }
}
