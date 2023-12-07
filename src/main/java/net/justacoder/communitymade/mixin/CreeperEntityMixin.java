package net.justacoder.communitymade.mixin;

import net.justacoder.communitymade.mixininterface.ICreeperEntity;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.mob.CreeperEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(CreeperEntity.class)
public abstract class CreeperEntityMixin implements ICreeperEntity {

    @Shadow @Final private static TrackedData<Boolean> CHARGED;

    @Override
    public TrackedData<Boolean> getCharged() {
        return CHARGED;
    }
}
