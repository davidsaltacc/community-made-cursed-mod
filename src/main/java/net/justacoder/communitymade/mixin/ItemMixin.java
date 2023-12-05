package net.justacoder.communitymade.mixin;

import net.justacoder.communitymade.mixininterface.IItem;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Item.class)
public abstract class ItemMixin implements IItem {

    @Shadow @Final @Mutable private int maxCount;
    @Shadow @Final @Mutable public static int DEFAULT_MAX_COUNT;

    @Override
    public int getMaxItemCount() {
        return maxCount;
    }
    @Override
    public void setMaxItemCount(int count) {
        maxCount = count;
    }
    @Override
    public void setDefaultMaxCount(int count) {
        DEFAULT_MAX_COUNT = count;
    }
}
