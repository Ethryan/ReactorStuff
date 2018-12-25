package reactorStuff.items;

import org.apache.commons.lang3.ArrayUtils;

import ic2.api.reactor.IReactor;
import ic2.core.item.reactor.ItemReactorUranium;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import reactorStuff.core.MethodHelper;

public class ItemBlazoniumFuelRod extends ItemTerminiumFuelRod{

	public ItemBlazoniumFuelRod(String name, int cells, int cycles) {
		super(name, cells, cycles);
	}
	
	@Override
	protected int getFinalHeat(ItemStack stack, IReactor reactor, int x, int y, int heat) {
		return heat*16;
	}
	
	@Override
	public boolean acceptUraniumPulse(ItemStack stack, IReactor reactor, ItemStack pulsingStack, int youX, int youY,
			int pulseX, int pulseY, boolean heatrun) {
		if (!heatrun) {
            reactor.addOutput(1F);
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
	            	   reactor.addOutput(1F);
	               }
	               pulseComponentRow(reactor, stack, x-1, y, x, y, heatRun, -1, 0);
	               pulseComponentRow(reactor, stack, x+1, y, x, y, heatRun, +1, 0);
	               pulseComponentRow(reactor, stack, x, y-1, x, y, heatRun, 0, -1);
	               pulseComponentRow(reactor, stack, x, y+1, x, y, heatRun, 0, +1);
	            } else {
	               pulses=basePulses+stack.getTagCompound().getInteger("neutronPulses");
	               heat = triangularNumber(pulses) * 4;
	               heat = this.getFinalHeat(stack, reactor, x, y, heat);
	               MethodHelper.disperseHeat(reactor, x, y, heat);
	            }
	         }
	         
	         int neutrons=stack.getTagCompound().getInteger("neutronPulses");
	         float rh=(float)reactor.getHeat()/(float)reactor.getMaxHeat();
	         for(int n=0;n<neutrons;n++){
	        	 if(Item.itemRand.nextFloat()<rh){
	        		 pulseComponentRow(reactor, stack, x-1, y, x, y, heatRun, -1, 0);
		             pulseComponentRow(reactor, stack, x+1, y, x, y, heatRun, +1, 0);
		             pulseComponentRow(reactor, stack, x, y-1, x, y, heatRun, 0, -1);
		             pulseComponentRow(reactor, stack, x, y+1, x, y, heatRun, 0, +1);
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
	
	public void pulseComponentRow(IReactor reactor, ItemStack pulsingStack, int youX, int youY,
			int pulseX, int pulseY, boolean heatrun, int addX, int addY){
		for(int tries=0;tries<8;tries++){
			ItemStack other=reactor.getItemAt(youX, youY);
			if(checkPulseable(reactor, youX, youY, pulsingStack, pulseX, pulseY, heatrun)>0){
				if(other.getItem() instanceof ItemReactorUranium){
					other.getTagCompound().setInteger("neutronPulses", other.getTagCompound().getInteger("neutronPulses")+1);
					int[] a=pulsingStack.getTagCompound().getIntArray("pulsedComponents");
					int[] b=ArrayUtils.addAll(a, youX, youY);
					pulsingStack.getTagCompound().setIntArray("pulsedComponents", b);
				}
			}
			youX+=addX;
			youY+=addY;
		}
	}
	
	@Override
	protected ItemStack getDepletedStack(ItemStack arg0, IReactor arg1) {
		switch (this.numberOfCells) {
        case 1: {
            return new ItemStack(ItemsRegistry.depleted_blazonium_rod);
        }
        case 2: {
            return new ItemStack(ItemsRegistry.depleted_blazonium_rod_dual);
        }
        case 4: {
            return new ItemStack(ItemsRegistry.depleted_blazonium_rod_quad);
        }
        default: {
            throw new RuntimeException("invalid cell count: " + this.numberOfCells);
        }
    }
	}

}
