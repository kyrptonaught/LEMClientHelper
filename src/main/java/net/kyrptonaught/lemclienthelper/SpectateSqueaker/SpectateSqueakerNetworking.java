package net.kyrptonaught.lemclienthelper.SpectateSqueaker;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class SpectateSqueakerNetworking {

    @Environment(EnvType.CLIENT)
    public static void sendSqueakPacket() {
        ClientPlayNetworking.send(new SqueakPacket(true));
    }
}
