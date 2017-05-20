package com.witchworks.common.block.natural.crop;

import com.witchworks.common.lib.LibBlockName;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

/**
 * This class was created by Arekkuusu on 19/05/2017.
 * It's distributed as part of Witch Works under
 * the MIT license.
 */
public class CropThistle extends BlockCrop {

	public CropThistle() {
		super(LibBlockName.CROP_THISTLE, 6);
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(worldIn, pos, state, rand);
		if (isMaxAge(state) && state.getValue(AGE) != 7) {
			if (rand.nextBoolean()) {
				worldIn.setBlockState(pos, getDefaultState().withProperty(AGE, 7));
			} else {
				trySpread(worldIn, pos, rand);
			}
		}
	}

	private void trySpread(World world, BlockPos center, Random rand) {
		BlockPos I = center.add(-1, -1, -1);
		BlockPos F = center.add(1, 1, 1);
		BlockPos.getAllInBox(I, F).forEach(
				pos -> {
					if (rand.nextBoolean() && canSustainBush(world.getBlockState(pos.down()))
							&& (world.isAirBlock(pos) || world.getBlockState(pos).getBlock().isReplaceable(world, pos))) {
						world.setBlockState(pos, getDefaultState(), 2);
					}
				}
		);
	}

	@Override
	public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return true;
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		entityIn.attackEntityFrom(DamageSource.cactus, 1.0F);
	}
}
