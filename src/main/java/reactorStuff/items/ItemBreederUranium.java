package reactorStuff.items;

import ic2.api.reactor.IReactor;
import net.minecraft.item.ItemStack;

public class ItemBreederUranium extends ItemThoriumFuelRod{

	public ItemBreederUranium(String name, int cells, int cycles) {
		super(name, cells, cycles);
	}
	
	@Override
	public void processChamber(ItemStack arg0, IReactor arg1, int arg2, int arg3, boolean arg4) {
		
	}
	
	@Override
	public boolean acceptUraniumPulse(ItemStack stack, IReactor reactor, ItemStack pulsingStack, int youX, int youY,
			int pulseX, int pulseY, boolean heatrun) {
		if (!heatrun) {
	         int myLevel = this.getCustomDamage(stack) + (reactor.getHeat()>reactor.getMaxHeat()/2?2:1);
	         if (myLevel >= this.getMaxCustomDamage(stack)) {
	            reactor.setItemAt(youX, youY, getDepletedStack(stack, reactor));
	         } else {
	            this.setCustomDamage(stack, myLevel);
	         }
	      }

	      return true;
	}
	
	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		return 1.0D - super.getDurabilityForDisplay(stack);
	}
	
	@Override
	protected ItemStack getDepletedStack(ItemStack arg0, IReactor arg1) {
		switch (this.numberOfCells) {
        case 1: {
            return new ItemStack(ItemsRegistry.bred_uranium);
        }
        case 2: {
            return new ItemStack(ItemsRegistry.bred_uranium_dual);
        }
        case 4: {
            return new ItemStack(ItemsRegistry.bred_uranium_quad);
        }
        default: {
            throw new RuntimeException("invalid cell count: " + this.numberOfCells);
        }
    }
	}

}
