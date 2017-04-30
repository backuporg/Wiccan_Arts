package com.witchworks.common.item.food;

import com.witchworks.common.core.WitchWorksCreativeTabs;
import net.minecraft.item.ItemFood;

/**
 * This class was created by Arekkuusu on 28/02/2017.
 * It's distributed as part of Witchworks under
 * the MIT license.
 */
public class ItemModFood extends ItemFood {

	public ItemModFood(String id, int amount, float saturation, boolean isWolfFood) {
		super(amount, saturation, isWolfFood);
		setRegistryName(id);
		setUnlocalizedName(id);
		setCreativeTab(WitchWorksCreativeTabs.ITEMS_CREATIVE_TAB);
	}
}
