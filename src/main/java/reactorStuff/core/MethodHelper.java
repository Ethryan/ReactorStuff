package reactorStuff.core;

import java.util.Random;

import ic2.api.reactor.IReactor;
import ic2.api.reactor.IReactorComponent;
import ic2.core.item.reactor.ItemReactorMOX;
import ic2.core.item.reactor.ItemReactorUranium;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import reactorStuff.items.ItemBreederUranium;
import reactorStuff.items.ItemEleriumFuel;
import reactorStuff.items.ItemTerminiumFuelRod;
import reactorStuff.items.ItemThoriumFuelRod;
import reactorStuff.items.ItemTrigaFuel;

public class MethodHelper {
	
	public static Random rand=new Random();
	public static boolean teleportEntityLivingRandomly(EntityLivingBase entity,double range,double rangeV){
		double x = entity.posX + (rand.nextDouble() - 0.5D) * range;
        double y = entity.posY + (rand.nextDouble() - 0.5D) * rangeV;
        double z = entity.posZ + (rand.nextDouble() - 0.5D) * range;
        EnderTeleportEvent event=new EnderTeleportEvent(entity, x, y, z, 0);
        if(!MinecraftForge.EVENT_BUS.post(event)){
        	boolean didTeleport=entity.attemptTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ());
        	if(didTeleport){
        		entity.world.playSound(null, entity.prevPosX, entity.prevPosY, entity.prevPosZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT, entity.getSoundCategory(), 1.0F, 1.0F);
        		entity.world.playSound(null, entity.posX, entity.posY, entity.posZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT, entity.getSoundCategory(), 1.0F, 1.0F);
                return true;
        	}
        	return false;
        }
        return false;
	}
	
	public static int getHeatForCell(ItemReactorUranium item, int heat, IReactor reactor){
		if(item.getClass().equals(ItemReactorUranium.class)){
			return heat*4;
		}
		if(item.getClass().equals(ItemReactorMOX.class)){
			if(reactor.isFluidCooled()&&reactor.getHeat()>=reactor.getMaxHeat()/2)
				return heat*8;
			return heat*4;
		}
		if(item.getClass().equals(ItemThoriumFuelRod.class)){
			return heat*2;
		}
		if(item.getClass().equals(ItemEleriumFuel.class)){
			return heat*2;
		}
		if(item.getClass().equals(ItemTrigaFuel.class)){
			float reactorHeat=(float)reactor.getHeat()/(float)reactor.getMaxHeat();
			if(reactorHeat<0.2)
				return 4*heat;
			if(reactorHeat<0.4)
				return 3*heat;
			if(reactorHeat<0.6)
				return 2*heat;
			if(reactorHeat<0.8)
				return heat;
			return 0;
		}
		if(item.getClass().equals(ItemBreederUranium.class)){
			return 0;
		}
		if(item.getClass().equals(ItemTerminiumFuelRod.class)){
			return heat*4;
		}
		return heat*4;
	}
	
	public static void disperseHeat(IReactor reactor, int x, int y, int heat){
		boolean canDisperseHeat=true;
			while(canDisperseHeat&&heat>0){
				int nrcomp=0;
				nrcomp=checkHeatAcceptor(reactor, x-1, y)+checkHeatAcceptor(reactor, x+1, y)+checkHeatAcceptor(reactor, x, y-1)+checkHeatAcceptor(reactor, x, y+1);
				canDisperseHeat=false;
				if(nrcomp>0){
					int heatPart=heat/nrcomp+(heat%nrcomp>0?1:0);
					for(int i=1;i<=4;i++){
						switch(i){
						case 1:{
							if(checkHeatAcceptor(reactor, x-1, y)==1){
								int dheat=Math.min(heatPart, heat);
								heat-=dheat;
								int vheat=((IReactorComponent)reactor.getItemAt(x-1, y).getItem()).alterHeat(reactor.getItemAt(x-1, y), reactor, x-1, y, dheat);
								heat+=vheat;
								if(vheat<dheat)
									canDisperseHeat=true;
							}
						}
						case 2:{
							if(checkHeatAcceptor(reactor, x+1, y)==1){
								int dheat=Math.min(heatPart, heat);
								heat-=dheat;
								int vheat=((IReactorComponent)reactor.getItemAt(x+1, y).getItem()).alterHeat(reactor.getItemAt(x+1, y), reactor, x+1, y, dheat);
								heat+=vheat;
								if(vheat<dheat)
									canDisperseHeat=true;
							}
						}
						case 3:{
							if(checkHeatAcceptor(reactor, x, y-1)==1){
								int dheat=Math.min(heatPart, heat);
								heat-=dheat;
								int vheat=((IReactorComponent)reactor.getItemAt(x, y-1).getItem()).alterHeat(reactor.getItemAt(x, y-1), reactor, x, y-1, dheat);
								heat+=vheat;
								if(vheat<dheat)
									canDisperseHeat=true;
							}
						}
						case 4:{
							if(checkHeatAcceptor(reactor, x, y+1)==1){
								int dheat=Math.min(heatPart, heat);
								heat-=dheat;
								int vheat=((IReactorComponent)reactor.getItemAt(x, y+1).getItem()).alterHeat(reactor.getItemAt(x, y+1), reactor, x, y+1, dheat);
								heat+=vheat;
								if(vheat<dheat)
									canDisperseHeat=true;
							}
						}
						}
					}
				}
			}
			if(heat>0){
				reactor.addHeat(heat);
			}
	}
	
	public static int checkHeatAcceptor(IReactor reactor, int x, int y){
		ItemStack comp=reactor.getItemAt(x, y);
		if(comp!=null&&comp.getItem() instanceof IReactorComponent&&((IReactorComponent)comp.getItem()).canStoreHeat(comp, reactor, x, y)){
			return 1;
		}else
			return 0;
	}
	
}
