package net.fabricmc.example.mixin;

import net.minecraft.client.option.SimpleOption;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SimpleOption.class)
public class GuiScaleMixin {

    @Inject(method = "getGuiScaleValues", at = @At("HEAD"), cancellable = true)
    private static void modifyGuiScaleSteps(CallbackInfoReturnable<double[]> cir) {
        cir.setReturnValue(new double[]{1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0});
    }
}
