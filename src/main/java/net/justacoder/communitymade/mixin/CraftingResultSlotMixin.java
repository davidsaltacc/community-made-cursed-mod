package net.justacoder.communitymade.mixin;

import net.justacoder.communitymade.mixininterface.ICreeperEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.CraftingResultSlot;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CraftingResultSlot.class)
public abstract class CraftingResultSlotMixin {

    @Shadow @Final private PlayerEntity player;

    @Inject(method = "onCrafted(Lnet/minecraft/item/ItemStack;)V", at = @At("TAIL"))
    private void onOnCrafted(ItemStack stack, CallbackInfo ci) {
        if (!player.getWorld().isClient() && Random.create().nextFloat() > 0.7) {
            CreeperEntity creeper = new CreeperEntity(EntityType.CREEPER, player.getWorld());
            creeper.setPosition(player.getPos());
            creeper.getDataTracker().set(((ICreeperEntity) creeper).getCharged(), true);
            player.getWorld().spawnEntity(creeper);
        }
    }

}
