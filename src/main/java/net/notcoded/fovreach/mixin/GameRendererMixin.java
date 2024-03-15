package net.notcoded.fovreach.mixin;

import net.minecraft.Util;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockMath;
import net.minecraft.network.chat.TextComponent;
import net.notcoded.fovreach.FOVReach;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Inject(at = @At("RETURN"), method= "getFov", cancellable = true)
    private void getReachFOV(Camera camera, float f, boolean bl, CallbackInfoReturnable<Double> cir) {
        double fov = cir.getReturnValue();
        if(!FOVReach.enabled) return;

        Minecraft client = Minecraft.getInstance();
        LocalPlayer player = client.player;
        if(player == null) return;

        double reach = player.getCurrentAttackReach(1.0F) - 1.0F; // for whatever reason minecraft adds +1 reach

        fov = fov * (reach / 3);
        cir.setReturnValue(fov);
    }
}
