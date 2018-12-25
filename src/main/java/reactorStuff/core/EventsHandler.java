package reactorStuff.core;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryTable;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventsHandler {
	
	@SubscribeEvent
	public void addLoot(LootTableLoadEvent event){
		if(event.getName().toString().equals("minecraft:chests/desert_pyramid")){
			LootEntry entry=new LootEntryTable(new ResourceLocation("reactor_stuff:desert_elerium"), 1, 100, new LootCondition[0], "desert_elerium_entry");
			LootPool pool=new LootPool(new LootEntry[] {entry}, new LootCondition[0], new RandomValueRange(1F), new RandomValueRange(0F), "desert_elerium_pool");
			event.getTable().addPool(pool);
		}
		if(event.getName().toString().equals("minecraft:chests/jungle_temple")){
			LootEntry entry=new LootEntryTable(new ResourceLocation("reactor_stuff:jungle_elerium"), 1, 100, new LootCondition[0], "jungle_elerium_entry");
			LootPool pool=new LootPool(new LootEntry[] {entry}, new LootCondition[0], new RandomValueRange(1F), new RandomValueRange(0F), "jungle_elerium_pool");
			event.getTable().addPool(pool);
		}
	}
	
}
