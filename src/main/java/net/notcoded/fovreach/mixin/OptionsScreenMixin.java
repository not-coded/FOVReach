package net.notcoded.fovreach.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.OptionsScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.notcoded.fovreach.FOVReach;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OptionsScreen.class)
public abstract class OptionsScreenMixin extends Screen {
    protected OptionsScreenMixin(Component component) {
        super(component);
    }

    @Inject(at = @At("RETURN"), method = "init")
    private void addCustomButton(CallbackInfo ci) {
        this.addButton(new Button(this.width / 2 - 155, this.height / 6 + 144 - 6, 150, 20, new TextComponent("FOV Reach: " + ((FOVReach.enabled) ? "Enabled" : "Disabled")), (buttonWidget) -> {
            FOVReach.enabled = !FOVReach.enabled;
            Minecraft.getInstance().setScreen(this);
        }));
    }
}
