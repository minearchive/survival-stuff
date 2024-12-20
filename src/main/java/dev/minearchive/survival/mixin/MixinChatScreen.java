package dev.minearchive.survival.mixin;

import dev.minearchive.survival.mods.Hud;
import dev.minearchive.survival.mods.ModsManager;
import dev.minearchive.survival.util.Util;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ChatScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatScreen.class)
public class MixinChatScreen implements Util {

    @Inject(method = "render", at = @At("TAIL"))
    public void onRender(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        final double x = mc.mouse.getX();
        final double y = mc.mouse.getY();
        ModsManager.getModList().stream().filter(mod -> mod instanceof Hud).forEach(mod -> {

        });
    }
}
