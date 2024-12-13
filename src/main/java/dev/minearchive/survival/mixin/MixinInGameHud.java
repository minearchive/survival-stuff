package dev.minearchive.survival.mixin;

import dev.minearchive.survival.events.Render2DEvent;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static dev.minearchive.survival.Client.EVENT_BUS;

@Mixin(InGameHud.class)
public class MixinInGameHud {

    @Inject(method = "render", at = @At("HEAD"))
    public void onRender(DrawContext context, float tickDelta, CallbackInfo ci) {
        EVENT_BUS.post(new Render2DEvent(context, tickDelta));
    }

}
