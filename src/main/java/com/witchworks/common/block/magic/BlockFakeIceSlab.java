package com.witchworks.common.block.magic;

import com.witchworks.common.block.ModBlocks;
import com.witchworks.common.core.WitchWorksCreativeTabs;
import com.witchworks.common.lib.LibMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

/**
 * Created by Joseph on 9/3/2017.
 */
public abstract class BlockFakeIceSlab extends BlockSlab {

	@SuppressWarnings("deprecation")
	public BlockFakeIceSlab(String unlocalizedName) {
		super(Material.ICE);
		setResistance(2F);
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(new ResourceLocation(LibMod.MOD_ID, unlocalizedName));
		this.setCreativeTab(WitchWorksCreativeTabs.BLOCKS_CREATIVE_TAB);
		setHardness(2F);
		slipperiness = 0.98F;
		useNeighborBrightness = true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		IBlockState sideState = world.getBlockState(pos.offset(side));
		Block block = sideState.getBlock();
		return block != this && super.shouldSideBeRendered(state, world, pos, side);
	}

	@Override
	public String getUnlocalizedName(int meta) {
		return this.getUnlocalizedName();
	}

	public IBlockState isDouble(int meta) {
		if (!this.isDouble())
			return this.getDefaultState().withProperty(HALF, EnumBlockHalf.values()[meta % EnumBlockHalf.values().length]);
		return this.getDefaultState();
	}

	@Override
	public IProperty<?> getVariantProperty() {
		return HALF;
	}

	@Override
	public Comparable<?> getTypeForItem(ItemStack stack) {
		return EnumBlockHalf.BOTTOM;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		if (this.isDouble())
			return 0;
		return ((EnumBlockHalf) state.getValue(HALF)).ordinal() + 1;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(ModBlocks.fake_ice_slab_half);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[]{HALF});
	}

	@Override
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean isBlockNormalCube(IBlockState state) {
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean isTopSolid(IBlockState state) {
		return false;
	}
}