package net.justacoder.communitymade.mixin;

import net.minecraft.block.BedBlock;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BedBlock.class)
public abstract class BedBlockMixin {

    @Inject(method = "isBedWorking", at = @At("HEAD"), cancellable = true)
    private static void onIsBedWorking(World world, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }

}
