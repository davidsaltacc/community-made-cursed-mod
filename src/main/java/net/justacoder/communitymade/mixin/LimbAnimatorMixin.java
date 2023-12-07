package net.justacoder.communitymade.mixin;

import net.minecraft.entity.LimbAnimator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LimbAnimator.class)
public abstract class LimbAnimatorMixin {

    @Inject(method = "getPos(F)F", at = @At("RETURN"), cancellable = true)
    private void onGetPos(float tickDelta, CallbackInfoReturnable<Float> cir) {
        float v = cir.getReturnValue() * 10;
        cir.setReturnValue(v);
    }

}
