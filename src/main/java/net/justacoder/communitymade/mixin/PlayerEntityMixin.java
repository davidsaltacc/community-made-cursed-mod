package net.justacoder.communitymade.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        PlayerEntity pl = (PlayerEntity) (Object) this;
        World world = pl.getWorld();
        if (world.isClient || world.getDimension().natural()) {
            return;
        }
        if (pl.getSleepTimer() >= 40 && pl.isSleeping()) {
            pl.wakeUp();
            world.createExplosion(null, world.getDamageSources().badRespawnPoint(pl.getPos()), null, pl.getPos(), 5.0f, true, World.ExplosionSourceType.BLOCK);
        }
    }

}
