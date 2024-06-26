package net.kyrptonaught.lemclienthelper.syncedKeybinds;


import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record KeybindPressPacket(Identifier keybind) implements CustomPayload {
    public static final Id<KeybindPressPacket> PACKET_ID = new Id<>(Identifier.of(SyncedKeybindsMod.MOD_ID, "sync_keybinds_packet"));
    public static final PacketCodec<RegistryByteBuf, KeybindPressPacket> codec = Identifier.PACKET_CODEC.xmap(KeybindPressPacket::new, KeybindPressPacket::keybind).cast();

    @Override
    public Id<? extends CustomPayload> getId() {
        return PACKET_ID;
    }
}
