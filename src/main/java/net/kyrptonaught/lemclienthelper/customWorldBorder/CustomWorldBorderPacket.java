package net.kyrptonaught.lemclienthelper.customWorldBorder;


import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record CustomWorldBorderPacket(double xCenter, double zCenter, double xSize,
                                      double zSize) implements CustomPayload {
    public static final Id<CustomWorldBorderPacket> PACKET_ID = new Id<>(Identifier.of("customworldborder", "customborder"));
    public static final PacketCodec<RegistryByteBuf, CustomWorldBorderPacket> codec = PacketCodec.of(CustomWorldBorderPacket::write, CustomWorldBorderPacket::read);

    public static CustomWorldBorderPacket read(RegistryByteBuf buf) {
        return new CustomWorldBorderPacket(buf.readDouble(), buf.readDouble(), buf.readDouble(), buf.readDouble());
    }

    public void write(RegistryByteBuf buf) {
        buf.writeDouble(xCenter);
        buf.writeDouble(zCenter);
        buf.writeDouble(xSize);
        buf.writeDouble(zSize);
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return PACKET_ID;
    }
}