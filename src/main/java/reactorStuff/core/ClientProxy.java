package reactorStuff.core;

import java.util.function.Function;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import reactorStuff.ReactorStuff;
import reactorStuff.render.GlowOreBakedModel;

public class ClientProxy extends CommonProxy {
	
	
	public void preInit(FMLPreInitializationEvent e){
		super.preInit(e);
		MinecraftForge.EVENT_BUS.register(this);
	}
	public void init(FMLInitializationEvent e){
		super.init(e);
	}
	public void postInit(FMLPostInitializationEvent e){
		super.postInit(e);
	}
	
	@SubscribeEvent
	public void onTextureStitch(TextureStitchEvent.Pre event){
		event.getMap().registerSprite(new ResourceLocation(ReactorStuff.MODID, "blocks/uranium_bits"));
		event.getMap().registerSprite(new ResourceLocation(ReactorStuff.MODID, "blocks/thorium_bits"));
		event.getMap().registerSprite(new ResourceLocation(ReactorStuff.MODID, "blocks/elerium_bits"));
		event.getMap().registerSprite(new ResourceLocation(ReactorStuff.MODID, "blocks/terminium_bits"));
		event.getMap().registerSprite(new ResourceLocation(ReactorStuff.MODID, "blocks/blazonium_bits"));
	}
	
	@SubscribeEvent
	public void onModelBake(ModelBakeEvent event){
		if(!Config.optifine_compat){
			// bake and register our models here
	        Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter = ModelLoader.defaultTextureGetter();
	
	        // get all of the sprites that we will use
	        TextureAtlasSprite stone = bakedTextureGetter.apply(new ResourceLocation("minecraft:blocks/stone"));
	        TextureAtlasSprite uranium = bakedTextureGetter.apply(new ResourceLocation(ReactorStuff.MODID, "blocks/uranium_bits"));
	        TextureAtlasSprite thorium = bakedTextureGetter.apply(new ResourceLocation(ReactorStuff.MODID, "blocks/thorium_bits"));
	        TextureAtlasSprite elerium_base = bakedTextureGetter.apply(new ResourceLocation(Config.elerium_base));
	        TextureAtlasSprite elerium = bakedTextureGetter.apply(new ResourceLocation(ReactorStuff.MODID, "blocks/elerium_bits"));
	        TextureAtlasSprite end_stone=bakedTextureGetter.apply(new ResourceLocation("minecraft:blocks/end_stone"));
	        TextureAtlasSprite netherrack=bakedTextureGetter.apply(new ResourceLocation("minecraft:blocks/netherrack"));
	        TextureAtlasSprite terminium=bakedTextureGetter.apply(new ResourceLocation(ReactorStuff.MODID, "blocks/terminium_bits"));
	        TextureAtlasSprite blazonium=bakedTextureGetter.apply(new ResourceLocation(ReactorStuff.MODID, "blocks/blazonium_bits"));
	        
	
	        // Add all of the models
	        event.getModelRegistry().putObject(new ModelResourceLocation(ReactorStuff.MODID+":glow_ore_uranium"),
	                                           	new GlowOreBakedModel(stone, uranium));
	        event.getModelRegistry().putObject(new ModelResourceLocation(ReactorStuff.MODID+":glow_ore_thorium"),
	        									new GlowOreBakedModel(stone, thorium));
	        event.getModelRegistry().putObject(new ModelResourceLocation(ReactorStuff.MODID+":glow_ore_elerium"),
	        									new GlowOreBakedModel(elerium_base, elerium));
	        event.getModelRegistry().putObject(new ModelResourceLocation(ReactorStuff.MODID+":glow_ore_terminium"),
	        									new GlowOreBakedModel(end_stone, terminium));
	        event.getModelRegistry().putObject(new ModelResourceLocation(ReactorStuff.MODID+":glow_ore_blazonium"),
	        									new GlowOreBakedModel(netherrack, blazonium));
		}
	}
	
}
