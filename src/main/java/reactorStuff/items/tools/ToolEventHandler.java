package reactorStuff.items.tools;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import reactorStuff.core.MethodHelper;

public class ToolEventHandler {
	
	@SubscribeEvent(priority=EventPriority.LOWEST)
	public static void collectDrops(HarvestDropsEvent event){
		if(event.getHarvester()!=null){
			if(event.getHarvester().getHeldItemMainhand()!=null){
				if((event.getHarvester().getHeldItemMainhand().getItem()instanceof ItemTool&&
						((ItemTool)event.getHarvester().getHeldItemMainhand().getItem()).getToolMaterialName().equals("terminium"))||
						(event.getHarvester().getHeldItemMainhand().getItem()instanceof ItemSword&&
								((ItemSword)event.getHarvester().getHeldItemMainhand().getItem()).getToolMaterialName().equals("terminium"))||
						(event.getHarvester().getHeldItemMainhand().getItem()instanceof ItemModHoe&&
								((ItemModHoe)event.getHarvester().getHeldItemMainhand().getItem()).getToolMaterialName().equals("terminium"))){
					if(!event.getDrops().isEmpty()){
						for(ItemStack stack:event.getDrops()){
							if(event.getWorld().rand.nextFloat()<=event.getDropChance()){
								if(!event.getHarvester().addItemStackToInventory(stack)){
									Block.spawnAsEntity(event.getWorld(), event.getHarvester().getPosition(), stack);
								}
							}
						}
						event.getDrops().clear();
						event.getWorld().playSound(null, event.getPos(), SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.BLOCKS, 1F, 1F);
						spawnParticles((WorldServer) event.getWorld(), event.getPos().offset(EnumFacing.DOWN), event.getWorld().rand, EnumParticleTypes.PORTAL);
						spawnParticles((WorldServer) event.getWorld(), event.getPos().offset(EnumFacing.DOWN), event.getWorld().rand, EnumParticleTypes.PORTAL);
					}
				}
				if((event.getHarvester().getHeldItemMainhand().getItem()instanceof ItemTool&&
						((ItemTool)event.getHarvester().getHeldItemMainhand().getItem()).getToolMaterialName().equals("blazonium"))||
						(event.getHarvester().getHeldItemMainhand().getItem()instanceof ItemSword&&
								((ItemSword)event.getHarvester().getHeldItemMainhand().getItem()).getToolMaterialName().equals("blazonium"))||
						(event.getHarvester().getHeldItemMainhand().getItem()instanceof ItemModHoe&&
								((ItemModHoe)event.getHarvester().getHeldItemMainhand().getItem()).getToolMaterialName().equals("blazonium"))){
					if(!event.getDrops().isEmpty()){
						if(!event.getWorld().isRemote){
							float xp=0F;
							boolean ok=false;
							for(int i=0;i<event.getDrops().size();i++){
								ItemStack stack=event.getDrops().get(i);
								ItemStack smelt=FurnaceRecipes.instance().getSmeltingResult(stack).copy();
								if(!smelt.isEmpty()){
									ok=true;
									smelt.setCount(stack.getCount()*smelt.getCount());
									event.getDrops().set(i, smelt);
									xp+=FurnaceRecipes.instance().getSmeltingExperience(smelt)*smelt.getCount();
									
								}
							}
							int j=0;
							if(event.getWorld().rand.nextFloat()<(xp-(int)xp))
								j=(int)xp+1;
							else
								j=(int)xp;
							while(j>0){
								int k=EntityXPOrb.getXPSplit(j);
								j-=k;
								event.getWorld().spawnEntity(new EntityXPOrb(event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), k));
							}
							if(ok){
								event.getWorld().playSound(null, event.getPos(), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.BLOCKS, 1F, 1F);
								spawnParticles((WorldServer) event.getWorld(), event.getPos(), event.getWorld().rand, EnumParticleTypes.FLAME);
							}
						}
					}
				}
			}
		}
	}
	
	public static void spawnParticles(WorldServer world, BlockPos pos, Random itemRand, EnumParticleTypes type) {
		for (int i = 0; i < 5; ++i) {
			double rx = itemRand.nextGaussian() * 0.02D;
			double ry = itemRand.nextGaussian() * 0.02D;
			double rz = itemRand.nextGaussian() * 0.02D;
			final double magnitude = 20.0;
			world.spawnParticle(type, pos.getX() + 0.5 + (rx * magnitude), pos.getY() + 0.5 + (ry * magnitude), pos.getZ() + 0.5 + (rz * magnitude),
					5, 0, 0, 0, 0.02);
		}
	}
	
	@SubscribeEvent
	public static void teleportOnHit(LivingHurtEvent event){
		if(event.getSource().getImmediateSource() instanceof EntityPlayer){
			if(!event.getSource().isFireDamage()&&
					!event.getSource().isProjectile()&&
					!event.getSource().isMagicDamage()&&
					!event.getSource().isExplosion()){
				EntityPlayer player=((EntityPlayer)event.getSource().getImmediateSource());
				if(event.getEntityLiving().isEntityAlive()&&!player.world.isRemote&&getToolMaterial(player.getHeldItemMainhand().getItem(), "terminium")&&player.isSneaking()){			
					boolean ok=false;
					for(int i=0;i<32;i++){
						if(MethodHelper.teleportEntityLivingRandomly(event.getEntityLiving(), 32, 16)){
							ok=true;
							break;
						}
					}
					if(ok){
						player.world.playSound(null, player.getPosition(), SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.BLOCKS, 1F, 1F);
					}
				}
			}
		}
	}
	
	public static boolean getToolMaterial(Item item, String name){
		if((item instanceof ItemTool&&((ItemTool)item).getToolMaterialName().equals(name))||
				(item instanceof ItemSword&&((ItemSword)item).getToolMaterialName().equals(name))||
				(item instanceof ItemModHoe&&((ItemModHoe)item).getToolMaterialName().equals(name))){
			return true;
		}
		return false;
	}
	
	@SubscribeEvent(priority=EventPriority.LOWEST)
	public static void collectMobDrops(LivingDropsEvent event){
		if(event.getSource().getImmediateSource() instanceof EntityPlayer){
			if(!event.getSource().isFireDamage()&&
					!event.getSource().isProjectile()&&
					!event.getSource().isMagicDamage()&&
					!event.getSource().isExplosion()){
				EntityPlayer player=((EntityPlayer)event.getSource().getImmediateSource());
				if(getToolMaterial(player.getHeldItemMainhand().getItem(), "terminium")&&!event.getDrops().isEmpty()){
					for(EntityItem item:event.getDrops()){
						if(!player.addItemStackToInventory(item.getItem())){
							Block.spawnAsEntity(player.world, player.getPosition(), item.getItem());
						}
					}
					event.getDrops().clear();
					player.world.playSound(null, player.getPosition(), SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.BLOCKS, 1F, 1F);
					spawnParticles((WorldServer) player.world, event.getEntityLiving().getPosition(), player.world.rand, EnumParticleTypes.PORTAL);
					spawnParticles((WorldServer) player.world, event.getEntityLiving().getPosition(), player.world.rand, EnumParticleTypes.PORTAL);
				}
			}
		}
	}
	
}
