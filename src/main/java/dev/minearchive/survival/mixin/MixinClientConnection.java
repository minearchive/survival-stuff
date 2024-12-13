package dev.minearchive.survival.mixin;

import dev.minearchive.survival.events.PacketEvent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.packet.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static dev.minearchive.survival.Client.EVENT_BUS;

@Mixin(ClientConnection.class)
public class MixinClientConnection {

    @Unique
    private static final MinecraftClient mc = MinecraftClient.getInstance();

    @Inject(method = "handlePacket", at = @At("HEAD"))
    private static <T extends PacketListener> void onHandlePacket(Packet<T> packet, PacketListener listener, CallbackInfo ci) {
        if (nullCheck()) return;
        EVENT_BUS.post(new PacketEvent.Receive(packet));
    }

    @Inject(method = "send(Lnet/minecraft/network/packet/Packet;)V", at = @At("HEAD"))
    private void onSendPacketPre(Packet<?> packet, CallbackInfo info) {
        if(nullCheck()) return;
        EVENT_BUS.post(new PacketEvent.Send(packet));
    }

    @Unique
    private static boolean nullCheck() {
        return mc.player == null || mc.world == null;
    }

}
