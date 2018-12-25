package reactorStuff.items.tools;

import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPick extends ItemPickaxe{

	public ItemPick(String name, ToolMaterial material) {
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
	public float getDestroySpeed(ItemStack stack, IBlockState state) {
		if(this.toolMaterial==ToolMaterials.TERMINIUM_TOOL&&state.getBlock()==Blocks.OBSIDIAN){
			return this.efficiency*5F;
		}
		return super.getDestroySpeed(stack, state);
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
			tooltip.add(ChatFormatting.DARK_PURPLE+"Obsidian Crusher");
			tooltip.add(ChatFormatting.DARK_PURPLE+"Ender Collector");
			tooltip.add(ChatFormatting.DARK_PURPLE+"Rifting Edge");
		}
		if(getToolMaterialName().equals("blazonium")){
			tooltip.add(ChatFormatting.DARK_RED+"Smelting Heat");
			tooltip.add(ChatFormatting.DARK_RED+"Fire Aspect III");
		}
	}

}
