package net.kyrptonaught.lemclienthelper.TakeEverything;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class TakeEverythingNetworking {

    @Environment(EnvType.CLIENT)
    public static void sendTakeEverythingPacket() {
        if (TakeEverythingMod.getConfig().enabled) {
            ClientPlayNetworking.send(new TakeEverythingPacket(true));
        }
    }
}
