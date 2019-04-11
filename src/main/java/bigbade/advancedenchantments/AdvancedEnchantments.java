package bigbade.advancedenchantments;

import bigbade.advancedenchantments.blocks.simpleenchanter.SimpleEnchanterRenderer;
import bigbade.advancedenchantments.proxies.CommonProxy;
import bigbade.advancedenchantments.registries.BlockRegistry;
import bigbade.advancedenchantments.registries.GuiRegistry;
import bigbade.advancedenchantments.tileentities.SimpleEnchanterTile;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = "advancedenchantments", version = "1.0.0", name = "Advanced Enchantments")
public class AdvancedEnchantments {
    @Mod.Instance
    public static AdvancedEnchantments INSTANCE;

    @SidedProxy(clientSide = "bigbade.advancedenchantments.proxies.ClientProxy", serverSide = "bigbade.advancedenchantments.proxies.ServerProxy")
    public static CommonProxy PROXY;

    public static CreativeTabs TAB = new AdvancedEnchantmentsTab();

    public static final Logger LOGGER = LogManager.getLogger();

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        PROXY.preInitialization(event);
        LOGGER.info("Registering TileEntity SimpleTable!");
        GameRegistry.registerTileEntity(SimpleEnchanterTile.class, new ResourceLocation(Reference.MODID.toString(), "simple_table"));
        AdvancedEnchantments.LOGGER.info("Registering SimpleTable book entity TESR!");
        ClientRegistry.bindTileEntitySpecialRenderer(SimpleEnchanterTile.class, new SimpleEnchanterRenderer());
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        PROXY.initialization(event);
        NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, new GuiRegistry());
    }

    @Mod.EventHandler
    public void onPostInit(FMLPostInitializationEvent event) {
        PROXY.postInitialization(event);
    }
}
