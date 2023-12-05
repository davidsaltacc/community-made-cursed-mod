package net.justacoder.communitymade.mixin;

import net.minecraft.entity.SpawnGroup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SpawnGroup.class)
public abstract class SpawnGroupMixin {

    @Inject(method = "isPeaceful", at = @At("HEAD"), cancellable = true)
    private void onIsPeaceful(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }

}
