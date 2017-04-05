package com.wiccanarts.common.item.food;

import com.wiccanarts.common.lib.*;
import net.minecraft.init.*;

/**
 * This class was created by Arekkuusu on 02/03/2017.
 * It's distributed as part of Wiccan Arts under
 * the MIT license.
 */
public class ItemGinger extends ItemCrop {

	public ItemGinger() {
		super(LibItemName.GINGER, 4, 0.8F, false);
		addPotion(MobEffects.HASTE, MobEffects.STRENGTH);
	}
}
