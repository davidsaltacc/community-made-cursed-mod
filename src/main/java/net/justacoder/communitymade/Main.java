package net.justacoder.communitymade;

import net.justacoder.communitymade.block.ModBlocks;
import net.justacoder.communitymade.mixininterface.IItem;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Main {

    public static Logger LOGGER = LoggerFactory.getLogger("CommunityMade");
    public static String MOD_ID = "communitymade";

    public static void initialize() {
        LOGGER.info("Starting Cursedcraft...");
        Registries.ITEM.forEach(item -> {
            if (item.getMaxCount() == 64) {
                ((IItem) item).setMaxItemCount(67);
            }
        });
        ModBlocks.addBlocks();
        LOGGER.info("Finished starting Cursedcraft");
    }

    public static void onTick(ServerWorld world) {
        world.getPlayers().forEach(player -> {
            if (player.isTouchingWater()) {
                world.setBlockState(BlockPos.ofFloored(player.getEyePos()).add(0, 1, 0), Blocks.AIR.getDefaultState());
            }
        });
    }
}