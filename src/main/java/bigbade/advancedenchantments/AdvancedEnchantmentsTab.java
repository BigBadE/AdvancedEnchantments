package bigbade.advancedenchantments;

import bigbade.advancedenchantments.registries.BlockRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class AdvancedEnchantmentsTab extends CreativeTabs {
    public AdvancedEnchantmentsTab() {
        super("advancedenchantmentstab");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(BlockRegistry.SIMPLE_ENCHANTER);
    }
}
