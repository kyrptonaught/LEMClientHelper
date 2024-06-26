package net.kyrptonaught.lemclienthelper.SpectateSqueaker;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record SqueakPacket(boolean enabled) implements CustomPayload {
    public static final Id<SqueakPacket> PACKET_ID = new Id<>(Identifier.of(SpectateSqueakerMod.MOD_ID, "squeak_packet"));
    public static final PacketCodec<RegistryByteBuf, SqueakPacket> codec = PacketCodecs.BOOL.xmap(SqueakPacket::new, SqueakPacket::enabled).cast();

    @Override
    public Id<? extends CustomPayload> getId() {
        return PACKET_ID;
    }
}
