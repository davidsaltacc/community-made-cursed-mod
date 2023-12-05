package net.justacoder.communitymade.mixin;

import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ServerPlayNetworkHandler.class)
public abstract class ServerPlayNetworkHandlerMixin {

    @ModifyConstant(method = "onCreativeInventoryAction", constant = @Constant(intValue = 64))
    private int modifyStackThingyIdk(int constant) {
        return 67;
    }

}
