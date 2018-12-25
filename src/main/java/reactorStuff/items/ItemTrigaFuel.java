package reactorStuff.items;

import ic2.api.reactor.IReactor;
import net.minecraft.item.ItemStack;

public class ItemTrigaFuel extends ItemThoriumFuelRod{

	public ItemTrigaFuel(String name, int cells, int cycles) {
		super(name, cells, cycles);
	}
	
	@Override
	protected int getFinalHeat(ItemStack stack, IReactor reactor, int x, int y, int heat) {
		float reactorHeat=(float)reactor.getHeat()/(float)reactor.getMaxHeat();
		if(reactorHeat<0.2)
			return heat;
		if(reactorHeat<0.4)
			return 3*heat/4;
		if(reactorHeat<0.6)
			return heat/2;
		if(reactorHeat<0.8)
			return heat/4;
		return 0;
	}
	
	@Override
	public boolean acceptUraniumPulse(ItemStack stack, IReactor reactor, ItemStack pulsingStack, int youX, int youY,
			int pulseX, int pulseY, boolean heatrun) {
		float heat=(float)reactor.getHeat()/(float)reactor.getMaxHeat();
		if (!heatrun) {
            if(heat<0.2){
            	reactor.addOutput(1F);
            	return true;
            }
            if(heat<0.4){
            	reactor.addOutput(0.75F);
            	return true;
            }
            if(heat<0.6){
            	reactor.addOutput(0.5F);
            	return true;
            }
            if(heat<0.8){
            	reactor.addOutput(0.25F);
            	return true;
            }
            return false;
        }
        return heat<0.8;
	}
	
	@Override
	public void processChamber(ItemStack stack, IReactor reactor, int x, int y, boolean heatRun) {
		float heat=(float)reactor.getHeat()/(float)reactor.getMaxHeat();
		if(!heatRun&&reactor.produceEnergy()){
			if(heat<0.2)
				this.applyCustomDamage(stack, 3, null);
			else if(heat<0.4)
				this.applyCustomDamage(stack, 2, null);
			else if(heat<0.6)
				this.applyCustomDamage(stack, 1, null);
		}
		if(heat<0.8)
			super.processChamber(stack, reactor, x, y, heatRun);
	}
	
	@Override
	protected ItemStack getDepletedStack(ItemStack arg0, IReactor arg1) {
		switch (this.numberOfCells) {
        case 1: {
            return new ItemStack(ItemsRegistry.depleted_triga);
        }
        case 2: {
            return new ItemStack(ItemsRegistry.depleted_triga_dual);
        }
        case 4: {
            return new ItemStack(ItemsRegistry.depleted_triga_quad);
        }
        default: {
            throw new RuntimeException("invalid cell count: " + this.numberOfCells);
        }
    }
	}

}
