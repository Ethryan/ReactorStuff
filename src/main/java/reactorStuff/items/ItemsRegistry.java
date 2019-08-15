package reactorStuff.items;


import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import reactorStuff.blocks.BlocksRegistry;
import reactorStuff.items.tools.ItemModAxe;
import reactorStuff.items.tools.ItemModHoe;
import reactorStuff.items.tools.ItemModSword;
import reactorStuff.items.tools.ItemPick;
import reactorStuff.items.tools.ItemShovel;
import reactorStuff.items.tools.ToolMaterials;

public class ItemsRegistry {
	
	public static ItemBase uranium_ingot=new ItemBase("uranium_ingot");
	public static ItemBase uranium_dust=new ItemBase("uranium_dust");
	public static ItemBase thorium_ingot=new ItemBase("thorium_ingot");
	public static ItemBase thorium_dust=new ItemBase("thorium_dust");
	public static ItemBase thorium_crushed=new ItemBase("dirty_thorium");
	public static ItemBase thorium_purified=new ItemBase("purified_thorium");
	public static ItemBase emerald_dust=new ItemBase("emerald_dust");
	public static ItemBase beryllium=new ItemBase("beryllium");
	public static ItemBase elerium=new ItemBase("elerium");
	public static ItemEleriumCrystal elerium_crystal=new ItemEleriumCrystal("elerium_crystal");
	public static ItemBase gem_blazonium=new ItemBase("gem_blazonium");
	public static ItemBase terminium_ingot=new ItemBase("terminium_ingot");
	public static ItemBase terminium_dust=new ItemBase("terminium_dust");
	public static ItemBase terminium_crushed=new ItemBase("terminium_crushed");
	public static ItemBase terminium_pure=new ItemBase("terminium_pure");
	public static ItemBase obsidian_stick=new ItemBase("obsidian_stick");
	
	public static ItemRadioactive thorium_chunk=new ItemRadioactive("thorium_chunk", 20*10);
	public static ItemRadioactive thorium_nugget=new ItemRadioactive("thorium_nugget", 20*10);
	public static ItemRadioactive uranium_233_chunk=new ItemRadioactive("uranium_233_chunk", 20*150);
	public static ItemRadioactive uranium_233_nugget=new ItemRadioactive("uranium_233_nugget", 20*150);
	public static ItemRadioactive thorium_fuel=new ItemRadioactive("thorium_fuel", 20*60);
	public static ItemRadioactive americium_chunk=new ItemRadioactive("americium", 20*150);
	public static ItemRadioactive americium_nugget=new ItemRadioactive("small_americium", 20*150);
	public static ItemRadioactive ambe_pellet=new ItemRadioactive("ambe_pellet", 20*200);
	public static ItemRadioactive isotope_fuel=new ItemRadioactive("isotope_fuel", 20*10);
	public static ItemRadioactive triga_fuel=new ItemRadioactive("triga_fuel", 20*150);
	public static ItemRadioactive small_lawrencium=new ItemRadioactive("small_lawrencium", 20*150);
	public static ItemRadioactive lawrencium=new ItemRadioactive("lawrencium", 20*150);
	public static ItemRadioactive terminium_fuel=new ItemRadioactive("terminium_fuel", 20*60);
	public static ItemRadioactive terminium_252_chunk=new ItemRadioactive("terminium_252_chunk", 20*60);
	public static ItemRadioactive terminium_252_nugget=new ItemRadioactive("terminium_252_nugget", 20*60);
	public static ItemRadioactive terminium_254_chunk=new ItemRadioactive("terminium_254_chunk", 20*60);
	public static ItemRadioactive terminium_254_nugget=new ItemRadioactive("terminium_254_nugget", 20*60);
	public static ItemRadioactive californium_nugget=new ItemRadioactive("californium_nugget", 20*150);
	public static ItemRadioactive californium=new ItemRadioactive("californium", 20*150);
	public static ItemRadioactive blazonium_nugget=new ItemRadioactive("blazonium_nugget", 20*300);
	public static ItemRadioactive blazonium_fuel=new ItemRadioactive("blazonium_fuel", 20*200);
	public static ItemRadioactive cfbe_pellet=new ItemRadioactive("cfbe_pellet", 20*200);
	
