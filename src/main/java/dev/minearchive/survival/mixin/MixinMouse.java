package dev.minearchive.survival.mixin;

import dev.minearchive.survival.events.MouseEvent;
import dev.minearchive.survival.util.input.InputAction;
import net.minecraft.client.Mouse;
import net.minecraft.client.input.Input;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static dev.minearchive.survival.Client.EVENT_BUS;

@Mixin(Mouse.class)
public class MixinMouse {

    @Inject(method = "onMouseButton", at = @At("HEAD"))
    private void onMouseButton(long window, int button, int action, int mods, CallbackInfo ci) {
        EVENT_BUS.post(new MouseEvent.MouseButtonEvent(button, InputAction.getByInt(action)));
    }

    @Inject(method = "onMouseScroll", at = @At("HEAD"))
    private void onMouseScroll(long window, double horizontal, double vertical, CallbackInfo ci) {
        EVENT_BUS.post(new MouseEvent.MouseScrollEvent(horizontal, vertical));
    }

}
