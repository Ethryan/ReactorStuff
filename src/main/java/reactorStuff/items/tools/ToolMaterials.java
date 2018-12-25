package reactorStuff.items.tools;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ToolMaterials {
	
	public static ToolMaterial TERMINIUM_TOOL=EnumHelper.addToolMaterial("terminium", 3, 750, 9F, 4F, 20);
	public static ToolMaterial BLAZONIUM_TOOL=EnumHelper.addToolMaterial("blazonium", 3, 1024, 7F, 2.5F, 10);
	public static ArmorMaterial TERMINIUM_ARMOR=EnumHelper.addArmorMaterial("terminium", "", 20, new int[]{2,5,7,2}, 20, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1F);
	
}
