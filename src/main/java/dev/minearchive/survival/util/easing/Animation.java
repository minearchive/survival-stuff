package dev.minearchive.survival.util.easing;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Animation {
    @Getter
    @Setter
    private float value;
    private long lastUpdateTime;
    @Setter
    private IEasing easing;

    public Animation(float value, IEasing easing) {
        this.value = value;
        this.lastUpdateTime = System.currentTimeMillis();
        this.easing = easing;
    }

    public void animateTo(float target, double duration) {
        long currentTime = System.currentTimeMillis();
        long deltaTime = currentTime - lastUpdateTime;
        lastUpdateTime = currentTime;

        float progress = (float) deltaTime / (float) duration;
        float easedProgress = (float) easing.ease(progress);

        float deltaValue = (target - value) * easedProgress;
        value += deltaValue;
    }

}
