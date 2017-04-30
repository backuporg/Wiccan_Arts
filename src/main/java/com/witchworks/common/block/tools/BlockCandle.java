package com.witchworks.common.block.tools;

import com.witchworks.common.block.BlockMod;
import com.witchworks.common.block.tile.TileCandle;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import static com.witchworks.api.state.WitchWorksState.COLOR;

/**
 * This class was created by Arekkuusu on 11/03/2017.
 * It's distributed as part of Witchworks under
 * the MIT license.
 */
public class BlockCandle extends BlockMod implements ITileEntityProvider {

	public BlockCandle(String id) {
		super(id, Material.CLOTH);
		setSound(SoundType.CLOTH);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		final TileCandle candle = (TileCandle) worldIn.getTileEntity(pos);
		if (candle != null) {
			if (heldItem != null && heldItem.getItem() == Items.FLINT_AND_STEEL) {
				heldItem.damageItem(1, playerIn);
				candle.litCandle();
			} else {
				candle.unLitCandle();
			}
		}
		worldIn.theProfiler.startSection("checkLight");
		worldIn.checkLight(pos);
		worldIn.theProfiler.endSection();
		return true;
	}

	@Override
	protected IBlockState defaultState() {
		return super.defaultState().withProperty(COLOR, EnumDyeColor.WHITE);
	}

	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state);
	}

	@Override
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, COLOR);
	}

	@SuppressWarnings ("deprecation")
	public MapColor getMapColor(IBlockState state) {
		return state.getValue(COLOR).getMapColor();
	}

	@SuppressWarnings ("deprecation")
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(COLOR, EnumDyeColor.byMetadata(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(COLOR).getMetadata();
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, ItemStack stack) {
		return getDefaultState().withProperty(COLOR, EnumDyeColor.byMetadata(meta));
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return worldIn.getBlockState(
				pos.down()).isSideSolid(
				worldIn,
				pos.down(),
				EnumFacing.UP
		);
	}

	@Override
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
		final TileCandle candle = (TileCandle) world.getTileEntity(pos);
		return candle != null && candle.isLit() ? (int) (15.0F * (0.5F + getType() * 0.25)) : 0;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileCandle(getType(), EnumDyeColor.byMetadata(meta));
	}

	@SuppressWarnings ("deprecation")
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@SuppressWarnings ("deprecation")
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	public int getType() {
		return 0;
	}
}
