package net.kyrptonaught.lemclienthelper.ServerStates;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;

public class ServerStatesNetworking {
    public static final Identifier SERVER_STATE = new Identifier("lem", "server_state_packet");
    public static void registerPackets() {
        //register packets relating to the server stater here
        ClientPlayNetworking.registerGlobalReceiver(SERVER_STATE, (client, handler, buf, responseSender) -> {
            States states = ServerStatesMod.states;
            states.gameActive = buf.readBoolean();
            states.gameID = buf.readIdentifier();
        });
    }
}
