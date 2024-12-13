package dev.minearchive.survival.mixin;

import dev.minearchive.survival.events.WindowResizeEvent;
import dev.minearchive.survival.util.NanoVGUtil;
import net.minecraft.client.WindowEventHandler;
import net.minecraft.client.WindowSettings;
import net.minecraft.client.util.MonitorTracker;
import net.minecraft.client.util.Window;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static dev.minearchive.survival.Client.EVENT_BUS;

@Mixin(Window.class)
public abstract class MixinWindow {

    @Inject(method = "<init>", at = @At("TAIL"))
    public void onInit(WindowEventHandler eventHandler, MonitorTracker monitorTracker, WindowSettings settings, String videoMode, String title, CallbackInfo ci) {
        NanoVGUtil.init();
    }

    @Inject(method = "onWindowSizeChanged", at = @At("TAIL"))
    public void onWindowSizeChanged(long window, int width, int height, CallbackInfo ci) {
        EVENT_BUS.post(new WindowResizeEvent(window, height));
    }
    
}
