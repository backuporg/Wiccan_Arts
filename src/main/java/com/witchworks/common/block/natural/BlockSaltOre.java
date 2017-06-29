package com.witchworks.common.block.natural;

import com.witchworks.common.block.BlockMod;
import com.witchworks.common.item.ModItems;
import com.witchworks.common.lib.LibBlockName;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

/**
 * This class was created by Joseph on 3/4/2017.
 * It's distributed as part of Witchworks under
 * the MIT license.
 */
public class BlockSaltOre extends BlockMod {

	public BlockSaltOre() {
		super(LibBlockName.SALT_ORE, Material.ROCK);
		setResistance(3F);
		setHardness(3F);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return ModItems.salt;
	}

	@Override
	public int quantityDroppedWithBonus(int fortune, Random random) {
		return this.quantityDropped(random) + random.nextInt(fortune + 1);
	}

	@Override
	public int quantityDropped(Random random) {
		return 4 + random.nextInt(2);
	}

	@Override
	public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune) {
		if (this.getItemDropped(state, RANDOM, fortune) != Item.getItemFromBlock(this)) {
			return 1 + RANDOM.nextInt(5);
		}
		return 0;
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
}
