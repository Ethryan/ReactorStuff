package reactorStuff.items;

import ic2.api.reactor.IReactor;
import ic2.api.reactor.IReactorComponent;
import ic2.core.item.reactor.ItemReactorUranium;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import reactorStuff.ReactorStuff;

public class ItemThoriumFuelRod extends ItemReactorUranium{

	public ItemThoriumFuelRod(String name, int cells, int cycles) {
		super(null, cells, cycles);
		setRegistryName(ReactorStuff.MODID,name);
		setUnlocalizedName(getRegistryName().toString());
		setCreativeTab(CreativeTabs.MATERIALS);
	}
	
	@SideOnly(Side.CLIENT)
	public void initModel(){
		ModelResourceLocation model=new ModelResourceLocation(getRegistryName(), "inventory");
		ModelBakery.registerItemVariants(this, model);
		ModelLoader.setCustomMeshDefinition(this, new ItemMeshDefinition() {
			
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return model;
			}
		});
		//ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
	
	@Override
	public String getUnlocalizedName() {
		return "item."+getRegistryName().toString()+".name";
	}
	
	@Override
	protected int getFinalHeat(ItemStack stack, IReactor reactor, int x, int y, int heat) {
		return heat/2;
	}
	
	@Override
	public boolean acceptUraniumPulse(ItemStack stack, IReactor reactor, ItemStack pulsingStack, int youX, int youY,
			int pulseX, int pulseY, boolean heatrun) {
		if (!heatrun) {
            reactor.addOutput(0.5f);
        }
        return true;
	}
	
	public int pulseComponent(IReactor reactor, int x, int y, ItemStack stack, int mex, int mey, boolean heatrun){
		ItemStack other = reactor.getItemAt(x, y);
		if(other!=null&&other.getItem() instanceof IReactorComponent){
			 boolean i=((IReactorComponent)other.getItem()).acceptUraniumPulse(other, reactor, stack, x, y, mex, mey, heatrun);
			 if(i){
				 if(other.getItem() instanceof ItemReactorUranium){
					 other.getTagCompound().setInteger("neutronPulses", other.getTagCompound().getInteger("neutronPulses")+1);
				 }
				 return 1;
			 }else
				 return 0;
		}else
			return 0;
	}
	
	@Override
	protected ItemStack getDepletedStack(ItemStack arg0, IReactor arg1) {
        switch (this.numberOfCells) {
            case 1: {
                return new ItemStack(ItemsRegistry.depleted_thorium);
            }
            case 2: {
                return new ItemStack(ItemsRegistry.depleted_thorium_dual);
            }
            case 4: {
                return new ItemStack(ItemsRegistry.depleted_thorium_quad);
            }
            default: {
                throw new RuntimeException("invalid cell count: " + this.numberOfCells);
            }
        }
	}

}
