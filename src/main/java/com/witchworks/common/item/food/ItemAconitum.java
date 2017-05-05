package com.witchworks.common.item.food;

import com.witchworks.client.handler.ModelHandler;
import com.witchworks.common.lib.LibItemName;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * This class was created by Arekkuusu on 02/03/2017.
 * It's distributed as part of Witchworks under
 * the MIT license.
 */
public class ItemAconitum extends ItemCrop {

	public ItemAconitum() {
		super(LibItemName.ACONITUM, 2, 0.6F, false);
		addPotion(MobEffects.POISON, MobEffects.NAUSEA);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}
}
