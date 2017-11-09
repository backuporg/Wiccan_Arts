package com.witchworks.common.block.tools;

import com.witchworks.common.lib.LibBlockName;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

/**
 * This class was created by Joseph on 3/4/2017.
 * It's distributed as part of Witchworks under
 * the MIT license.
 */
public class BlockCandleMedium extends BlockCandle {

	private static final AxisAlignedBB MEDIUM_BOX = new AxisAlignedBB(0.31, 0, 0.31, 0.69, 0.75, 0.69);

	public BlockCandleMedium() {
		super(LibBlockName.CANDLE_MEDIUM);
	}

	@SuppressWarnings("deprecation")
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return MEDIUM_BOX;
	}

	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		{
			for (int i = 0; i < 16; ++i) {
				items.add(new ItemStack(this, 1, i));
			}
		}
	}

	@Override
	public int getType() {
		return 1;
	}
}
