package net.justacoder.communitymade.mixin;

import net.justacoder.communitymade.damage.CustomDamageTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Inject(method = "getMaxCount", at = @At("RETURN"), cancellable = true)
    private void increaseStackLimit(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(67);
    }

    @Inject(method = "damage(ILnet/minecraft/entity/LivingEntity;Ljava/util/function/Consumer;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;incrementStat(Lnet/minecraft/stat/Stat;)V"))
    private <T extends LivingEntity> void onBroken(int amount, T entity, Consumer<T> breakCallback, CallbackInfo ci) {
        if (!entity.getWorld().isClient()) {
            entity.damage(CustomDamageTypes.TOOL_SUICIDE, 1e7f);
        }
    }
}
