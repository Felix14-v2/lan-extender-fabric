package me.doinkythederp.lanextender.mixin;

import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.doinkythederp.lanextender.LANExtenderMod;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import net.minecraft.client.MinecraftClient;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(at = @At("HEAD"), method = "openScreen")
    private void clearLANScreenCheckbox(@Nullable net.minecraft.client.gui.screen.Screen screen, CallbackInfo info) {
        if (screen != null && LANExtenderMod.publishCheckbox != null) {
            // Checkbox state should not persist after closing the Open To LAN screen.
            // This would mess up the /publish command, which should not start an ngrok
            // session.
            LANExtenderMod.publishCheckbox = null;
        }
    }
}
