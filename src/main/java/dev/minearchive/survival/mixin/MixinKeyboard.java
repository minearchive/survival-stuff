package dev.minearchive.survival.mixin;

import dev.minearchive.survival.events.KeyboardEvent;
import dev.minearchive.survival.util.input.InputAction;
import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static dev.minearchive.survival.Client.EVENT_BUS;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_UNKNOWN;

@Mixin(Keyboard.class)
public class MixinKeyboard {

    @Inject(method = "onKey", at = @At("HEAD"))
    public void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        if (key != GLFW_KEY_UNKNOWN) {
            EVENT_BUS.post(new KeyboardEvent(key, InputAction.getByInt(action)));
        }
    }

}
