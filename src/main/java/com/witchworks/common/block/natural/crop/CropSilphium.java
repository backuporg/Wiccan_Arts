package com.witchworks.common.block.natural.crop;

import com.witchworks.common.lib.LibBlockName;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

/**
 * This class was created by Arekkuusu on 19/05/2017.
 * It's distributed as part of Witch Works under
 * the MIT license.
 */
public class CropSilphium extends BlockCrop {

	public CropSilphium() {
		super(LibBlockName.CROP_SILPHIUM);
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (rand.nextBoolean() || !worldIn.getBiome(pos).canRain()) return;
		if (isMaxAge(state) && state.getValue(AGE) != 7 && canSustainBush(worldIn.getBlockState(pos.down())) && worldIn.isAirBlock(pos.up())) {
			if (worldIn.getBlockState(pos.down()).getBlock() == this) {
				worldIn.setBlockState(pos, state.withProperty(AGE, 7), 2);
			} else if (rand.nextInt(20) == 0) {
				if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, true)) {
					worldIn.setBlockState(pos.up(), getDefaultState());
					net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
				}
			}
		}
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		IBlockState state = worldIn.getBlockState(pos.down());
		return canSustainBush(state);
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) {
		this.checkForDrop(worldIn, pos, state);
	}

	private boolean checkForDrop(World worldIn, BlockPos pos, IBlockState state) {
		if (this.canSustainBush(worldIn.getBlockState(pos.down()))) {
			return true;
		} else {
			this.dropBlockAsItem(worldIn, pos, state, 0);
			worldIn.setBlockToAir(pos);
			return false;
		}
	}

	@Override
	protected boolean canSustainBush(IBlockState state) {
		return state.getBlock() == Blocks.FARMLAND || state.getBlock() == this;
	}
}
