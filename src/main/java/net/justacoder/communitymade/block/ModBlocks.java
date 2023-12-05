package net.justacoder.communitymade.block;

import net.justacoder.communitymade.Main;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.Instrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public abstract class ModBlocks {

    public static ZombifiedCraftingTableBlock ZCT_BLOCK = new ZombifiedCraftingTableBlock(AbstractBlock.Settings.create().mapColor(MapColor.PALE_GREEN).instrument(Instrument.BASS).strength(2.5f).sounds(BlockSoundGroup.SCULK).burnable());

    public static void addBlocks() {
        Registry.register(Registries.BLOCK, new Identifier(Main.MOD_ID, "zombified_crafting_table"), ZCT_BLOCK);
        Registry.register(Registries.ITEM, new Identifier(Main.MOD_ID, "zombified_crafting_table"), new BlockItem(ZCT_BLOCK, new Item.Settings()));
    }

}
