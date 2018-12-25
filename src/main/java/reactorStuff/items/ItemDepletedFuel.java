package reactorStuff.items;

import ic2.api.reactor.IReactor;
import ic2.api.reactor.IReactorComponent;
import net.minecraft.item.ItemStack;

public class ItemDepletedFuel extends ItemRadioactive implements IReactorComponent{

	public ItemDepletedFuel(String name, int rads) {
		super(name, rads);
	}

	@Override
	public boolean canBePlacedIn(ItemStack stack, IReactor reactor) {
		return false;
	}

	@Override
	public void processChamber(ItemStack stack, IReactor reactor, int x, int y, boolean heatrun) {
		
	}

	@Override
	public boolean acceptUraniumPulse(ItemStack stack, IReactor reactor, ItemStack pulsingStack, int youX, int youY,
			int pulseX, int pulseY, boolean heatrun) {
		return false;
	}

	@Override
	public boolean canStoreHeat(ItemStack stack, IReactor reactor, int x, int y) {
		return false;
	}

	@Override
	public int getMaxHeat(ItemStack stack, IReactor reactor, int x, int y) {
		return 0;
	}

	@Override
	public int getCurrentHeat(ItemStack stack, IReactor reactor, int x, int y) {
		return 0;
	}

	@Override
	public int alterHeat(ItemStack stack, IReactor reactor, int x, int y, int heat) {
		return 0;
	}

	@Override
	public float influenceExplosion(ItemStack stack, IReactor reactor) {
		return 0;
	}

}