	public static ItemThoriumFuelRod thorium_rod=new ItemThoriumFuelRod("thorium_rod", 1, 40000);
	public static ItemThoriumFuelRod thorium_rod_dual=new ItemThoriumFuelRod("thorium_rod_dual", 2, 40000);
	public static ItemThoriumFuelRod thorium_rod_quad=new ItemThoriumFuelRod("thorium_rod_quad", 4, 40000);
	public static ItemAmBeRod ambe_rod=new ItemAmBeRod("ambe_rod");
	public static ItemBreederUranium isotope_rod=new ItemBreederUranium("isotope_rod", 1, 40000);
	public static ItemBreederUranium isotope_rod_dual=new ItemBreederUranium("isotope_rod_dual", 2, 80000);
	public static ItemBreederUranium isotope_rod_quad=new ItemBreederUranium("isotope_rod_quad", 4, 160000);
	public static ItemTrigaFuel triga_rod=new ItemTrigaFuel("triga_rod", 1, 60000);
	public static ItemTrigaFuel triga_rod_dual=new ItemTrigaFuel("triga_rod_dual", 2, 60000);
	public static ItemTrigaFuel triga_rod_quad=new ItemTrigaFuel("triga_rod_quad", 4, 60000);
	public static ItemEleriumFuel elerium_rod=new ItemEleriumFuel("elerium_rod", 1, 200000);
	public static ItemEleriumFuel elerium_rod_dual=new ItemEleriumFuel("elerium_rod_dual", 2, 400000);
	public static ItemEleriumFuel elerium_rod_quad=new ItemEleriumFuel("elerium_rod_quad", 4, 800000);
	public static ItemTerminiumFuelRod terminium_rod=new ItemTerminiumFuelRod("terminium_rod", 1, 30000);
	public static ItemTerminiumFuelRod terminium_rod_dual=new ItemTerminiumFuelRod("terminium_rod_dual", 2, 30000);
	public static ItemTerminiumFuelRod terminium_rod_quad=new ItemTerminiumFuelRod("terminium_rod_quad", 4, 30000);
	public static ItemBlazoniumFuelRod blazonium_rod=new ItemBlazoniumFuelRod("blazonium_rod", 1, 10000);
	public static ItemBlazoniumFuelRod blazonium_rod_dual=new ItemBlazoniumFuelRod("blazonium_rod_dual", 2, 10000);
	public static ItemBlazoniumFuelRod blazonium_rod_quad=new ItemBlazoniumFuelRod("blazonium_rod_quad", 4, 10000);
	public static ItemAmBeRod cfbe_rod=new ItemAmBeRod("cfbe_rod");
	
	public static ItemDepletedFuel depleted_thorium=new ItemDepletedFuel("depleted_thorium_rod", 20*10);
	public static ItemDepletedFuel depleted_thorium_dual=new ItemDepletedFuel("depleted_thorium_rod_dual", 20*10);
	public static ItemDepletedFuel depleted_thorium_quad=new ItemDepletedFuel("depleted_thorium_rod_quad", 20*10);
	public static ItemDepletedFuel bred_uranium=new ItemDepletedFuel("bred_uranium_rod", 20*10);
	public static ItemDepletedFuel bred_uranium_dual=new ItemDepletedFuel("bred_uranium_rod_dual", 20*10);
	public static ItemDepletedFuel bred_uranium_quad=new ItemDepletedFuel("bred_uranium_rod_quad", 20*10);
	public static ItemDepletedFuel depleted_triga=new ItemDepletedFuel("depleted_triga_rod", 20*10);
	public static ItemDepletedFuel depleted_triga_dual=new ItemDepletedFuel("depleted_triga_rod_dual", 20*10);
	public static ItemDepletedFuel depleted_triga_quad=new ItemDepletedFuel("depleted_triga_rod_quad", 20*10);
	public static ItemDepletedFuel depleted_elerium=new ItemDepletedFuel("depleted_elerium_rod", 0);
	public static ItemDepletedFuel depleted_elerium_dual=new ItemDepletedFuel("depleted_elerium_rod_dual", 0);
	public static ItemDepletedFuel depleted_elerium_quad=new ItemDepletedFuel("depleted_elerium_rod_quad", 0);
	public static ItemDepletedFuel depleted_terminium_rod=new ItemDepletedFuel("depleted_terminium_rod", 20*10);
	public static ItemDepletedFuel depleted_terminium_rod_dual=new ItemDepletedFuel("depleted_terminium_rod_dual", 20*10);
	public static ItemDepletedFuel depleted_terminium_rod_quad=new ItemDepletedFuel("depleted_terminium_rod_quad", 20*10);
	public static ItemDepletedFuel depleted_blazonium_rod=new ItemDepletedFuel("depleted_blazonium_rod", 20*10);
	public static ItemDepletedFuel depleted_blazonium_rod_dual=new ItemDepletedFuel("depleted_blazonium_rod_dual", 20*10);
	public static ItemDepletedFuel depleted_blazonium_rod_quad=new ItemDepletedFuel("depleted_blazonium_rod_quad", 20*10);
	
