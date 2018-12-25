package reactorStuff.core;

import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.Recipes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import reactorStuff.ReactorStuff;
import reactorStuff.items.ItemsRegistry;

public class Crafting {
	
	public static void initMachineRecipes(){
		IRecipeInput fuel_rod=Recipes.inputFactory.forStack(new ItemStack(Item.getByNameOrId("ic2:crafting"), 1, 9));
		IRecipeInput thorium_fuel=Recipes.inputFactory.forStack(new ItemStack(ItemsRegistry.thorium_fuel));
		ic2.api.recipe.Recipes.cannerBottle.addRecipe(fuel_rod, thorium_fuel, new ItemStack(ItemsRegistry.thorium_rod), false);
		IRecipeInput ambe_pellet=Recipes.inputFactory.forStack(new ItemStack(ItemsRegistry.ambe_pellet));
		Recipes.cannerBottle.addRecipe(fuel_rod, ambe_pellet, new ItemStack(ItemsRegistry.ambe_rod), false);
		IRecipeInput isotope_fuel=Recipes.inputFactory.forStack(new ItemStack(ItemsRegistry.isotope_fuel));
		Recipes.cannerBottle.addRecipe(fuel_rod, isotope_fuel, new ItemStack(ItemsRegistry.isotope_rod), false);
		IRecipeInput triga_fuel=Recipes.inputFactory.forStack(new ItemStack(ItemsRegistry.triga_fuel));
		Recipes.cannerBottle.addRecipe(fuel_rod, triga_fuel, new ItemStack(ItemsRegistry.triga_rod), false);
		IRecipeInput elerium_fuel=Recipes.inputFactory.forStack(new ItemStack(ItemsRegistry.elerium));
		Recipes.cannerBottle.addRecipe(fuel_rod, elerium_fuel, new ItemStack(ItemsRegistry.elerium_rod), false);
		IRecipeInput terminium_fuel=Recipes.inputFactory.forStack(new ItemStack(ItemsRegistry.terminium_fuel));
		Recipes.cannerBottle.addRecipe(fuel_rod, terminium_fuel, new ItemStack(ItemsRegistry.terminium_rod), false);
		IRecipeInput blazonium_fuel=Recipes.inputFactory.forStack(new ItemStack(ItemsRegistry.blazonium_fuel));
		Recipes.cannerBottle.addRecipe(fuel_rod, blazonium_fuel, new ItemStack(ItemsRegistry.blazonium_rod), false);
		IRecipeInput cfbe_pellet=Recipes.inputFactory.forStack(new ItemStack(ItemsRegistry.cfbe_pellet));
		Recipes.cannerBottle.addRecipe(fuel_rod, cfbe_pellet, new ItemStack(ItemsRegistry.cfbe_rod), false);
		
		IRecipeInput depleted_thorium=Recipes.inputFactory.forStack(new ItemStack(ItemsRegistry.depleted_thorium));
		IRecipeInput depleted_thorium_dual=Recipes.inputFactory.forStack(new ItemStack(ItemsRegistry.depleted_thorium_dual));
		IRecipeInput depleted_thorium_quad=Recipes.inputFactory.forStack(new ItemStack(ItemsRegistry.depleted_thorium_quad));
		NBTTagCompound heat=new NBTTagCompound();
		heat.setInteger("minHeat", 5000);
		ItemStack iron=OreDictionary.getOres("dustIron").get(0).copy();
		ic2.api.recipe.Recipes.centrifuge.addRecipe(depleted_thorium, heat, false, 
				new ItemStack(ItemsRegistry.thorium_chunk, 2),
				new ItemStack(ItemsRegistry.uranium_233_nugget, 6),
				iron.copy());
		iron.setCount(3);
		ic2.api.recipe.Recipes.centrifuge.addRecipe(depleted_thorium_dual, heat, false, 
				new ItemStack(ItemsRegistry.thorium_chunk, 4),
				new ItemStack(ItemsRegistry.uranium_233_nugget, 12),
				iron.copy());
		iron.setCount(6);
		ic2.api.recipe.Recipes.centrifuge.addRecipe(depleted_thorium_quad, heat, false, 
				new ItemStack(ItemsRegistry.thorium_chunk, 8),
				new ItemStack(ItemsRegistry.uranium_233_nugget, 24),
				iron.copy());
		IRecipeInput depleted_mox=Recipes.inputFactory.forStack(new ItemStack(Item.getByNameOrId("ic2:nuclear"), 1, 14));
		IRecipeInput depleted_mox_dual=Recipes.inputFactory.forStack(new ItemStack(Item.getByNameOrId("ic2:nuclear"), 1, 15));
		IRecipeInput depleted_mox_quad=Recipes.inputFactory.forStack(new ItemStack(Item.getByNameOrId("ic2:nuclear"), 1, 16));
		iron.setCount(1);
		ic2.api.recipe.Recipes.centrifuge.addRecipe(depleted_mox, heat, true, 
				new ItemStack(Item.getByNameOrId("ic2:nuclear"), 4, 7),
				new ItemStack(ItemsRegistry.americium_nugget),
				iron.copy());
		iron.setCount(3);
		ic2.api.recipe.Recipes.centrifuge.addRecipe(depleted_mox_dual, heat, true, 
				new ItemStack(Item.getByNameOrId("ic2:nuclear"), 8, 7),
				new ItemStack(ItemsRegistry.americium_nugget, 2),
				iron.copy());
		iron.setCount(6);
		ic2.api.recipe.Recipes.centrifuge.addRecipe(depleted_mox_quad, heat, true, 
				new ItemStack(Item.getByNameOrId("ic2:nuclear"), 16, 7),
				new ItemStack(ItemsRegistry.americium_nugget, 4),
				iron.copy());
		NBTTagCompound heat3=new NBTTagCompound();
		heat3.setInteger("minHeat", 4000);
		IRecipeInput bred_uranium=Recipes.inputFactory.forStack(new ItemStack(ItemsRegistry.bred_uranium));
		IRecipeInput dual_bred_uranium=Recipes.inputFactory.forStack(new ItemStack(ItemsRegistry.bred_uranium_dual));
		IRecipeInput quad_bred_uranium=Recipes.inputFactory.forStack(new ItemStack(ItemsRegistry.bred_uranium_quad));
		iron.setCount(1);
		Recipes.centrifuge.addRecipe(bred_uranium, heat3, false,
				new ItemStack(Item.getByNameOrId("ic2:nuclear"), 2, 2),
				new ItemStack(Item.getByNameOrId("ic2:nuclear"), 2, 7),
				iron.copy());
		iron.setCount(3);
		Recipes.centrifuge.addRecipe(dual_bred_uranium, heat3, false,
				new ItemStack(Item.getByNameOrId("ic2:nuclear"), 4, 2),
				new ItemStack(Item.getByNameOrId("ic2:nuclear"), 4, 7),
				iron.copy());
		iron.setCount(6);
		Recipes.centrifuge.addRecipe(quad_bred_uranium, heat3, false,
				new ItemStack(Item.getByNameOrId("ic2:nuclear"), 8, 2),
				new ItemStack(Item.getByNameOrId("ic2:nuclear"), 8, 7),
				iron.copy());
		
		IRecipeInput triga=Recipes.inputFactory.forStack(new ItemStack(ItemsRegistry.depleted_triga));
		IRecipeInput triga_dual=Recipes.inputFactory.forStack(new ItemStack(ItemsRegistry.depleted_triga_dual));
		IRecipeInput triga_quad=Recipes.inputFactory.forStack(new ItemStack(ItemsRegistry.depleted_triga_quad));
		ItemStack tin=OreDictionary.getOres("dustTin").get(0).copy();
		tin.setCount(3);
		iron.setCount(1);
		Recipes.centrifuge.addRecipe(triga, heat3, false,
				tin.copy(),
				new ItemStack(Item.getByNameOrId("ic2:nuclear"), 1, 7),
				iron.copy());
		tin.setCount(6);
		iron.setCount(3);
		Recipes.centrifuge.addRecipe(triga_dual, heat3, false,
				tin.copy(),
				new ItemStack(Item.getByNameOrId("ic2:nuclear"), 2, 7),
				iron.copy());
		tin.setCount(12);
		iron.setCount(6);
		Recipes.centrifuge.addRecipe(triga_quad, heat3, false,
				tin.copy(),
				new ItemStack(Item.getByNameOrId("ic2:nuclear"), 4, 7),
				iron.copy());
		
		IRecipeInput delerium=Recipes.inputFactory.forStack(new ItemStack(ItemsRegistry.depleted_elerium));
		IRecipeInput delerium_dual=Recipes.inputFactory.forStack(new ItemStack(ItemsRegistry.depleted_elerium_dual));
		IRecipeInput delerium_quad=Recipes.inputFactory.forStack(new ItemStack(ItemsRegistry.depleted_elerium_quad));
		iron.setCount(1);
		Recipes.centrifuge.addRecipe(delerium, heat, false,
				new ItemStack(ItemsRegistry.small_lawrencium),
				iron.copy());
		iron.setCount(3);
		Recipes.centrifuge.addRecipe(delerium_dual, heat, false,
				new ItemStack(ItemsRegistry.small_lawrencium, 2),
				iron.copy());
		iron.setCount(6);
		Recipes.centrifuge.addRecipe(delerium_quad, heat, false,
				new ItemStack(ItemsRegistry.small_lawrencium, 4),
				iron.copy());
		
		IRecipeInput elcr=Recipes.inputFactory.forStack(new ItemStack(ItemsRegistry.elerium_crystal));
		Recipes.centrifuge.addRecipe(elcr, heat, false, new ItemStack(ItemsRegistry.elerium));
		
		IRecipeInput terminium_crush=Recipes.inputFactory.forStack(new ItemStack(ItemsRegistry.terminium_crushed));
		IRecipeInput terminium_pure=Recipes.inputFactory.forStack(new ItemStack(ItemsRegistry.terminium_pure));
		IRecipeInput terminium_dust=Recipes.inputFactory.forOreDict("dustTerminium");
		Recipes.centrifuge.addRecipe(terminium_pure, heat, false,
				new ItemStack(ItemsRegistry.terminium_252_chunk,4),
				new ItemStack(ItemsRegistry.terminium_252_nugget,7),
				new ItemStack(ItemsRegistry.terminium_254_nugget,4));
		Recipes.centrifuge.addRecipe(terminium_crush, heat, false,
				new ItemStack(ItemsRegistry.terminium_252_chunk,3),
				new ItemStack(ItemsRegistry.terminium_252_nugget,8),
				new ItemStack(ItemsRegistry.terminium_254_nugget,2));
		Recipes.centrifuge.addRecipe(terminium_dust, heat, false,
				new ItemStack(ItemsRegistry.terminium_252_chunk,4),
				new ItemStack(ItemsRegistry.terminium_252_nugget,7),
				new ItemStack(ItemsRegistry.terminium_254_nugget,4));
		
		IRecipeInput terminium_depleted=Recipes.inputFactory.forStack(new ItemStack(ItemsRegistry.depleted_terminium_rod));
		IRecipeInput terminium_depleted_dual=Recipes.inputFactory.forStack(new ItemStack(ItemsRegistry.depleted_terminium_rod_dual));
		IRecipeInput terminium_depleted_quad=Recipes.inputFactory.forStack(new ItemStack(ItemsRegistry.depleted_terminium_rod_quad));
		iron.setCount(1);
		Recipes.centrifuge.addRecipe(terminium_depleted, heat, false,
				new ItemStack(ItemsRegistry.terminium_252_chunk,3),
				new ItemStack(ItemsRegistry.californium_nugget,2),
				iron.copy());
		iron.setCount(3);
		Recipes.centrifuge.addRecipe(terminium_depleted_dual, heat, false,
				new ItemStack(ItemsRegistry.terminium_252_chunk,6),
				new ItemStack(ItemsRegistry.californium_nugget,4),
				iron.copy());
		iron.setCount(6);
		Recipes.centrifuge.addRecipe(terminium_depleted_quad, heat, false,
				new ItemStack(ItemsRegistry.terminium_252_chunk,12),
				new ItemStack(ItemsRegistry.californium_nugget,8),
				iron.copy());
		
		IRecipeInput blazonium_crystal=Recipes.inputFactory.forStack(new ItemStack(ItemsRegistry.gem_blazonium));
		Recipes.centrifuge.addRecipe(blazonium_crystal, heat, false,
				new ItemStack(Items.BLAZE_POWDER,12),
				new ItemStack(ItemsRegistry.blazonium_nugget,2));
		IRecipeInput depleted_blazonium=Recipes.inputFactory.forStack(new ItemStack(ItemsRegistry.depleted_blazonium_rod));
		IRecipeInput depleted_blazonium_dual=Recipes.inputFactory.forStack(new ItemStack(ItemsRegistry.depleted_blazonium_rod_dual));
		IRecipeInput depleted_blazonium_quad=Recipes.inputFactory.forStack(new ItemStack(ItemsRegistry.depleted_blazonium_rod_quad));
		iron.setCount(1);
		Recipes.centrifuge.addRecipe(depleted_blazonium, heat, false,
				new ItemStack(Item.getByNameOrId("ic2:nuclear"), 3, 7),
				new ItemStack(Items.BLAZE_POWDER, 3),
				iron.copy());
		iron.setCount(3);
		Recipes.centrifuge.addRecipe(depleted_blazonium_dual, heat, false,
				new ItemStack(Item.getByNameOrId("ic2:nuclear"), 6, 7),
				new ItemStack(Items.BLAZE_POWDER, 6),
				iron.copy());
		iron.setCount(6);
		Recipes.centrifuge.addRecipe(depleted_blazonium_quad, heat, false,
				new ItemStack(Item.getByNameOrId("ic2:nuclear"), 12, 7),
				new ItemStack(Items.BLAZE_POWDER, 12),
				iron.copy());
		
		IRecipeInput th_ore=Recipes.inputFactory.forOreDict("oreThorium");
		Recipes.macerator.addRecipe(th_ore, null, false, new ItemStack(ItemsRegistry.thorium_crushed, 2));
		IRecipeInput th_ingot=Recipes.inputFactory.forOreDict("ingotThorium");
		Recipes.macerator.addRecipe(th_ingot, null, false, new ItemStack(ItemsRegistry.thorium_dust));
		IRecipeInput u_ingot=Recipes.inputFactory.forOreDict("ingotUranium");
		Recipes.macerator.addRecipe(u_ingot, null, false, new ItemStack(ItemsRegistry.uranium_dust));
		IRecipeInput emerald=Recipes.inputFactory.forOreDict("gemEmerald");
		Recipes.macerator.addRecipe(emerald, null, false, new ItemStack(ItemsRegistry.emerald_dust));
		IRecipeInput terminium_ore=Recipes.inputFactory.forOreDict("oreTerminium");
		Recipes.macerator.addRecipe(terminium_ore, null, false, new ItemStack(ItemsRegistry.terminium_crushed, 2));
		
		
		NBTTagCompound fluid=new NBTTagCompound();
		fluid.setInteger("amount", 1000);
		IRecipeInput th_cr=Recipes.inputFactory.forStack(new ItemStack(ItemsRegistry.thorium_crushed));
		Recipes.oreWashing.addRecipe(th_cr, fluid, false,
				new ItemStack(ItemsRegistry.thorium_purified),
				new ItemStack(Item.getByNameOrId("ic2:dust"), 2, 23),
				new ItemStack(Item.getByNameOrId("ic2:dust"), 1, 15));
		Recipes.oreWashing.addRecipe(terminium_crush, fluid, false,
				new ItemStack(ItemsRegistry.terminium_pure),
				new ItemStack(Item.getByNameOrId("ic2:nuclear"), 1, 6));
		
		NBTTagCompound heat2=new NBTTagCompound();
		heat2.setInteger("minHeat", 3000);
		IRecipeInput  u_dust=Recipes.inputFactory.forOreDict("dustUranium");
		Recipes.centrifuge.addRecipe(u_dust, heat2, false,
				new ItemStack(Item.getByNameOrId("ic2:nuclear"), 2, 5),
				new ItemStack(Item.getByNameOrId("ic2:nuclear"), 5, 2));
		Recipes.centrifuge.addRecipe(th_cr, heat2, false,
				new ItemStack(Item.getByNameOrId("ic2:nuclear"), 1, 6),
				new ItemStack(ItemsRegistry.thorium_chunk, 4),
				new ItemStack(Item.getByNameOrId("ic2:dust"), 1, 15));
		IRecipeInput th_pr=Recipes.inputFactory.forStack(new ItemStack(ItemsRegistry.thorium_purified));
		Recipes.centrifuge.addRecipe(th_pr, heat2, false,
				new ItemStack(Item.getByNameOrId("ic2:nuclear"), 2, 6),
				new ItemStack(ItemsRegistry.thorium_chunk, 5));
		IRecipeInput th_dust=Recipes.inputFactory.forOreDict("dustThorium");
		Recipes.centrifuge.addRecipe(th_dust, heat2, false,
				new ItemStack(Item.getByNameOrId("ic2:nuclear"), 2, 6),
				new ItemStack(ItemsRegistry.thorium_chunk, 5));
		IRecipeInput emerald_dust=Recipes.inputFactory.forStack(new ItemStack(ItemsRegistry.emerald_dust, 4));
		Recipes.centrifuge.addRecipe(emerald_dust, heat2, false, new ItemStack(ItemsRegistry.beryllium));
		IRecipeInput ambe_rod=Recipes.inputFactory.forStack(new ItemStack(ItemsRegistry.ambe_rod));
		iron.setCount(1);
		Recipes.centrifuge.addRecipe(ambe_rod, heat, false,
				new ItemStack(ItemsRegistry.americium_chunk),
				iron.copy());
		IRecipeInput cfbe_rod=Recipes.inputFactory.forStack(new ItemStack(ItemsRegistry.cfbe_rod));
		Recipes.centrifuge.addRecipe(cfbe_rod, heat, false,
				new ItemStack(ItemsRegistry.californium),
				iron.copy());
		
		IRecipeInput rtg_empty=Recipes.inputFactory.forStack(new ItemStack(ItemsRegistry.RTG,1,0));
		IRecipeInput rtg_plutonium=Recipes.inputFactory.forStack(new ItemStack(ItemsRegistry.RTG,1,1));
		IRecipeInput rtg_americium=Recipes.inputFactory.forStack(new ItemStack(ItemsRegistry.RTG,1,2));
		IRecipeInput rtg_lawrencium=Recipes.inputFactory.forStack(new ItemStack(ItemsRegistry.RTG,1,3));
		IRecipeInput rtg_californium=Recipes.inputFactory.forStack(new ItemStack(ItemsRegistry.RTG,1,4));
		IRecipeInput plutonium=Recipes.inputFactory.forStack(new ItemStack(Item.getByNameOrId("ic2:nuclear"), 1, 3));
		IRecipeInput americium=Recipes.inputFactory.forStack(new ItemStack(ItemsRegistry.americium_chunk));
		IRecipeInput lawrencium=Recipes.inputFactory.forStack(new ItemStack(ItemsRegistry.lawrencium));
		IRecipeInput californium=Recipes.inputFactory.forStack(new ItemStack(ItemsRegistry.californium));
		Recipes.cannerBottle.addRecipe(rtg_empty, plutonium, new ItemStack(ItemsRegistry.RTG,1,1), false);
		Recipes.cannerBottle.addRecipe(rtg_empty, americium, new ItemStack(ItemsRegistry.RTG,1,2), false);
		Recipes.cannerBottle.addRecipe(rtg_empty, lawrencium, new ItemStack(ItemsRegistry.RTG,1,3), false);
		Recipes.cannerBottle.addRecipe(rtg_empty, californium, new ItemStack(ItemsRegistry.RTG,1,4), false);
		Recipes.extractor.addRecipe(rtg_plutonium, null, false, new ItemStack(Item.getByNameOrId("ic2:nuclear"), 1, 3));
		Recipes.extractor.addRecipe(rtg_americium, null, false, new ItemStack(ItemsRegistry.americium_chunk));
		Recipes.extractor.addRecipe(rtg_lawrencium, null, false, new ItemStack(ItemsRegistry.lawrencium));
		Recipes.extractor.addRecipe(rtg_californium, null, false, new ItemStack(ItemsRegistry.californium));
	}
	
