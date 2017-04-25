package com.witchworks.common.item.food.seed;

import com.witchworks.api.item.IModelRegister;
import com.witchworks.client.handler.ModelHandler;
import com.witchworks.common.core.WitchWorksCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * This class was created by Arekkuusu on 27/02/2017.
 * It's distributed as part of Wiccan Arts under
 * the MIT license.
 */
@SuppressWarnings ("WeakerAccess")
public class ItemSeed extends ItemSeeds implements IPlantable, IModelRegister {

	protected final Block crop;
	protected final Block soil;

	public ItemSeed(String id, Block crop, Block soil) {
		super(crop, soil);
		setRegistryName(id);
		setUnlocalizedName(id);
		setCreativeTab(WitchWorksCreativeTabs.PLANTS_CREATIVE_TAB);
		this.crop = crop;
		this.soil = soil;
	}

	@Override
	@SideOnly (Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		tooltip.add(TextFormatting.ITALIC + I18n.format("witch.tooltip." + getNameInefficiently(stack) + "_description.name"));
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		return soil == Blocks.FARMLAND ? EnumPlantType.Crop : EnumPlantType.Water;
	}

	public String getNameInefficiently(ItemStack stack) {
		return getUnlocalizedName().substring(5);
	}

	@Override
	@SideOnly (Side.CLIENT)
	public void registerModels() {
		ModelHandler.registerItem(this);
	}
}
