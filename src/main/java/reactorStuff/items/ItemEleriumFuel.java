package reactorStuff.items;

import ic2.api.reactor.IReactor;
import ic2.api.reactor.IReactorComponent;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import reactorStuff.core.MethodHelper;

public class ItemEleriumFuel extends ItemThoriumFuelRod{

	public ItemEleriumFuel(String name, int cells, int cycles) {
		super(name, cells, cycles);
	}
	
	@Override
	public boolean acceptUraniumPulse(ItemStack stack, IReactor reactor, ItemStack pulsingStack, int youX, int youY,
			int pulseX, int pulseY, boolean heatrun) {
		if (!heatrun) {
            reactor.addOutput(2f);
            stack.getTagCompound().setInteger("neutronPulses", stack.getTagCompound().getInteger("neutronPulses")+1);
        }
        return false;
	}
	
	@Override
	protected int getFinalHeat(ItemStack stack, IReactor reactor, int x, int y, int heat) {
		return heat*2;
	}
	
	@Override
	public void processChamber(ItemStack stack, IReactor reactor, int x, int y, boolean heatRun) {
		if(reactor.produceEnergy()&&heatRun){
			int pulses=stack.getTagCompound().getInteger("neutronPulses");
			int heat=triangularNumber(pulses);
			heat=getFinalHeat(stack, reactor, x, y, heat);
			MethodHelper.disperseHeat(reactor, x, y, heat);
			if(stack.getTagCompound().getInteger("neutronPulses")>0){
				if(this.getCustomDamage(stack) >= this.getMaxCustomDamage(stack) - 1){
					reactor.setItemAt(x, y, this.getDepletedStack(stack, reactor));
				}else{
					this.applyCustomDamage(stack, 1, null);
				}
			}
			stack.getTagCompound().setInteger("neutronPulses", 0);
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
	public void onUpdate(ItemStack arg0, World arg1, Entity arg2, int arg3, boolean arg4) {
		//no radiation;
	}
	
}
