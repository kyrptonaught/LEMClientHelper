package net.kyrptonaught.lemclienthelper.syncedKeybinds;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import java.util.HashMap;

public record SyncKeybindsPacket(
        HashMap<Identifier, SyncedKeybindsConfig.KeybindConfigItem> keybinds) implements CustomPayload {
    public static final Id<SyncKeybindsPacket> PACKET_ID = new Id<>(Identifier.of(SyncedKeybindsMod.MOD_ID, "keybind_pressed_packet"));
    public static final PacketCodec<RegistryByteBuf, SyncKeybindsPacket> codec = PacketCodec.of(SyncKeybindsPacket::write, SyncKeybindsPacket::read);

    public static SyncKeybindsPacket read(RegistryByteBuf buf) {
        int size = buf.readInt();
        HashMap<Identifier, SyncedKeybindsConfig.KeybindConfigItem> keybinds = new HashMap<>(size);
        for (int i = 0; i < size; i++) {
            keybinds.put(buf.readIdentifier(), new SyncedKeybindsConfig.KeybindConfigItem(buf.readString(), buf.readString()));
        }

        return new SyncKeybindsPacket(keybinds);
    }

    public void write(RegistryByteBuf buf) {

    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return PACKET_ID;
    }
}