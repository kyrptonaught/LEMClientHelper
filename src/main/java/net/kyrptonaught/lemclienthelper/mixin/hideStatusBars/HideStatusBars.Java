package net.kyrptonaught.lemclienthelper.mixin.hideStatusBars;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.kyrptonaught.lemclienthelper.hud.HudMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(InGameHud.class)
public class HideStatusBars {
    @Unique
    private final Identifier Lobby = new Identifier("lem.base","lobby");

    @WrapOperation(method = "render", at= @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderStatusBars(Lnet/minecraft/client/gui/DrawContext;)V"))
    public void hideHearts(InGameHud instance, DrawContext context, Operation<Void> original) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        // Player is in the lobby, Player is in adventure mode, config is enabled.
        if (player != null) {
            if (player.getWorld().getRegistryKey().getValue().equals(Lobby) && !player.getAbilities().allowModifyWorld && HudMod.getConfig().hideStatusBarsEnabled) {
                return;
            } else {
                original.call(instance, context);
            }
        } else {
            original.call(instance, context);
        }
    }
}
