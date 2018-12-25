package reactorStuff.items;

import java.util.List;

import com.google.common.collect.Iterables;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.energy.IEnergyStorage;

public class ItemEleriumCore extends ItemBase{

	public static final int POWER=80;
	public static final int CAPACITY=80;
	public static final int DURABILITY=4000000;
	@CapabilityInject(IEnergyStorage.class)
	public static Capability<IEnergyStorage> ENERGY=null;
	
	public ItemEleriumCore() {
		super("core_elerium");
		setHasSubtypes(true);
		setMaxDamage(101);
		setNoRepair();
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.REDSTONE);
	}
	
	@Override
	public boolean hasEffect(ItemStack stack) {
		if(stack.hasTagCompound()&&
				stack.getTagCompound().getBoolean("active")==true)
			return true;
		else
			return false;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if(playerIn.isSneaking()){
			ItemStack stack=playerIn.getHeldItem(handIn);
			if(!stack.hasTagCompound()){
				stack.setTagCompound(new NBTTagCompound());
			}
			stack.getTagCompound().setBoolean("active", !stack.getTagCompound().getBoolean("active"));
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if(worldIn.isRemote)
			return;
		if(!stack.hasTagCompound()){
			stack.setTagCompound(new NBTTagCompound());
		}
		if(stack.getTagCompound().getInteger("durability")>0&&
				stack.getTagCompound().getInteger("buffer")<CAPACITY){
			stack.getTagCompound().setInteger("buffer", stack.getTagCompound().getInteger("buffer")+POWER);
			stack.getTagCompound().setInteger("durability", stack.getTagCompound().getInteger("durability")-1);
			stack.setItemDamage((int)(100*(1F-stack.getTagCompound().getInteger("durability")/(float)DURABILITY)));
		}
		if(stack.getTagCompound().getBoolean("active")==true){
			Iterable<ItemStack> equipment=Iterables.concat(((EntityPlayer)entityIn).inventory.mainInventory,((EntityPlayer)entityIn).inventory.armorInventory,((EntityPlayer)entityIn).inventory.offHandInventory);
			for(ItemStack item:equipment){
				if(item.equals(stack))
					continue;
				if(item.hasCapability(ENERGY, null)){
					IEnergyStorage cap=item.getCapability(ENERGY, null);
					int e=stack.getTagCompound().getInteger("buffer");
					int a=cap.receiveEnergy(Math.min(80, e), false);
					e-=a;
					stack.getTagCompound().setInteger("buffer", e);
				}
			}
		}
	}
	
	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return !(oldStack.getItem()==newStack.getItem());
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if(!stack.hasTagCompound()){
			stack.setTagCompound(new NBTTagCompound());
		}
		tooltip.add("Elerium Fuel Left: "+stack.getTagCompound().getInteger("durability")/20+"/"+DURABILITY/20);
	}
	
}
