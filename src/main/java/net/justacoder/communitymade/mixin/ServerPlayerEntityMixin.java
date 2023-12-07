package net.justacoder.communitymade.mixin;

import com.mojang.authlib.GameProfile;
import com.mojang.datafixers.util.Either;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Unit;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.List;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {

    public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

    @Shadow protected abstract boolean isBedObstructed(BlockPos pos, Direction direction);

    @Shadow protected abstract boolean isBedTooFarAway(BlockPos pos, Direction direction);

    @Inject(method = "trySleep", at = @At("HEAD"), cancellable = true)
    private void onTrySleep(BlockPos pos, CallbackInfoReturnable<Either<PlayerEntity.SleepFailureReason, Unit>> cir) {
        ServerPlayerEntity pl = (ServerPlayerEntity) (Object) this;
        Direction direction = this.getWorld().getBlockState(pos).get(HorizontalFacingBlock.FACING);
        if (pl.isSleeping() || !pl.isAlive()) {
            cir.setReturnValue(Either.left(PlayerEntity.SleepFailureReason.OTHER_PROBLEM));
        }
        if (!isBedTooFarAway(pos, direction)) {
            cir.setReturnValue(Either.left(PlayerEntity.SleepFailureReason.TOO_FAR_AWAY));
        }
        if (isBedObstructed(pos, direction)) {
            cir.setReturnValue(Either.left(PlayerEntity.SleepFailureReason.OBSTRUCTED));
        }
        pl.setSpawnPoint(pl.getWorld().getRegistryKey(), pos, pl.getYaw(), false, true);
        if (!pl.isCreative()) {
            double d = 8.0;
            double e = 5.0;
            Vec3d vec3d = Vec3d.ofBottomCenter(pos);
            List<HostileEntity> list = pl.getWorld().getEntitiesByClass(HostileEntity.class, new Box(vec3d.getX() - 8.0, vec3d.getY() - 5.0, vec3d.getZ() - 8.0, vec3d.getX() + 8.0, vec3d.getY() + 5.0, vec3d.getZ() + 8.0), entity -> entity.isAngryAt(pl));
            if (!list.isEmpty()) {
                cir.setReturnValue(Either.left(PlayerEntity.SleepFailureReason.NOT_SAFE));
            }
        }
        Either<PlayerEntity.SleepFailureReason, Unit> either = super.trySleep(pos).ifRight(unit -> {
            pl.incrementStat(Stats.SLEEP_IN_BED);
            Criteria.SLEPT_IN_BED.trigger(pl);
        });
        if (!pl.getServerWorld().isSleepingEnabled()) {
            pl.sendMessage(Text.translatable("sleep.not_possible"), true);
        }
        ((ServerWorld) pl.getWorld()).updateSleepingPlayers();
        cir.setReturnValue(either);
    }

}
