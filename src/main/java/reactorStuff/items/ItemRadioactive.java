package reactorStuff.items;

import ic2.core.IC2Potion;
import ic2.core.item.armor.ItemArmorHazmat;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemRadioactive extends ItemBase{
	
	public final int rads;
	public ItemRadioactive(String name,int rads) {
		super(name);
		this.rads=rads;
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (rads>0&&entityIn instanceof EntityLivingBase) {
            final EntityLivingBase entityLiving = (EntityLivingBase)entityIn;
            if (!ItemArmorHazmat.hasCompleteHazmat(entityLiving)) {
                IC2Potion.radiation.applyTo(entityLiving, rads, 100);
            }
        }
	}
	
}
