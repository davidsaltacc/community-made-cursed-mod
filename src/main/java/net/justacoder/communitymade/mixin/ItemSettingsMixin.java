package net.justacoder.communitymade.mixin;

import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Item.Settings.class)
public abstract class ItemSettingsMixin {
    @ModifyVariable(method = "maxCount", at = @At("HEAD"), argsOnly = true)
    private int setCount(int original) {
        return 67;
    }
}
