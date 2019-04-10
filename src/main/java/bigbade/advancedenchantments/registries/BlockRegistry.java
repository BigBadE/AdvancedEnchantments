package bigbade.advancedenchantments.registries;

import bigbade.advancedenchantments.AdvancedEnchantments;
import bigbade.advancedenchantments.Reference;
import bigbade.advancedenchantments.blocks.simpleenchanter.SimpleEnchanterBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Objects;

@Mod.EventBusSubscriber
@GameRegistry.ObjectHolder("advancedenchantments")
public class BlockRegistry {

    @GameRegistry.ObjectHolder("simple_table")
    public static SimpleEnchanterBlock SIMPLE_ENCHANTER;

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        AdvancedEnchantments.LOGGER.info("Blocks registering!");
        event.getRegistry().registerAll(new SimpleEnchanterBlock());
        AdvancedEnchantments.LOGGER.info("Blocks registered!");
    }

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        AdvancedEnchantments.LOGGER.info("ItemBlocks registering!");
        for (Block block : ForgeRegistries.BLOCKS.getValuesCollection()) {
            if (Objects.requireNonNull(block.getRegistryName()).getResourceDomain().equals(Reference.MODID.toString())) {
                AdvancedEnchantments.LOGGER.info("Found block " + block.getLocalizedName());
                event.getRegistry().register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
                AdvancedEnchantments.LOGGER.info("Registered ItemBlock for block " + block.getLocalizedName());
            }
        }
        AdvancedEnchantments.LOGGER.info("ItemBlocks registered!");
    }
}
