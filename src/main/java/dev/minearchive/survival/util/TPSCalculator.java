package dev.minearchive.survival.util;

import com.google.common.eventbus.Subscribe;
import dev.minearchive.survival.Client;
import dev.minearchive.survival.events.PacketEvent;
import net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket;
import net.minecraft.util.math.MathHelper;

/**
 * code reference <a href="https://github.com/MeteorDevelopment/meteor-client/blob/master/src/main/java/meteordevelopment/meteorclient/utils/world/TickRate.java#L21">source</a>
 */
public class TPSCalculator implements Util {
    public static TPSCalculator INSTANCE = new TPSCalculator();

    public TPSCalculator() {
        Client.register(this);
    }

    private int nextIndex = 0;
    private final float[] tickRates = new float[20];
    private long timeLastTimeUpdate = -1;
    private long timeGameJoined;

    @Subscribe
    public void onReceivePacket(PacketEvent.Receive event) {
        if (event.packet() instanceof WorldTimeUpdateS2CPacket) {
            long now = System.currentTimeMillis();
            float timeElapsed = (now - timeLastTimeUpdate) / 1000.0F;
            tickRates[nextIndex] = MathHelper.clamp(20.0f / timeElapsed, 0.0f, 20.0f);
            nextIndex = (nextIndex + 1) % tickRates.length;
            timeLastTimeUpdate = now;
        }
    }

    public float getTickRate() {
        if (mc.world == null || mc.player == null) return 0;
        if (System.currentTimeMillis() - timeGameJoined < 4000) return 20;

        int numTicks = 0;
        float sumTickRates = 0.0f;
        for (float tickRate : tickRates) {
            if (tickRate > 0) {
                sumTickRates += tickRate;
                numTicks++;
            }
        }
        return sumTickRates / numTicks;
    }

}
