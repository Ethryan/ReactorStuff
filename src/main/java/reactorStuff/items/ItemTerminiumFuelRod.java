package reactorStuff.items;

import org.apache.commons.lang3.ArrayUtils;

import ic2.api.reactor.IReactor;
import ic2.api.reactor.IReactorComponent;
import ic2.core.item.reactor.ItemReactorUranium;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import reactorStuff.core.MethodHelper;

public class ItemTerminiumFuelRod extends ItemThoriumFuelRod{

	public ItemTerminiumFuelRod(String name, int cells, int cycles) {
		super(name, cells, cycles);
	}
	
	@Override
	protected int getFinalHeat(ItemStack stack, IReactor reactor, int x, int y, int heat) {
		return heat;
	}
	
	@Override
	public boolean acceptUraniumPulse(ItemStack stack, IReactor reactor, ItemStack pulsingStack, int youX, int youY,
			int pulseX, int pulseY, boolean heatrun) {
		if (!heatrun) {
            reactor.addOutput(2F);
            stack.getTagCompound().setInteger("neutronPulses", stack.getTagCompound().getInteger("neutronPulses")+1);
            return true;
        }
        return false;
	}
	
	@Override
	public void processChamber(ItemStack stack, IReactor reactor, int x, int y, boolean heatRun) {
		if (reactor.produceEnergy()) {
	         int basePulses = 1 + this.numberOfCells / 2;

	         for(int iteration = 0; iteration < this.numberOfCells; ++iteration) {
	            int pulses = basePulses;
	            int heat;
	            if (!heatRun) {
	               for(heat = 0; heat < pulses; ++heat) {
	            	   reactor.addOutput(2F);
	               }
	               randomlyPulseComponent(reactor, stack, x-1, y, x, y, heatRun);
	               randomlyPulseComponent(reactor, stack, x+1, y, x, y, heatRun);
	               randomlyPulseComponent(reactor, stack, x, y-1, x, y, heatRun);
	               randomlyPulseComponent(reactor, stack, x, y+1, x, y, heatRun);
	            } else {
	               pulses=basePulses+stack.getTagCompound().getInteger("neutronPulses");
	               heat = triangularNumber(pulses) * 4;
	               heat = this.getFinalHeat(stack, reactor, x, y, heat);
	               MethodHelper.disperseHeat(reactor, x, y, heat);
	            }
	         }
	         
	         if(heatRun){
				if(this.getCustomDamage(stack) >= this.getMaxCustomDamage(stack) - 1){
					reactor.setItemAt(x, y, this.getDepletedStack(stack, reactor));
				}else{
					this.applyCustomDamage(stack, 1, null);
				}
				stack.getTagCompound().setInteger("neutronPulses", 0);
	        	 
	        	 int[] slots=stack.getTagCompound().getIntArray("pulsedComponents");
	        	 if(slots.length>0){
	        		 for(int i=0;i<slots.length;i+=2){
	        			 ItemStack other=reactor.getItemAt(slots[i], slots[i+1]);
	        			 if(other!=null&&other.getItem() instanceof ItemReactorUranium){
	        				 int pulses=other.getTagCompound().getInteger("neutronPulses");
	        				 if(pulses>0){
	        					 ItemReactorUranium item=(ItemReactorUranium)other.getItem();
	        					 int cells=1+item.numberOfCells/2;
	        					 int cellPulses=cells +
	        							 checkPulseable(reactor, slots[i] - 1, slots[i+1], stack, slots[i], slots[i+1], heatRun) + 
	        							 checkPulseable(reactor, slots[i] + 1, slots[i+1], stack, slots[i], slots[i+1], heatRun) + 
	        							 checkPulseable(reactor, slots[i], slots[i+1] - 1, stack, slots[i], slots[i+1], heatRun) + 
	        							 checkPulseable(reactor, slots[i], slots[i+1] + 1, stack, slots[i], slots[i+1], heatRun);
	        					 int newHeat=triangularNumber(cellPulses+pulses)-triangularNumber(cellPulses);
	        					 newHeat=MethodHelper.getHeatForCell(item, newHeat, reactor);
	        					 other.getTagCompound().setInteger("neutronPulses", 0);
	        					 MethodHelper.disperseHeat(reactor, slots[i], slots[i+1], newHeat);
	        				 }
	        			 }
	        		 }
	        	 }
	        	 stack.getTagCompound().setIntArray("pulsedComponents", new int[]{});
	         }

	      }
	}
	
	public void randomlyPulseComponent(IReactor reactor, ItemStack pulsingStack, int youX, int youY,
			int pulseX, int pulseY, boolean heatrun){
		for(int tries=0;tries<16;tries++){
			int x=Item.itemRand.nextInt(9);
			int y=Item.itemRand.nextInt(6);
			if(x==pulseX&&y==pulseY)
				continue;
			ItemStack other=reactor.getItemAt(x, y);
			if(checkPulseable(reactor, x, y, pulsingStack, pulseX, pulseY, heatrun)>0){
				if(other.getItem() instanceof ItemReactorUranium){
					other.getTagCompound().setInteger("neutronPulses", other.getTagCompound().getInteger("neutronPulses")+1);
					int[] a=pulsingStack.getTagCompound().getIntArray("pulsedComponents");
					int[] b=ArrayUtils.addAll(a, x, y);
					pulsingStack.getTagCompound().setIntArray("pulsedComponents", b);
				}
				return;
			}
		}
		ItemStack other=reactor.getItemAt(youX, youY);
		if(checkPulseable(reactor, youX, youY, pulsingStack, pulseX, pulseY, heatrun)>0&&other.getItem() instanceof ItemReactorUranium){
			other.getTagCompound().setInteger("neutronPulses", other.getTagCompound().getInteger("neutronPulses")+1);
			int[] a=pulsingStack.getTagCompound().getIntArray("pulsedComponents");
			int[] b=ArrayUtils.addAll(a, youX, youY);
			pulsingStack.getTagCompound().setIntArray("pulsedComponents", b);
			return;
		}
	}
	
	public int checkHeatAcceptor(IReactor reactor, int x, int y){
		ItemStack comp=reactor.getItemAt(x, y);
		if(comp!=null&&comp.getItem() instanceof IReactorComponent&&((IReactorComponent)comp.getItem()).canStoreHeat(comp, reactor, x, y)){
			return 1;
		}else
			return 0;
	}
	
	@Override
	protected ItemStack getDepletedStack(ItemStack arg0, IReactor arg1) {
        switch (this.numberOfCells) {
            case 1: {
                return new ItemStack(ItemsRegistry.depleted_terminium_rod);
            }
            case 2: {
                return new ItemStack(ItemsRegistry.depleted_terminium_rod_dual);
            }
            case 4: {
                return new ItemStack(ItemsRegistry.depleted_terminium_rod_quad);
            }
            default: {
                throw new RuntimeException("invalid cell count: " + this.numberOfCells);
            }
        }
	}

}
