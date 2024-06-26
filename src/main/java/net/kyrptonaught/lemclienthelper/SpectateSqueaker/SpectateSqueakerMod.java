package net.kyrptonaught.lemclienthelper.SpectateSqueaker;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public class SpectateSqueakerMod {
    public static final String MOD_ID = "spectatesqueak";

    public static void onInitialize() {
        PayloadTypeRegistry.playC2S().register(SqueakPacket.PACKET_ID, SqueakPacket.codec);
    }
}
