package reactorStuff.blocks;

import net.minecraft.block.Block;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import reactorStuff.core.Config;

public class BlocksRegistry {
	
	public static BlockGlowOre uranium=new BlockGlowOre("ore_uranium");
	public static BlockGlowOre thorium=new BlockGlowOre("ore_thorium");
	public static BlockGlowOre elerium=new BlockGlowOre("ore_elerium");
	public static BlockGlowOre terminium=new BlockGlowOre("ore_terminium");
	public static BlockGlowOre blazonium=new BlockGlowOre("ore_blazonium");
	
	public static BlockRTG RTG=new BlockRTG();
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event){
		event.getRegistry().registerAll(uranium, thorium, elerium, RTG, terminium, blazonium);
	}
	
	@SubscribeEvent
	public static void registerModel(ModelRegistryEvent event){
		if(!Config.optifine_compat){
			uranium.initModel();
			thorium.initModel();
			elerium.initModel();
			terminium.initModel();
			blazonium.initModel();
		}
	}
	
}
