package reactorStuff.items;

import ic2.api.reactor.IReactor;
import ic2.api.reactor.IReactorComponent;
import ic2.core.IC2Potion;
import ic2.core.item.armor.ItemArmorHazmat;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemAmBeRod extends ItemBase implements IReactorComponent{

	public ItemAmBeRod(String name) {
		super(name);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (entityIn instanceof EntityLivingBase) {
            final EntityLivingBase entityLiving = (EntityLivingBase)entityIn;
            if (!ItemArmorHazmat.hasCompleteHazmat(entityLiving)) {
                IC2Potion.radiation.applyTo(entityLiving, 20*200, 100);
            }
        }
	}

	@Override
	public boolean canBePlacedIn(ItemStack stack, IReactor reactor) {
		return true;
	}

	@Override
	public void processChamber(ItemStack stack, IReactor reactor, int x, int y, boolean heatrun) {
		if(reactor.produceEnergy()&&!heatrun){
			checkPulseable(reactor, x-1, y, stack, x, y, heatrun);
			checkPulseable(reactor, x+1, y, stack, x, y, heatrun);
			checkPulseable(reactor, x, y-1, stack, x, y, heatrun);
			checkPulseable(reactor, x, y+1, stack, x, y, heatrun);
		}
	}
	
	protected static int checkPulseable(IReactor reactor, int x, int y, ItemStack stack, int mex, int mey, boolean heatrun) {
	      ItemStack other = reactor.getItemAt(x, y);
	      return other != null && other.getItem() instanceof IReactorComponent && ((IReactorComponent)other.getItem()).acceptUraniumPulse(other, reactor, stack, x, y, mex, mey, heatrun) ? 1 : 0;
	}

	@Override
	public boolean acceptUraniumPulse(ItemStack stack, IReactor reactor, ItemStack pulsingStack, int youX, int youY,
			int pulseX, int pulseY, boolean heatrun) {
		return heatrun;
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
		return -1F;
	}

}
