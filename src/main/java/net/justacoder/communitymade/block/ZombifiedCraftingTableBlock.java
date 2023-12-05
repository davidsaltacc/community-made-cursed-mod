package net.justacoder.communitymade.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.CraftingTableBlock;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ZombifiedCraftingTableBlock extends CraftingTableBlock {

    public ZombifiedCraftingTableBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        }
        for (int i = 0; i < 5; i++) {
            ZombieEntity entity = new ZombieEntity(world);
            entity.setPosition(player.getPos());
            world.spawnEntity(entity);
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }
}
