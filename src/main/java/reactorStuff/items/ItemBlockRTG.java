package reactorStuff.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import reactorStuff.tile.TileRTG.RTGType;

public class ItemBlockRTG extends ItemBlockBase{

	public ItemBlockRTG(Block block) {
		super(block);
		setHasSubtypes(true);
		setMaxDamage(0);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		if(stack.getMetadata()<RTGType.values().length){
			return getUnlocalizedName()+"_"+RTGType.values()[stack.getMetadata()].getName();
		}else
			return getUnlocalizedName()+"_"+RTGType.NONE.getName();
	}
	
	@Override
	public void initModel() {
		for(int i=0;i<RTGType.values().length;i++){
			ModelLoader.setCustomModelResourceLocation(this, i, new ModelResourceLocation(getRegistryName(), "inventory"));
		}
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		int p=RTGType.values()[stack.getMetadata()].output;
		if(p>0)
			tooltip.add("Power: "+p+"RF/t");
	}

}
