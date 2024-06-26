package net.kyrptonaught.lemclienthelper.TakeEverything;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record TakeEverythingPacket(boolean enabled) implements CustomPayload {
    public static final Id<TakeEverythingPacket> PACKET_ID = new Id<>(Identifier.of("takeeverything", "take_everything_packet"));
    public static final PacketCodec<RegistryByteBuf, TakeEverythingPacket> codec = PacketCodecs.BOOL.xmap(TakeEverythingPacket::new, TakeEverythingPacket::enabled).cast();

    @Override
    public Id<? extends CustomPayload> getId() {
        return PACKET_ID;
    }
}
