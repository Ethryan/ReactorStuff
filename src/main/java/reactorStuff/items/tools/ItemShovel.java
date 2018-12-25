package reactorStuff.items.tools;

import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemShovel extends ItemSpade{

	public ItemShovel(ToolMaterial material, String name) {
		super(material);
		setRegistryName(name);
		setUnlocalizedName(getRegistryName().toString());
		setCreativeTab(CreativeTabs.TOOLS);
	}
	
	@SideOnly(Side.CLIENT)
	public void initModel(){
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if(this.toolMaterial==ToolMaterials.BLAZONIUM_TOOL)
			target.setFire(15);
		return super.hitEntity(stack, target, attacker);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if(getToolMaterialName().equals("terminium")){
			tooltip.add(ChatFormatting.DARK_PURPLE+"Ender Collector");
			tooltip.add(ChatFormatting.DARK_PURPLE+"Rifting Edge");
		}
		if(getToolMaterialName().equals("blazonium")){
			tooltip.add(ChatFormatting.DARK_RED+"Smelting Heat");
			tooltip.add(ChatFormatting.DARK_RED+"Fire Aspect III");
		}
	}

}
