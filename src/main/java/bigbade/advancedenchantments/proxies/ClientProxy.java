package bigbade.advancedenchantments.proxies;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.Objects;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), id));
	}
	
	@Override
	public void preInitialization(FMLPreInitializationEvent event) {
		
	}

	@Override
	public void initialization(FMLInitializationEvent event) {

	}

	@Override
	public void postInitialization(FMLPostInitializationEvent event) {

	}
}
