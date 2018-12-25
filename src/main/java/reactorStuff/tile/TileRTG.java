package reactorStuff.tile;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.energy.IEnergyStorage;
import reactorStuff.blocks.BlockRTG;

public class TileRTG extends TileEntity implements ITickable{
	
	public RTGType type=RTGType.NONE;
	public int t=0;
	@CapabilityInject(IEnergyStorage.class)
	public static Capability<IEnergyStorage> ENERGY=null;
	public TileRTG() {
		
	}

	@Override
	public void update() {
		if(world.isRemote)
			return;
		t++;
		if(t>=20){
			t=0;
			if(type==RTGType.NONE)
				return;
			IBlockState rtg=world.getBlockState(pos);
			EnumFacing facing=rtg.getValue(BlockRTG.FACING);
			TileEntity tile=world.getTileEntity(pos.offset(facing));
			if(tile!=null&&tile.hasCapability(ENERGY, facing.getOpposite())){
				IEnergyStorage cap=tile.getCapability(ENERGY, facing.getOpposite());
				if(!cap.canReceive())
					return;
				int energy=type.output*20;
				TileEntity tile2;
				BlockPos pos2=pos;
				do{
					pos2=pos2.offset(facing.getOpposite());
					tile2=world.getTileEntity(pos2);
					if(tile2 instanceof TileRTG){
						IBlockState state2=world.getBlockState(pos2);
						if(state2.getValue(BlockRTG.FACING)==facing){
							energy+=((TileRTG)tile2).type.output*20;
						}
					}
				}while(tile2 instanceof TileRTG);
				cap.receiveEnergy(energy, false);
			}
		}
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("type", type.ordinal());
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		type=RTGType.values()[compound.getInteger("type")];
	}
	
	public enum RTGType implements IStringSerializable{
		NONE("empty",0),
		PLUTONIUM("plutonium",8),
		AMERICIUM("americium",2),
		LAWRENCIUM("lawrencium",20),
		CALIFORNIUM("californium",3);
		public String name;
		public int output;
		private RTGType(String name, int out) {
			this.name=name;
			this.output=out;
		}
		@Override
		public String getName() {
			return this.name;
		}
	}
}
