package net.justacoder.communitymade.mixin;

import net.justacoder.communitymade.damage.CustomDamageTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PigEntity.class)
public abstract class PigEntityMixin extends LivingEntity {
    protected PigEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public boolean damage(DamageSource damageSource, float amount) {
        if (getWorld().isClient()) {
            return super.damage(damageSource, amount);
        }

        if (damageSource.getAttacker() != null && damageSource.getAttacker() instanceof PlayerEntity player) { // funny java syntax
            player.damage(CustomDamageTypes.CANCER, 1e7f);
        }

        return super.damage(damageSource, amount);
    }
}
