package dev.minearchive.survival.events;

import net.minecraft.network.packet.Packet;

public class PacketEvent {

    public record Send(Packet<?> packet) {
    }

    public record Receive(Packet<?> packet) {
    }

}