	public static void initRecipes(){
		GameRegistry.addSmelting(ItemsRegistry.uranium_dust, new ItemStack(ItemsRegistry.uranium_ingot), 0.1F);
		GameRegistry.addSmelting(ItemsRegistry.thorium_dust, new ItemStack(ItemsRegistry.thorium_ingot), 0.1F);
		GameRegistry.addSmelting(ItemsRegistry.uranium_ore, new ItemStack(ItemsRegistry.uranium_ingot), 1F);
		GameRegistry.addSmelting(ItemsRegistry.thorium_ore, new ItemStack(ItemsRegistry.thorium_ingot), 1F);
		GameRegistry.addSmelting(ItemsRegistry.thorium_purified, new ItemStack(ItemsRegistry.thorium_ingot), 1F);
		GameRegistry.addSmelting(new ItemStack(Item.getByNameOrId("ic2:purified"), 1, 6), new ItemStack(ItemsRegistry.uranium_ingot), 1F);
		GameRegistry.addSmelting(ItemsRegistry.terminium_dust, new ItemStack(ItemsRegistry.terminium_ingot), 0.1F);
		GameRegistry.addSmelting(ItemsRegistry.terminium_ore, new ItemStack(ItemsRegistry.terminium_ingot), 1F);
		GameRegistry.addSmelting(ItemsRegistry.terminium_pure, new ItemStack(ItemsRegistry.terminium_ingot), 1F);
		
		ForgeRegistries.RECIPES.register(new ShapelessOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), ItemsRegistry.thorium_chunk, new Object[]{
				ItemsRegistry.thorium_nugget, ItemsRegistry.thorium_nugget, ItemsRegistry.thorium_nugget,
				ItemsRegistry.thorium_nugget, ItemsRegistry.thorium_nugget, ItemsRegistry.thorium_nugget,
				ItemsRegistry.thorium_nugget, ItemsRegistry.thorium_nugget, ItemsRegistry.thorium_nugget
		}).setRegistryName("thorium_chunk"));
		ForgeRegistries.RECIPES.register(new ShapelessOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), new ItemStack(ItemsRegistry.thorium_nugget, 9), new Object[]{
				ItemsRegistry.thorium_chunk
		}).setRegistryName("thorium_nugget"));
		ForgeRegistries.RECIPES.register(new ShapelessOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), ItemsRegistry.uranium_233_chunk, new Object[]{
				ItemsRegistry.uranium_233_nugget, ItemsRegistry.uranium_233_nugget, ItemsRegistry.uranium_233_nugget,
				ItemsRegistry.uranium_233_nugget, ItemsRegistry.uranium_233_nugget, ItemsRegistry.uranium_233_nugget,
				ItemsRegistry.uranium_233_nugget, ItemsRegistry.uranium_233_nugget, ItemsRegistry.uranium_233_nugget
		}).setRegistryName("uranium_chunk"));
		ForgeRegistries.RECIPES.register(new ShapelessOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), new ItemStack(ItemsRegistry.uranium_233_nugget, 9), new Object[]{
				ItemsRegistry.uranium_233_chunk
		}).setRegistryName("uranium_nugget"));
		ForgeRegistries.RECIPES.register(new ShapelessOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), ItemsRegistry.americium_chunk, new Object[]{
				ItemsRegistry.americium_nugget, ItemsRegistry.americium_nugget, ItemsRegistry.americium_nugget,
				ItemsRegistry.americium_nugget, ItemsRegistry.americium_nugget, ItemsRegistry.americium_nugget,
				ItemsRegistry.americium_nugget, ItemsRegistry.americium_nugget, ItemsRegistry.americium_nugget
		}).setRegistryName("americium_chunk"));
		ForgeRegistries.RECIPES.register(new ShapelessOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), new ItemStack(ItemsRegistry.americium_nugget, 9), new Object[]{
				ItemsRegistry.americium_chunk
		}).setRegistryName("americium_nugget"));
		ForgeRegistries.RECIPES.register(new ShapelessOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), new ItemStack(Item.getByNameOrId("ic2:nuclear"), 1, 2), new Object[]{
				new ItemStack(Item.getByNameOrId("ic2:nuclear"), 1, 6), new ItemStack(Item.getByNameOrId("ic2:nuclear"), 1, 6), new ItemStack(Item.getByNameOrId("ic2:nuclear"), 1, 6),
				new ItemStack(Item.getByNameOrId("ic2:nuclear"), 1, 6), new ItemStack(Item.getByNameOrId("ic2:nuclear"), 1, 6), new ItemStack(Item.getByNameOrId("ic2:nuclear"), 1, 6),
				new ItemStack(Item.getByNameOrId("ic2:nuclear"), 1, 6), new ItemStack(Item.getByNameOrId("ic2:nuclear"), 1, 6), new ItemStack(Item.getByNameOrId("ic2:nuclear"), 1, 6)
		}).setRegistryName("uranium238_chunk"));
		ForgeRegistries.RECIPES.register(new ShapelessOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), new ItemStack(Item.getByNameOrId("ic2:nuclear"), 9, 6), new Object[]{
				new ItemStack(Item.getByNameOrId("ic2:nuclear"), 1, 2)
		}).setRegistryName("uranium238_nugget"));
		ForgeRegistries.RECIPES.register(new ShapelessOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), ItemsRegistry.ambe_pellet, new Object[]{
				ItemsRegistry.americium_chunk, ItemsRegistry.beryllium
		}).setRegistryName("ambe_pellet"));
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), ItemsRegistry.thorium_fuel, new Object[]{
				"TTT",
				"UUU",
				"TTT",
				'T', ItemsRegistry.thorium_chunk, 'U', ItemsRegistry.uranium_233_nugget
		}).setRegistryName("thorium_fuel1"));
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), ItemsRegistry.thorium_fuel, new Object[]{
				"TTT",
				"UUU",
				"TTT",
				'T', ItemsRegistry.thorium_chunk, 'U', new ItemStack(Item.getByNameOrId("ic2:nuclear"),1,5)
		}).setRegistryName("thorium_fuel2"));
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), Item.getByNameOrId("ic2:nuclear"), new Object[]{
				"TTT",
				"UUU",
				"TTT",
				'T', new ItemStack(Item.getByNameOrId("ic2:nuclear"),1,2), 'U', ItemsRegistry.uranium_233_nugget
		}).setRegistryName("uranium_fuel"));
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), new ItemStack(Item.getByNameOrId("ic2:nuclear"),1,4), new Object[]{
				"TTT",
				"UUU",
				"TTT",
				'T', new ItemStack(Item.getByNameOrId("ic2:nuclear"),1,2), 'U', new ItemStack(Item.getByNameOrId("ic2:nuclear"),1,7)
		}).setRegistryName("mox"));
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), ItemsRegistry.thorium_rod_dual, new Object[]{
				"TUT",
				'T', ItemsRegistry.thorium_rod, 'U', new ItemStack(Item.getByNameOrId("ic2:plate"),1,3)
		}).setRegistryName("dual_thorium"));
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), ItemsRegistry.thorium_rod_quad, new Object[]{
				"TUT",
				"CUC",
				"TUT",
				'T', ItemsRegistry.thorium_rod, 'U', new ItemStack(Item.getByNameOrId("ic2:plate"),1,3), 'C', new ItemStack(Item.getByNameOrId("ic2:plate"),1,1)
		}).setRegistryName("quad_thorium"));
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), ItemsRegistry.hammer, new Object[]{
				"TGT",
				"TST",
				" S ",
				'T', "ingotThorium", 'G', Blocks.GLOWSTONE, 'S', "stickWood"
		}).setRegistryName("hammer"));
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), ItemsRegistry.isotope_fuel, new Object[]{
				"TTT",
				"UUU",
				"TTT",
				'T', new ItemStack(Item.getByNameOrId("ic2:nuclear"),1,2), 'U', "dustCoal"
		}).setRegistryName("isotope_fuel"));
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), ItemsRegistry.isotope_rod_dual, new Object[]{
				"TUT",
				'T', ItemsRegistry.isotope_rod, 'U', new ItemStack(Item.getByNameOrId("ic2:plate"),1,3)
		}).setRegistryName("dual_isotope"));
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), ItemsRegistry.isotope_rod_quad, new Object[]{
				"TUT",
				"CUC",
				"TUT",
				'T', ItemsRegistry.isotope_rod, 'U', new ItemStack(Item.getByNameOrId("ic2:plate"),1,3), 'C', new ItemStack(Item.getByNameOrId("ic2:plate"),1,1)
		}).setRegistryName("quad_isotope"));
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), ItemsRegistry.triga_fuel, new Object[]{
				"STS",
				"UUU",
				"STS",
				'T', new ItemStack(Item.getByNameOrId("ic2:nuclear"),1,2), 'U', ItemsRegistry.uranium_233_nugget, 'S', "dustTin"
		}).setRegistryName("triga_fuel1"));
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), ItemsRegistry.triga_fuel, new Object[]{
				"STS",
				"UUU",
				"STS",
				'T', new ItemStack(Item.getByNameOrId("ic2:nuclear"),1,2), 'U', new ItemStack(Item.getByNameOrId("ic2:nuclear"),1,5), 'S', "dustTin"
		}).setRegistryName("triga_fuel2"));
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), ItemsRegistry.triga_rod_dual, new Object[]{
				"TUT",
				'T', ItemsRegistry.triga_rod, 'U', new ItemStack(Item.getByNameOrId("ic2:plate"),1,3)
		}).setRegistryName("dual_triga"));
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), ItemsRegistry.triga_rod_quad, new Object[]{
				"TUT",
				"CUC",
				"TUT",
				'T', ItemsRegistry.triga_rod, 'U', new ItemStack(Item.getByNameOrId("ic2:plate"),1,3), 'C', new ItemStack(Item.getByNameOrId("ic2:plate"),1,1)
		}).setRegistryName("quad_triga"));
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), ItemsRegistry.elerium_rod_dual, new Object[]{
				"TUT",
				'T', ItemsRegistry.elerium_rod, 'U', new ItemStack(Item.getByNameOrId("ic2:plate"),1,3)
		}).setRegistryName("dual_elerium"));
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), ItemsRegistry.elerium_rod_quad, new Object[]{
				"TUT",
				"CUC",
				"TUT",
				'T', ItemsRegistry.elerium_rod, 'U', new ItemStack(Item.getByNameOrId("ic2:plate"),1,3), 'C', new ItemStack(Item.getByNameOrId("ic2:plate"),1,1)
		}).setRegistryName("quad_elerium"));
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), ItemsRegistry.RTG, new Object[]{
				"CLC",
				"CFC",
				"CLC",
				'C', new ItemStack(Item.getByNameOrId("ic2:plate"),1,1), 'L', new ItemStack(Item.getByNameOrId("ic2:plate"),1,14), 'F', new ItemStack(Item.getByNameOrId("ic2:crafting"),1,9)
		}).setRegistryName("rtg_empty"));
		/*ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), new ItemStack(ItemsRegistry.elerium_core, 1, 101), new Object[]{
				"ACA",
				"N N",
				"AVA",
				'A', new ItemStack(Item.getByNameOrId("ic2:crafting"),1,3), 'C', new ItemStack(Item.getByNameOrId("ic2:crafting"),1,2), 'V', new ItemStack(Item.getByNameOrId("ic2:advanced_heat_vent")), 'N', ItemsRegistry.ambe_rod
		}).setRegistryName("elerium_core"));
		ForgeRegistries.RECIPES.register(new ShapelessOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), ItemsRegistry.elerium_core, new Object[]{
				new ItemStack(ItemsRegistry.elerium_core,1,101), ItemsRegistry.elerium_rod
		}).setRegistryName("elerium_refuel"));
		ForgeRegistries.RECIPES.register(new ShapelessOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), ItemsRegistry.elerium_rod, new Object[]{
				ItemsRegistry.elerium_core
		}).setRegistryName("elerium_defuel"));*/
		ForgeRegistries.RECIPES.register(new ShapelessOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), ItemsRegistry.terminium_252_chunk, new Object[]{
				ItemsRegistry.terminium_252_nugget, ItemsRegistry.terminium_252_nugget, ItemsRegistry.terminium_252_nugget,
				ItemsRegistry.terminium_252_nugget, ItemsRegistry.terminium_252_nugget, ItemsRegistry.terminium_252_nugget,
				ItemsRegistry.terminium_252_nugget, ItemsRegistry.terminium_252_nugget, ItemsRegistry.terminium_252_nugget
		}).setRegistryName("terminium_252_chunk"));
		ForgeRegistries.RECIPES.register(new ShapelessOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), ItemsRegistry.terminium_254_chunk, new Object[]{
				ItemsRegistry.terminium_254_nugget, ItemsRegistry.terminium_254_nugget, ItemsRegistry.terminium_254_nugget,
				ItemsRegistry.terminium_254_nugget, ItemsRegistry.terminium_254_nugget, ItemsRegistry.terminium_254_nugget,
				ItemsRegistry.terminium_254_nugget, ItemsRegistry.terminium_254_nugget, ItemsRegistry.terminium_254_nugget
		}).setRegistryName("terminium_254_chunk"));
		ForgeRegistries.RECIPES.register(new ShapelessOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), new ItemStack(ItemsRegistry.terminium_254_nugget,9), new Object[]{
				ItemsRegistry.terminium_254_chunk
		}).setRegistryName("terminium_254_nugget"));
		ForgeRegistries.RECIPES.register(new ShapelessOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), new ItemStack(ItemsRegistry.terminium_252_nugget,9), new Object[]{
				ItemsRegistry.terminium_252_chunk
		}).setRegistryName("terminium_252_nugget"));
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), ItemsRegistry.terminium_rod_dual, new Object[]{
				"TUT",
				'T', ItemsRegistry.terminium_rod, 'U', new ItemStack(Item.getByNameOrId("ic2:plate"),1,3)
		}).setRegistryName("dual_terminium"));
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), ItemsRegistry.terminium_rod_quad, new Object[]{
				"TUT",
				"CUC",
				"TUT",
				'T', ItemsRegistry.terminium_rod, 'U', new ItemStack(Item.getByNameOrId("ic2:plate"),1,3), 'C', new ItemStack(Item.getByNameOrId("ic2:plate"),1,1)
		}).setRegistryName("quad_terminium"));
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), ItemsRegistry.terminium_fuel, new Object[]{
				"TTT",
				"UUU",
				"TTT",
				'T', ItemsRegistry.terminium_252_chunk, 'U', ItemsRegistry.terminium_254_nugget
		}).setRegistryName("terminium_fuel"));
		ForgeRegistries.RECIPES.register(new ShapelessOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), ItemsRegistry.californium, new Object[]{
				ItemsRegistry.californium_nugget, ItemsRegistry.californium_nugget, ItemsRegistry.californium_nugget,
				ItemsRegistry.californium_nugget, ItemsRegistry.californium_nugget, ItemsRegistry.californium_nugget,
				ItemsRegistry.californium_nugget, ItemsRegistry.californium_nugget, ItemsRegistry.californium_nugget
		}).setRegistryName("californium_chunk"));
		ForgeRegistries.RECIPES.register(new ShapelessOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), new ItemStack(ItemsRegistry.californium_nugget,9), new Object[]{
				ItemsRegistry.californium
		}).setRegistryName("californium_nugget"));
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), ItemsRegistry.blazonium_fuel, new Object[]{
				"TTT",
				"UUU",
				"TTT",
				'T', new ItemStack(Item.getByNameOrId("ic2:nuclear"), 1, 2), 'U', ItemsRegistry.blazonium_nugget
		}).setRegistryName("blazonium_fuel"));
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), ItemsRegistry.blazonium_rod_dual, new Object[]{
				"TUT",
				'T', ItemsRegistry.blazonium_rod, 'U', new ItemStack(Item.getByNameOrId("ic2:plate"),1,3)
		}).setRegistryName("dual_blazonium"));
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), ItemsRegistry.blazonium_rod_quad, new Object[]{
				"TUT",
				"CUC",
				"TUT",
				'T', ItemsRegistry.blazonium_rod, 'U', new ItemStack(Item.getByNameOrId("ic2:plate"),1,3), 'C', new ItemStack(Item.getByNameOrId("ic2:plate"),1,1)
		}).setRegistryName("quad_blazonium"));
		ForgeRegistries.RECIPES.register(new ShapelessOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), ItemsRegistry.cfbe_pellet, new Object[]{
				ItemsRegistry.californium, ItemsRegistry.beryllium
		}).setRegistryName("cfbe_pellet"));
		ForgeRegistries.RECIPES.register(new ShapelessOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), ItemsRegistry.lawrencium, new Object[]{
				ItemsRegistry.small_lawrencium, ItemsRegistry.small_lawrencium, ItemsRegistry.small_lawrencium,
				ItemsRegistry.small_lawrencium, ItemsRegistry.small_lawrencium, ItemsRegistry.small_lawrencium,
				ItemsRegistry.small_lawrencium, ItemsRegistry.small_lawrencium, ItemsRegistry.small_lawrencium
		}).setRegistryName("lawrencium_chunk"));
		ForgeRegistries.RECIPES.register(new ShapelessOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), new ItemStack(ItemsRegistry.small_lawrencium,9), new Object[]{
				ItemsRegistry.lawrencium
		}).setRegistryName("lawrencium_nugget"));
		
		//tools
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), new ItemStack(ItemsRegistry.obsidian_stick, 2), new Object[]{
				"O",
				"O",
				'O', "obsidian"
		}).setRegistryName("obsidian_stick"));
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), ItemsRegistry.terminium_pick, new Object[]{
				"TTT",
				" S ",
				" S ",
				'T', "ingotTerminium", 'S', "stickObsidian"
		}).setRegistryName("terminium_pick"));
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), ItemsRegistry.terminium_shovel, new Object[]{
				"T",
				"S",
				"S",
				'T', "ingotTerminium", 'S', "stickObsidian"
		}).setRegistryName("terminium_shovel"));
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), ItemsRegistry.terminium_axe, new Object[]{
				"TT",
				"TS",
				" S",
				'T', "ingotTerminium", 'S', "stickObsidian"
		}).setMirrored(true).setRegistryName("terminium_axe"));
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), ItemsRegistry.terminium_sword, new Object[]{
				"T",
				"T",
				"S",
				'T', "ingotTerminium", 'S', "stickObsidian"
		}).setRegistryName("terminium_sword"));
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), ItemsRegistry.terminium_hoe, new Object[]{
				"TT",
				" S",
				" S",
				'T', "ingotTerminium", 'S', "stickObsidian"
		}).setMirrored(true).setRegistryName("terminium_hoe"));
		//blaze
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), ItemsRegistry.blazonium_pick, new Object[]{
				"BBB",
				" S ",
				" S ",
				'B', "gemBlazonium", 'S', "stickObsidian"
		}).setRegistryName("blazonium_pick"));
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), ItemsRegistry.blazonium_shovel, new Object[]{
				"B",
				"S",
				"S",
				'B', "gemBlazonium", 'S', "stickObsidian"
		}).setRegistryName("blazonium_shovel"));
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), ItemsRegistry.blazonium_axe, new Object[]{
				"BB",
				"BS",
				" S",
				'B', "gemBlazonium", 'S', "stickObsidian"
		}).setMirrored(true).setRegistryName("blazonium_axe"));
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), ItemsRegistry.blazonium_sword, new Object[]{
				"B",
				"B",
				"S",
				'B', "gemBlazonium", 'S', "stickObsidian"
		}).setRegistryName("blazonium_sword"));
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation(ReactorStuff.MODID, ReactorStuff.MODNAME), ItemsRegistry.blazonium_hoe, new Object[]{
				"BB",
				" S",
				" S",
				'B', "gemBlazonium", 'S', "stickObsidian"
		}).setMirrored(true).setRegistryName("blazonium_hoe"));
		
		
		
	}
}