	public static ItemBlockBase uranium_ore=new ItemBlockBase(BlocksRegistry.uranium);
	public static ItemBlockBase thorium_ore=new ItemBlockBase(BlocksRegistry.thorium);
	public static ItemBlockBase elerium_ore=new ItemBlockBase(BlocksRegistry.elerium);
	public static ItemBlockBase terminium_ore=new ItemBlockBase(BlocksRegistry.terminium);
	public static ItemBlockBase blazonium_ore=new ItemBlockBase(BlocksRegistry.blazonium);
	
	public static ItemBlockRTG RTG=new ItemBlockRTG(BlocksRegistry.RTG);
	
	public static ItemThoriumHammer hammer=new ItemThoriumHammer();
	public static ItemPick terminium_pick=new ItemPick("terminium_pick", ToolMaterials.TERMINIUM_TOOL);
	public static ItemPick blazonium_pick=new ItemPick("blazonium_pick", ToolMaterials.BLAZONIUM_TOOL);
	public static ItemShovel terminium_shovel=new ItemShovel(ToolMaterials.TERMINIUM_TOOL, "terminium_shovel");
	public static ItemShovel blazonium_shovel=new ItemShovel(ToolMaterials.BLAZONIUM_TOOL, "blazonium_shovel");
	public static ItemModAxe terminium_axe=new ItemModAxe(ToolMaterials.TERMINIUM_TOOL, 8.5F, -3F, "terminium_axe");
	public static ItemModAxe blazonium_axe=new ItemModAxe(ToolMaterials.BLAZONIUM_TOOL, 7.5F, -3F, "blazonium_axe");
	public static ItemModSword terminium_sword=new ItemModSword(ToolMaterials.TERMINIUM_TOOL, "terminium_sword");
	public static ItemModSword blazonium_sword=new ItemModSword(ToolMaterials.BLAZONIUM_TOOL, "blazonium_sword");
	public static ItemModHoe terminium_hoe=new ItemModHoe(ToolMaterials.TERMINIUM_TOOL, "terminium_hoe");
	public static ItemModHoe blazonium_hoe=new ItemModHoe(ToolMaterials.BLAZONIUM_TOOL, "blazonium_hoe");
	
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event){
		event.getRegistry().registerAll(
			uranium_dust,
			uranium_ingot,
			uranium_233_nugget,
			uranium_233_chunk,
			thorium_crushed,
			thorium_purified,
			thorium_dust,
			thorium_ingot,
			thorium_nugget,
			thorium_chunk,
			thorium_fuel,
			thorium_rod,
			thorium_rod_dual,
			thorium_rod_quad,
			hammer,
			depleted_thorium,
			depleted_thorium_dual,
			depleted_thorium_quad,
			isotope_fuel,
			isotope_rod,
			isotope_rod_dual,
			isotope_rod_quad,
			bred_uranium,
			bred_uranium_dual,
			bred_uranium_quad,
			triga_fuel,
			triga_rod,
			triga_rod_dual,
			triga_rod_quad,
			depleted_triga,
			depleted_triga_dual,
			depleted_triga_quad,
			emerald_dust,
			beryllium,
			americium_nugget,
			americium_chunk,
			ambe_pellet,
			ambe_rod,
			elerium_crystal,
			elerium,
			elerium_rod,
			elerium_rod_dual,
			elerium_rod_quad,
			depleted_elerium,
			depleted_elerium_dual,
			depleted_elerium_quad,
			small_lawrencium,
			lawrencium,
			gem_blazonium,
			terminium_rod,
			terminium_rod_dual,
			terminium_rod_quad,
			terminium_pick,
			blazonium_pick,
			terminium_shovel,
			terminium_axe,
			terminium_sword,
			terminium_hoe,
			blazonium_shovel,
			blazonium_axe,
			blazonium_sword,
			blazonium_hoe,
			terminium_dust,
			terminium_ingot,
			terminium_fuel,
			depleted_terminium_rod,
			depleted_terminium_rod_dual,
			depleted_terminium_rod_quad,
			terminium_crushed,
			terminium_pure,
			terminium_252_chunk,
			terminium_252_nugget,
			terminium_254_chunk,
			terminium_254_nugget,
			californium_nugget,
			californium,
			obsidian_stick,
			blazonium_rod,
			blazonium_rod_dual,
			blazonium_rod_quad,
			blazonium_nugget,
			blazonium_fuel,
			depleted_blazonium_rod,
			depleted_blazonium_rod_dual,
			depleted_blazonium_rod_quad,
			cfbe_rod, cfbe_pellet
		);
		event.getRegistry().registerAll(
			uranium_ore,
			thorium_ore,
			elerium_ore,
			RTG,
			terminium_ore,
			blazonium_ore
		);
	}
	
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event){
		uranium_ingot.initModel();
		uranium_dust.initModel();
		thorium_ingot.initModel();
		thorium_dust.initModel();
		thorium_chunk.initModel();
		thorium_nugget.initModel();
		uranium_233_chunk.initModel();
		uranium_233_nugget.initModel();
		thorium_fuel.initModel();
		thorium_rod.initModel();
		thorium_rod_dual.initModel();
		thorium_rod_quad.initModel();
		depleted_thorium.initModel();
		depleted_thorium_dual.initModel();
		depleted_thorium_quad.initModel();
		americium_chunk.initModel();
		americium_nugget.initModel();
		thorium_crushed.initModel();
		thorium_purified.initModel();
		emerald_dust.initModel();
		beryllium.initModel();
		ambe_pellet.initModel();
		ambe_rod.initModel();
		hammer.initModel();
		isotope_rod.initModel();
		isotope_rod_dual.initModel();
		isotope_rod_quad.initModel();
		bred_uranium.initModel();
		bred_uranium_dual.initModel();
		bred_uranium_quad.initModel();
		isotope_fuel.initModel();
		triga_fuel.initModel();
		triga_rod.initModel();
		triga_rod_dual.initModel();
		triga_rod_quad.initModel();
		depleted_triga.initModel();
		depleted_triga_dual.initModel();
		depleted_triga_quad.initModel();
		elerium.initModel();
		elerium_rod.initModel();
		elerium_rod_dual.initModel();
		elerium_rod_quad.initModel();
		elerium_crystal.initModel();
		depleted_elerium.initModel();
		depleted_elerium_dual.initModel();
		depleted_elerium_quad.initModel();
		small_lawrencium.initModel();
		lawrencium.initModel();
		gem_blazonium.initModel();
		terminium_rod.initModel();
		terminium_rod_dual.initModel();
		terminium_rod_quad.initModel();
		terminium_dust.initModel();
		terminium_ingot.initModel();
		terminium_fuel.initModel();
		depleted_terminium_rod.initModel();
		depleted_terminium_rod_dual.initModel();
		depleted_terminium_rod_quad.initModel();
		terminium_crushed.initModel();
		terminium_pure.initModel();
		terminium_252_chunk.initModel();
		terminium_252_nugget.initModel();
		terminium_254_chunk.initModel();
		terminium_254_nugget.initModel();
		californium.initModel();
		californium_nugget.initModel();
		obsidian_stick.initModel();
		blazonium_rod.initModel();
		blazonium_rod_dual.initModel();
		blazonium_rod_quad.initModel();
		depleted_blazonium_rod.initModel();
		depleted_blazonium_rod_dual.initModel();
		depleted_blazonium_rod_quad.initModel();
		blazonium_nugget.initModel();
		blazonium_fuel.initModel();
		cfbe_rod.initModel();
		cfbe_pellet.initModel();
		
		uranium_ore.initModel();
		thorium_ore.initModel();
		elerium_ore.initModel();
		terminium_ore.initModel();
		blazonium_ore.initModel();
		RTG.initModel();
		
		terminium_pick.initModel();
		blazonium_pick.initModel();
		terminium_shovel.initModel();
		terminium_axe.initModel();
		terminium_sword.initModel();
		terminium_hoe.initModel();
		blazonium_shovel.initModel();
		blazonium_axe.initModel();
		blazonium_sword.initModel();
		blazonium_hoe.initModel();
	}
	
	public static void initOreDict(){
		OreDictionary.registerOre("oreUranium", uranium_ore);
		OreDictionary.registerOre("oreThorium", thorium_ore);
		OreDictionary.registerOre("oreElerium", elerium_ore);
		OreDictionary.registerOre("oreMoscovium", elerium_ore);
		OreDictionary.registerOre("oreTerminium", terminium_ore);
		OreDictionary.registerOre("oreEinsteinium", terminium_ore);
		OreDictionary.registerOre("oreBlazonium", blazonium_ore);
		
		OreDictionary.registerOre("ingotUranium", uranium_ingot);
		OreDictionary.registerOre("dustUranium", uranium_dust);
		OreDictionary.registerOre("ingotThorium", thorium_ingot);
		OreDictionary.registerOre("dustThorium", thorium_dust);
		OreDictionary.registerOre("gemBlazonium", gem_blazonium);
		OreDictionary.registerOre("gemElerium", elerium_crystal);
		OreDictionary.registerOre("gemMoscovium", elerium_crystal);
		OreDictionary.registerOre("ingotTerminium", terminium_ingot);
		OreDictionary.registerOre("dustTerminium", terminium_dust);
		OreDictionary.registerOre("ingotEinsteinium", terminium_ingot);
		OreDictionary.registerOre("dustEinsteinium", terminium_dust);
		
		OreDictionary.registerOre("stickObsidian", obsidian_stick);
	}
	
}
