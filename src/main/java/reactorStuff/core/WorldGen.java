package reactorStuff.core;

import java.util.Random;

import com.google.common.base.Predicate;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import reactorStuff.blocks.BlocksRegistry;

public class WorldGen implements IWorldGenerator{
	
	public WorldGenerator uranium, thorium, terminium, blazonium;
	
	public WorldGen() {
		uranium=new WorldGenMinable(BlocksRegistry.uranium.getDefaultState(), 3);
		thorium=new WorldGenMinable(BlocksRegistry.thorium.getDefaultState(), 3);
		terminium=new WorldGenMinable(BlocksRegistry.terminium.getDefaultState(), 3, new EndPredicate());
		blazonium=new WorldGenMinable(BlocksRegistry.blazonium.getDefaultState(), 3, new NetherPredicate());
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		if(world.provider.getDimension()==0){
			if(Config.uraniumRarity!=0){
				runGenerator(uranium, world, random, chunkX, chunkZ, (int)Config.uraniumRarity, 0, 68);
			}
			if(Config.thoriumRarity!=0){
				runGenerator(thorium, world, random, chunkX, chunkZ, (int)Config.thoriumRarity, 0, 68);
			}
		}
		if(world.provider.getDimension()==-1){
			if(Config.blazoniumRarity!=0){
				runGenerator(blazonium, world, random, chunkX, chunkZ, (int)Config.blazoniumRarity, 0, 128);
			}
		}
		if(world.provider.getDimension()==1){
			if(Config.terminiumRarity!=0){
				runGenerator(terminium, world, random, chunkX, chunkZ, (int)Config.terminiumRarity, 0, 250);
			}
		}
		
	}
	
	public void runGenerator(WorldGenerator ore, World world, Random rand,
			int chunk_X, int chunk_Z, int chancesToSpawn, int minHeight, int maxHeight){
		int diffHeight=maxHeight-minHeight;
		for(int i=0;i<chancesToSpawn;i++){
			int x = chunk_X * 16 + rand.nextInt(16);
	        int y = minHeight + rand.nextInt(diffHeight);
	        int z = chunk_Z * 16 + rand.nextInt(16);
	        ore.generate(world, rand, new BlockPos(x, y, z));
		}
	}
	
	static class EndPredicate implements Predicate<IBlockState> {

		@Override
		public boolean apply(IBlockState input) {
			if(input!=null&&input.getBlock()==Blocks.END_STONE){
				return true;
			}
			return false;
		}
		
	}
	static class NetherPredicate implements Predicate<IBlockState> {
		
		@Override
		public boolean apply(IBlockState input) {
			if(input!=null&&input.getBlock()==Blocks.NETHERRACK){
				return true;
			}
			return false;
		}
	}

}
