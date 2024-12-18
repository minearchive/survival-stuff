package dev.minearchive.survival.util;

import com.jthemedetecor.OsThemeDetector;
import dev.minearchive.scheme.Scheme;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ThemeUtils {
    public static ThemeUtils INSTANCE;

    private final List<Theme> themes = new ArrayList<>();
    private Color current = new Color(0x4F378B);
    private Scheme materialScheme = OsThemeDetector.getDetector().isDark() ? Scheme.dark(current.getRGB()) : Scheme.light(current.getRGB());
    private boolean isDark = OsThemeDetector.getDetector().isDark();

    public ThemeUtils() {
        INSTANCE = this;
    }

    public record Theme(Color color) { }

    public ThemeUtils setColor(Color color) {
        this.current = color;
        this.materialScheme = isDark ? Scheme.dark(current.getRGB()) : Scheme.light(current.getRGB());
        return this;
    }

    public void changeMode(boolean isDark) {
        this.isDark = isDark;
        this.materialScheme = isDark ? Scheme.dark(current.getRGB()) : Scheme.light(current.getRGB());
    }
}

