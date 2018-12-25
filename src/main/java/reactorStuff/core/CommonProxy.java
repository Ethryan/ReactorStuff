package reactorStuff.core;

import java.io.File;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import reactorStuff.ReactorStuff;
import reactorStuff.blocks.BlocksRegistry;
import reactorStuff.items.ItemsRegistry;
import reactorStuff.items.tools.ToolEventHandler;
import reactorStuff.tile.TileRTG;

public class CommonProxy {
	
	
	public static Configuration config;
	public void preInit(FMLPreInitializationEvent e){
		File file=e.getModConfigurationDirectory();
		config=new Configuration(new File(file.getPath(),"reactor_stuff.cfg"));
		Config.readcfg();
		MinecraftForge.EVENT_BUS.register(ItemsRegistry.class);
		MinecraftForge.EVENT_BUS.register(BlocksRegistry.class);
		MinecraftForge.EVENT_BUS.register(new EventsHandler());
		MinecraftForge.EVENT_BUS.register(ToolEventHandler.class);
	}
	public void init(FMLInitializationEvent e){
		ItemsRegistry.initOreDict();
		Crafting.initMachineRecipes();
		Crafting.initRecipes();
		GameRegistry.registerWorldGenerator(new WorldGen(), 20);
		GameRegistry.registerTileEntity(TileRTG.class, new ResourceLocation(ReactorStuff.MODID, "rtg"));
	}
	public void postInit(FMLPostInitializationEvent e){
		ic2.core.init.MainConfig.get().getSub("worldgen").get("uranium/enabled").set(false);
		if(config.hasChanged()){
			config.save();
		}
	}
	
}
