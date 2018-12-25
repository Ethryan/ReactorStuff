package reactorStuff.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import reactorStuff.tile.TileRTG;
import reactorStuff.tile.TileRTG.RTGType;

public class BlockRTG extends Block implements ITileEntityProvider{

	public static final PropertyEnum<EnumFacing> FACING=PropertyEnum.create("facing", EnumFacing.class);
	public static final AxisAlignedBB AABB_RTG_X=new AxisAlignedBB(0D, 3D/8D, 3D/8D, 1D, 5D/8D, 5D/8D);
	public static final AxisAlignedBB AABB_RTG_Y=new AxisAlignedBB(3D/8D, 0D, 3D/8D, 5D/8D, 1D, 5D/8D);
	public static final AxisAlignedBB AABB_RTG_Z=new AxisAlignedBB(3D/8D, 3D/8D, 0D, 5D/8D, 5D/8D, 1D);
	
	public BlockRTG() {
		super(Material.ANVIL);
		setSoundType(SoundType.METAL);
		setRegistryName("rtg");
		setUnlocalizedName(getRegistryName().toString());
		setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.DOWN));
		setCreativeTab(CreativeTabs.REDSTONE);
		setHardness(5F);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		if(state.getValue(FACING).getAxis()==EnumFacing.Axis.X)
			return AABB_RTG_X;
		if(state.getValue(FACING).getAxis()==EnumFacing.Axis.Z)
			return AABB_RTG_Z;
		return AABB_RTG_Y;
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		if(stack.getMetadata()<RTGType.values().length)
			((TileRTG)worldIn.getTileEntity(pos)).type=RTGType.values()[stack.getMetadata()];
	}
	
	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		for(int i=0;i<RTGType.values().length;i++){
			items.add(new ItemStack(this, 1, i));
		}
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		int meta=((TileRTG)worldIn.getTileEntity(pos)).type.ordinal();
		ItemStack stack=new ItemStack(this, 1, meta);
		InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), stack);
		super.breakBlock(worldIn, pos, state);
	}
	
	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		return new ArrayList<ItemStack>();
	}
	
	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isNormalCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return false;
	}
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return this.getDefaultState().withProperty(FACING, facing.getOpposite());
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[]{FACING});
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getIndex();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, meta<6?EnumFacing.getFront(meta):EnumFacing.DOWN);
	}
	
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return face != EnumFacing.UP && face != EnumFacing.DOWN ? BlockFaceShape.MIDDLE_POLE : BlockFaceShape.CENTER;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileRTG();
	}

}
