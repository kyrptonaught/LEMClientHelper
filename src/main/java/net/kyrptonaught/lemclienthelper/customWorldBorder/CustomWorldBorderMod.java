package net.kyrptonaught.lemclienthelper.customWorldBorder;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public class CustomWorldBorderMod {

    public static void onInitialize() {
        PayloadTypeRegistry.playS2C().register(CustomWorldBorderPacket.PACKET_ID, CustomWorldBorderPacket.codec);
        CustomWorldBorderNetworking.registerReceive();
    }
}
