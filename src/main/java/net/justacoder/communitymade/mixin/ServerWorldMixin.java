package net.justacoder.communitymade.mixin;

import net.justacoder.communitymade.Main;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.function.BooleanSupplier;

@Mixin(ServerWorld.class)
public abstract class ServerWorldMixin {
    @Inject(method = "tick", at = @At(value = "TAIL"))
    private void startWorldTick(BooleanSupplier shouldKeepTicking, CallbackInfo ci) {
        Main.onTick((ServerWorld) (Object) this);
    }
}
