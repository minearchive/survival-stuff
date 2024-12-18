package dev.minearchive.survival.util.easing;

import lombok.Getter;
import lombok.Setter;

public class Animation {
    @Getter
    private float value, startValue, targetValue;
    private long startTime, duration;
    @Setter
    private IEasing easing;
    private Runnable onFinished;

    public Animation(float value, IEasing easing) {
        this.value = value;
        this.easing = easing;
    }

    public void animateTo(float target, double durationMs) {
        long currentTime = System.nanoTime();

        if (targetValue != target) {
            if (duration > 0) {
                long elapsedTime = currentTime - startTime;
                if (elapsedTime < duration) {
                    float progress = (float) elapsedTime / duration;
                    float easedProgress = (float) easing.ease(progress);
                    this.value = startValue + (targetValue - startValue) * easedProgress;
                } else value = targetValue;
            }

            this.startValue = value;
            this.targetValue = target;
            this.startTime = currentTime;
            this.duration = (long) (durationMs * 1_000_000);
        }

        long elapsedTime = currentTime - startTime;
        if (elapsedTime < duration) {
            float progress = (float) elapsedTime / duration;
            float easedProgress = (float) easing.ease(progress);

            this.value = startValue + (targetValue - startValue) * easedProgress;
        } else {
            if (onFinished != null) {
                onFinished.run();
                onFinished = null;
            }
            this.value = targetValue;
        }
    }

    public void setValue(float value) {
        this.value = value;
        this.targetValue = value;
    }

}
