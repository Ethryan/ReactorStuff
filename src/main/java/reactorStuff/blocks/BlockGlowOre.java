package reactorStuff.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import reactorStuff.ReactorStuff;
import reactorStuff.core.MethodHelper;
import reactorStuff.items.ItemsRegistry;

public class BlockGlowOre extends Block{

	public final String name;
	public BlockGlowOre(String name) {
		super(Material.ROCK);
		this.name=name;
		setRegistryName(name);
		setUnlocalizedName(getRegistryName().toString());
		setHardness(3);
		setResistance(5);
		setHarvestLevel("pickaxe", 2);
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		setTickRandomly(name.equals("ore_terminium"));
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	@SideOnly(Side.CLIENT)
	public void initModel(){
		final ModelResourceLocation mrl = new ModelResourceLocation(ReactorStuff.MODID+":glow_"+name);
        StateMapperBase ignoreState = new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState iBlockState) {
                return mrl;
            }
        };
        ModelLoader.setCustomStateMapper(this, ignoreState);
        //ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(Constants.MODID+Type,"inventory"));
	}
	
	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state,
			int fortune) {
		if(name.equals("ore_elerium"))
			drops.add(new ItemStack(ItemsRegistry.elerium_crystal));
		else if(name.equals("ore_blazonium"))
			drops.add(new ItemStack(ItemsRegistry.gem_blazonium, Math.max(1, Block.RANDOM.nextInt(fortune+2))));
		else
			super.getDrops(drops, world, pos, state, fortune);
	}
	
	@Override
	public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune) {
		if(name.equals("ore_elerium")){
			Random rand = world instanceof World ? ((World)world).rand : new Random();
			return MathHelper.getInt(rand, 5, 9);
		}
		if(name.equals("ore_blazonium")){
			Random rand = world instanceof World ? ((World)world).rand : new Random();
			return MathHelper.getInt(rand, 3, 7);
		}
		return 0;
	}
	
	@Override
	public boolean canEntityDestroy(IBlockState state, IBlockAccess world, BlockPos pos, Entity entity) {
		if(name.equals("ore_terminium")&&entity instanceof EntityDragon)
			return false;
		return super.canEntityDestroy(state, world, pos, entity);
	}
	
	@Override
	public boolean isFireSource(World world, BlockPos pos, EnumFacing side) {
		return name.equals("ore_blazonium");
	}
	
	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		if(!worldIn.isRemote&&entityIn instanceof EntityLivingBase){
			if(name.equals("ore_blazonium")){
				entityIn.attackEntityFrom(DamageSource.HOT_FLOOR, 2);
				entityIn.setFire(10);
			}
			if(name.equals("ore_terminium")){
				MethodHelper.teleportEntityLivingRandomly((EntityLivingBase)entityIn, 16D, 8D);
			}
		}
	}
	
	public void teleportBlock(World worldIn, BlockPos pos, Random random){
		for(int tries=0;tries<16;tries++){
			double x = pos.getX() + (random.nextDouble() - 0.5D) * 16;
	        double y = pos.getY() + (random.nextDouble() - 0.5D) * 8;
	        double z = pos.getZ() + (random.nextDouble() - 0.5D) * 16;
	        BlockPos newPos=new BlockPos(x, y, z);
	        if(worldIn.getBlockState(newPos).getBlock()==Blocks.END_STONE&&
	        		worldIn.getBlockState(newPos.offset(EnumFacing.UP)).getBlock()!=Blocks.CHORUS_PLANT&&
	        		worldIn.getBlockState(newPos.offset(EnumFacing.UP)).getBlock()!=Blocks.CHORUS_FLOWER){
	        	worldIn.setBlockState(new BlockPos(x, y, z), this.getDefaultState());
	        	worldIn.setBlockState(pos, Blocks.END_STONE.getDefaultState());
	        	worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.BLOCKS, 1.0F, 1.0F);
	        	worldIn.playSound(null, x, y, z, SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.BLOCKS, 1.0F, 1.0F);
	        	break;
	        }
		}
	}
	
	@Override
	public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {
		if(name.equals("ore_terminium")){
			if(random.nextInt(10)==0){
				teleportBlock(worldIn, pos, random);
			}else
				for(EnumFacing facing:EnumFacing.VALUES){
					if(worldIn.getBlockState(pos.offset(facing)).getMaterial()==Material.WATER){
						teleportBlock(worldIn, pos, random);
						break;
					}
				}
		}
	}
	
}
