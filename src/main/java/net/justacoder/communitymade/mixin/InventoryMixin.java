package net.justacoder.communitymade.mixin;

import net.minecraft.inventory.Inventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Inventory.class)
public interface InventoryMixin {

    /**
     * @author ..
     * @reason ..
     */
    @Overwrite
    public default int getMaxCountPerStack() {
        return 67;
    }

}
