package net.fabricmc.example.mixin;

import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Window.class)
public abstract class WindowMixin {

    @Shadow private double scaleFactor;
    @Shadow private int framebufferWidth;
    @Shadow private int framebufferHeight;
    @Shadow private int scaledWidth;
    @Shadow private int scaledHeight;

    @Inject(method = "setScaleFactor", at = @At("HEAD"), cancellable = true)
    private void applyFractionalScale(double guiScale, CallbackInfo ci) {
        if (guiScale > 0) {
            this.scaleFactor = guiScale;
            this.scaledWidth = (int) Math.ceil((double) this.framebufferWidth / this.scaleFactor);
            this.scaledHeight = (int) Math.ceil((double) this.framebufferHeight / this.scaleFactor);
            ci.cancel();
        }
    }
}
