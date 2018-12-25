package reactorStuff.core;

import net.minecraftforge.common.config.Configuration;

public class Config {
	
	public static int uraniumRarity=10, thoriumRarity=20, blazoniumRarity=10, terminiumRarity=75;
	public static String elerium_base="minecraft:blocks/stone";
	public static boolean optifine_compat=true;
	
	public static void readcfg(){
		Configuration cfg=CommonProxy.config;
		try{
			cfg.load();
			initConfig(cfg);
		} catch(Exception e){
			System.out.println("Reactor Stuff mod could not load configs");
		} finally{
			if(cfg.hasChanged()){
				cfg.save();
			}
		}
	}
	
	public static void initConfig(Configuration cfg){
		cfg.addCustomCategoryComment("GENERAL", "general configuration");
		uraniumRarity=cfg.getInt("UraniumRarity", "GENERAL", 10, 0, 2000000000, "How common uranium ore is in the world. Due to quirkiness in the code when generating small clusters the actual amount of clusters is lower");
		thoriumRarity=cfg.getInt("ThoriumRarity", "GENERAL", 20, 0, 2000000000, "How common thorium ore is in the world. Due to quirkiness in the code when generating small clusters the actual amount of clusters is lower");
		terminiumRarity=cfg.getInt("TerminiumRarity", "GENERAL", 75, 0, 2000000000, "How common terminium ore is in the world. Most will try to generate in the void so use a high number. Due to quirkiness in the code when generating small clusters the actual amount of clusters is lower");
		blazoniumRarity=cfg.getInt("BlazoniumRarity", "GENERAL", 10, 0, 2000000000, "How common blazonium ore is in the world. Due to quirkiness in the code when generating small clusters the actual amount of clusters is lower");
		elerium_base=cfg.getString("EleriumBase", "GENERAL", "minecraft:blocks/stone", "The background texture for elerium ore. This only changes the block. The item needs to be modified trough a texture pack");
		optifine_compat=cfg.getBoolean("EnableGlowOre", "GENERAL", true, "If you are not using optifine set this to true to enable glowing ores");
		
	}
	
}
