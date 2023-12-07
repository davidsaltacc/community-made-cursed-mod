package net.justacoder.communitymade.mixin;

import net.justacoder.communitymade.block.CustomBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.CraftingTableBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CraftingTableBlock.class)
public abstract class CraftingTableBlockMixin {

    @Inject(method = "onUse", at = @At("RETURN"))
    private void onOnUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        if (!world.isClient && Random.create().nextFloat() > 0.6f) {
            LightningEntity entity = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
            entity.setPosition(pos.toCenterPos());
            world.spawnEntity(entity);
            for (int i = 0; i < 5; i++) {
                ZombieEntity entity2 = new ZombieEntity(world);
                entity2.setPosition(player.getPos());
                world.spawnEntity(entity2);
            }
            world.setBlockState(pos, CustomBlocks.ZCT_BLOCK.getDefaultState());
        }
    }

}
