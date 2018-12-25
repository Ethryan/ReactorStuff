package reactorStuff.items;

import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemEleriumCrystal extends ItemBase{

	public ItemEleriumCrystal(String name) {
		super(name);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(ChatFormatting.OBFUSCATED+"Property of Tacoredneck");
		tooltip.add(ChatFormatting.OBFUSCATED+"Unauthorized Removal is Unauthorized");
	}

}
